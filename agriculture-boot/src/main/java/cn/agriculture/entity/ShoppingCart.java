package cn.agriculture.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: truedei
 * @Date: 2020 /20-5-29 15:00
 * @Description:　购物车
 */
@Setter
@Getter
public class ShoppingCart {

    private Integer shoppingCartId;//购物车id

    private Integer userId;//购买用户的id

    private Integer productId;//商品id

    private Integer amount;//购买数量

    private Integer payStatus=0;//支付状态,用来判断是否支付成功　　0:未支付  1:正在支付　　2:已支付

    private String out_trade_no;//订单编号



    private String memberUserName;//购买用户
    private String productName;//商品名字
    private Double productPrice;//商品单价
    private String homeDisplayDrawing;//单张展示图片

    private String shoppingCartNo;//购物车商品编号


    @Override
    public String toString() {
        return "ShoppingCart{" +
                "shoppingCartId=" + shoppingCartId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", amount=" + amount +
                ", payStatus=" + payStatus +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", memberUserName='" + memberUserName + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", homeDisplayDrawing='" + homeDisplayDrawing + '\'' +
                ", shoppingCartNo='" + shoppingCartNo + '\'' +
                '}';
    }
}
