//package cn.agriculture;
//
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClientBuilder;
//import com.aliyun.oss.model.PutObjectRequest;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//
///**
// * @Auther: truedei
// * @Date: 2020 /20-5-22 12:58
// * @Description:
// */
//public class alibabaOOSTest {
//
//    @Test
//    public  void test1(){
//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
//        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//        String accessKeyId = "LTAI4G6DHppqYssDLaxYdVYo";
//        String accessKeySecret = "qirun7Si4QjgEB8zoyhwtyiBoiZ6B7";
//        String bucketName = "zhenghui1";
//
//        //用户登录名称 truedei@1102282151669437.onaliyun.com
//        //AccessKey ID LTAI4G6DHppqYssDLaxYdVYo
//        //AccessKey Secret qirun7Si4QjgEB8zoyhwtyiBoiZ6B7
//
//        // 创建OSSClient实例。
//
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        // 创建存储空间。
//        ossClient.createBucket(bucketName);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//    }
//
//    @Test
//    public void test2(){
//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
//        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//        String accessKeyId = "LTAI4G6DHppqYssDLaxYdVYo";
//        String accessKeySecret = "qirun7Si4QjgEB8zoyhwtyiBoiZ6B7";
//        String bucketName = "8042965";
//
//// <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
//        String objectName = "test1/test2/123.jpg";
//
//// 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//// 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
//        String content = "Hello OSS";
//        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));
//
//// 关闭OSSClient。
//        ossClient.shutdown();
//
//    }
//
//
//    @Test
//    public void test3(){
//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
//        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//        String accessKeyId = "LTAI4G6DHppqYssDLaxYdVYo";
//        String accessKeySecret = "qirun7Si4QjgEB8zoyhwtyiBoiZ6B7";
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        // 创建PutObjectRequest对象。
//        String content = "Hello OSS";
//        // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
//        PutObjectRequest putObjectRequest = new PutObjectRequest("<yourBucketName>", "<yourObjectName>", new ByteArrayInputStream(content.getBytes()));
//
//        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
//        // ObjectMetadata metadata = new ObjectMetadata();
//        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
//        // metadata.setObjectAcl(CannedAccessControlList.Private);
//        // putObjectRequest.setMetadata(metadata);
//
//        // 上传字符串。
//        ossClient.putObject(putObjectRequest);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//    }
//}
