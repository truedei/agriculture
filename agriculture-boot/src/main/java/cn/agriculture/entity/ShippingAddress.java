package cn.agriculture.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
*
* @Author: 郑晖 truedei
* @Date: 20-5-15 下午10:21
* @Description: 收货地址实体类
*/
@Getter
@Setter
public class ShippingAddress {

    private Integer shippingAddressId;//收货地址id

    private Integer userId;//用户id

    private String userName;//收货姓名

    private String mobilePhone;//收货手机号

    private Integer province;//所属省

    private Integer city;//所属市/区

    private Integer area;//所属区域

    private String street;//街道

    private String completeAddress;//详细地址

    private String remarks;//备注

    private Date createDate;//创建时间
    private String createDateStr;

    private Integer defaultAddress;//是否是默认地址

    private String address;

}