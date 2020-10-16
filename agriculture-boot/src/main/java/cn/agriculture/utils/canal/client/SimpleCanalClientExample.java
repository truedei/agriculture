package cn.agriculture.utils.canal.client;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;


import cn.agriculture.entity.ManagerUser;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import net.sf.json.JSONObject;


public class SimpleCanalClientExample {


    public static void main(String args[]) {
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(
                new InetSocketAddress("172.17.0.2",
                11111), "example", "", "");

        try {
            //订阅
            connector.connect();
            //所有的变化
            connector.subscribe(".*\\..*");

            //回滚
            connector.rollback();
            //一个批次获取的最大数据量
            int batchSize = 1000;

            while (true){
                //获取指定数据的条数这里默认是120，但是不做确认
                //获取指定数量的数据
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                //获取批次ID
                long batchId = message.getId();
                //获取数据大小
                int size = message.getEntries().size();

                if (batchId == -1 || size == 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    // System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
                    //进行数据表筛选和增量数据处理
                    printEntry(message.getEntries());
                }

                //这里提交确认
                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }

        } finally {
            //销毁连接
            connector.disconnect();
        }
    }

    //进行数据表筛选和增量数据处理
    private static void printEntry(List<Entry> entrys) {

        //对增量数据进行循环处理
        for (Entry entry : entrys) {

            //如果是事务开始或、事务结束或者查询，则跳过本次循环
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }

            //定义一行数据变化
            RowChange rowChage = null;
            try {
                //获取一行数据
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## 获取行数据错误 , data:" + entry.toString(),e);
            }


            //这里可以用来判断需要监控的数据表，来过滤数据

            //按策略处理增量数据
            action(entry,rowChage);

        }
    }


    /**
     * 执行的动作 按策略处理增量数据
     * @param entry
     * @param rowChage
     */
    private static void action(Entry entry, RowChange rowChage){
//        String templog = String.format("binlog[%s:%s] , name[%s,%s] , eventType : %s",
//                entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
//                entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
//                eventType);
//        System.out.println(templog);

        //获取数据库事件类型
        EventType eventType = rowChage.getEventType();

        //从增量数据中获取数据库名
        String dbname = entry.getHeader().getSchemaName();



        //按照行进行遍历
        for (RowData rowData : rowChage.getRowDatasList()) {



            //删除操作
            if (eventType == EventType.DELETE) {
                System.out.println("删除");
                printColumn(rowData.getBeforeColumnsList());

                //插入操作
            } else if (eventType == EventType.INSERT) {
                System.out.println("新增");
                printColumn(rowData.getAfterColumnsList());


                if(dbname.equals("manager_user")){
                    System.out.println("管理表");
                    //插入Redis
                    redisInsert( dbname,rowData.getAfterColumnsList(), ManagerUser.class);
                }

//                Map<String,String>

                //修改
            }else if (eventType == EventType.UPDATE) {
                System.out.println("更新");
                printColumn(rowData.getAfterColumnsList());

            }

        }
    }

    /**
     * 解析数据
     * @param columns
     */
    private static void printColumn(List<Column> columns) {
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }

    /**
     * 同步插入数据到Redis
     * @param tableName
     * @param columns
     */
    private static <T> void redisInsert( String tableName,List<Column> columns,Class<T> cls){
        JSONObject json=new JSONObject();
        //创建一个对象，方便后续反射赋值
        Object obj=null;

        //利用反射获取，执行类的实例化对象
        try {
            obj = cls.newInstance();

            //利用反射，获取对象类信息中的所有属性
            Field[] fields = cls.getDeclaredFields();

            for (Column column : columns) {
                json.put(column.getName(), column.getValue());

                for(Field fd:fields){
                    //屏蔽权限
                    fd.setAccessible(true);
                    //为对象属性赋值
                    fd.set(obj,column.getName());
                }

            }

            //打印
            System.out.println("打印：");
            System.out.println(((T)obj).toString());


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        if(columns.size()>0){
            System.out.println("Mysql插入了");
//            RedisUtil.stringSet("user:"+ columns.get(0).getValue(),json.toJSONString());
        }
    }

    /**
     * 同步修改Redis中的数据
     * @param tableName
     * @param columns
     */
    private static  void redisUpdate( String tableName,List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if(columns.size()>0){
            System.out.println("Mysql更新了");
//            RedisUtil.stringSet("user:"+ columns.get(0).getValue(),json.toJSONString());
        }
    }


    /**
     * 同步删除Redis中的数据
     * @param tableName
     * @param columns
     */
    private static  void redisDelete(String tableName, List<Column> columns){
        JSONObject json=new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if(columns.size()>0){
            System.out.println("Mysql删除了");
//            RedisUtil.delKey("user:"+ columns.get(0).getValue());
        }
    }



    /**
     * 更新数据
     * @param tableName
     * @param columns
     */
    private void setRedis(String tableName, List<Column> columns)   {
        //定义json串用于存储增量数据的值
        JSONObject json = new JSONObject();
        //获取增量列和json串
        columns = dataFormatAndTransCode(columns, json);
        //定义 键 值
        String key, value;
        //如果存在数据，则继续处理
        if (columns.size() > 0) {
            //获取key值
            key = columns.get(0).getValue();
            //获取value值
            value = json.toString();
            //根据表名决定操作方式
            switch (tableName) {
                //如果是特殊表，则进行特殊处理
                case "special_table01":
                    //进行特殊处理，如key值格式化等等
                    //...
                    //设置缓存：以gyrlzyw-rmzw为值的缓存
//                    RedisUtils.set(key, value);

                    //打印日志信息
                    System.out.println("设置缓存：key  = " + key + "，value = " + value);
                    break;
                case "special_table02":
                    //...
                    break;

                //如果是普通的缓存中间表，则不需要额外处理，直接更新对应缓存即可
                default:
                    //设置缓存
//                    RedisUtils.set(key, value);
                    //打印日志信息
                    System.out.println("设置缓存：key  = " + key + "，value = " + value);
                    break;
            }
        }
    }




    /**
     * <p>Title: 对增量数据进行格式化与编码转换</p>
     * <p>
     * <p>可以按照项目编码和项目字段进行自定义</p>
     *
     * @author 韩超 2018/4/2 16:56
     */
    private List<Column> dataFormatAndTransCode(List<Column> columns, JSONObject json)   {
        //按列遍历（其实就是按字段遍历）
        for (Column column : columns) {
            //如果字段类型是blob，则进行转码
            if (column.getMysqlType().contains("blob")) {
                try {
                    json.put(column.getName(), new String(column.getValue().getBytes("ISO-8859-1"), "gbk"));
                } catch (UnsupportedEncodingException e) {
                    System.out.println(e.getMessage());
                }
            } else {//如果是其他字段，不用处理
                json.put(column.getName(), column.getValue());
            }
        }
        return columns;
    }

}