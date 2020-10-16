package cn.agriculture.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 支付完返回的订单信息，连接返回如下：
 *
 *         charset=UTF-8
 *         out_trade_no=20150320010101006
 *         method=alipay.trade.page.pay.return
 *         total_amount=0.10
 *         sign=FUYKYDu36aA7YPlZnla6zfB1xm6LUzscqG2OOo7JpI1bZ0u1urbjKBxNN39XrM1bTUBMboeDpK08%2B5Rj0OgrUVczAGRjEuClB6wj6SIcWgkEivciMwoTaEAjjEM5TC72txKogHsu8LM08y00avOUpC55DHbgKThaW6LVkLr71aYo1ZbkVMKXNF4PW99VTLCmyZ%2BB8NG2qyviOfXRe%2FF4tG%2BiiM4YrCl4pPYnx7xftVtAkfAbhjMuvTTOIeN9CzV%2FtcmEMiPCvudzV2meQQTIAdUd85nkSX3lYyopjzjBKpxTsBPrAGdKg7mVoyU8LfLWTsh4LT8G3NKQnGyOlCOhaQ%3D%3D
 *         trade_no=2020053022001464421000095240
 *         auth_app_id=2016102400748699
 *         version=1.0
 *         app_id=2016102400748699
 *         sign_type=RSA2
 *         seller_id=2088102180837026
 *         timestamp=2020-05-30+13%3A02%3A42
 *
 * @Auther: truedei
 * @Date: 2020 /20-5-30 15:28
 * @Description: 支付完返回的订单的信息
 */
@Getter
@Setter
public class AlipayReturnNotice {
    private String charset; //编码
    private String out_trade_no;//订单编号    1
    private String method;//方法
    private String total_amount;//支付金额  1
    private String sign;
    private String trade_no;//支付宝订单编号 1
    private String auth_app_id;//认证id
    private String version;//版本
    private String app_id;//app ID
    private String sign_type;//加密方式
    private String seller_id;//id
    private String timestamp;//日期


    @Override
    public String toString() {
        return "AlipayReturnNotice{" +
                "charset='" + charset + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", method='" + method + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", sign='" + sign + '\'' +
                ", trade_no='" + trade_no + '\'' +
                ", auth_app_id='" + auth_app_id + '\'' +
                ", version='" + version + '\'' +
                ", app_id='" + app_id + '\'' +
                ", sign_type='" + sign_type + '\'' +
                ", seller_id='" + seller_id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
