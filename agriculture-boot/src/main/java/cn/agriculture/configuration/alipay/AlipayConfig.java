package cn.agriculture.configuration.alipay;

public class AlipayConfig {

    //这里用natapp内外网穿透
    public static final String natUrl = "http://127.0.0.1:8080";

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102400748699";//在后台获取（必须配置）


    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDVJz2dGcxaYbM41OdqV60LYo2V9EepSuiMCwk+nvnUEgVXlPYbVrvxlzGch5JgmGdi1tw1BEZeKXvdpjq6ecv1XQTO4Lb2+nXCTaPHftoptjpeqnGqMnFy3uioX8oVMOdNB6cCn6L2UNZWG7xwUcqu9FedyAkMBA4ZwkZy0Ox6a8IRaDeeX6vVBF1MVrOJgkA6D1DJaNnzhaqkn1rDHQAx+GKrvnfip4oRGQNjjHc+I8rjHRo5c/XU0LFwuBPosmlwa/4tQ8OtUGxjoEgPXKvpLfNCcNIg7Mvy8Da+cBIbc+/yjMZ5+oWDtU9QiReLtlvVTozVPAC6YDseGsCyXbkNAgMBAAECggEAFyC937j34mqtbqIzReejgBO+dyHdULIvD1r3MtM/GlKmP72LAqZjmDAmkuxm5pJCzYewtxPyqq7noMIzpx4mEkZ4ihX55EyC0IVqGvBZraL3LGRkFuqIQsWLCxHxtGDtB/+nkHJP1VgqGYfC55EIY/lhNF6LFMGv4f2hdT3UGug5nYevLiBJ3Bf07fwZTWIp64XfL489dM+73cwSXH20YcFd5za9bgeVgEGcxvwtMarbxZg0RX5CuRYs3VzjvzPR/qN+rpANm5YpPe64Gplyn1hanu2NU4BjuxWWJxSdDk3zLXeMNFOkF1K74k/7VGGX+nJpSy0MjC7EVuutRpB6QQKBgQDv4H/in97rTqeECfxcRGgfXwvWtolFI/A/MBGiV84gSQb8I24GFfiGTIAZD7iPA2skt48+oyACl0cPXWOeAtLOYjg+bxMjvscvZ7E9gbCKj+QcWE1llQl7i57Vus7k8xRx5vCvxDru+hPDRRqed7fl74kZVvIqLLEUHAviypWdPQKBgQDjeun2ZDlBZKMDT1kbRo+x/C12yDqXE7nT8iAzcubfpDDhvdLsY8cLQwb3xSjBB8dSTvrDqGGM80Wqcbt0RkxtpGo00I9M0cerW8TGdwIXdMH5gEEmvUp7uzAR5Z0RQp4CC14bid6Ikhj0FakYQm/+TV3sQQoE4Lnl0oAwhJvoEQKBgQCL81MpiVSpvK4HXzwZKOAGzZE+qCjWae1VMFpZeRkNxQ2KNbGPY2ooEKH7/SwUZE4ykdXFGa8nU9Nlp3rjSYpsVumxo2RrSncztS+3Tt8NOH5B+1LMnXN23TLCR8dF1HxtJ+vs4HPx0X2OQ9cP9ZEZWfOHES8gZAq8TNCydLdtkQKBgQDS9UUNy8pRO6v1QNvI8LoH/G6JLo0vWArIZE26iL9U/Wabord9o9aOOh23H294++a8DSR+2KOG3IBp3clSvnLYZ1ZbHmMWgaFi59rnlqwo5o2Q/YDuFTOC5YhhwIfV3uq7yKEAdu4Zxys/COtk5RAJTORiWbqZ5XJgkMNyuzf+kQKBgBntmyv71b1NxnL020BOhkL4w2+Bfe/4wjHOOPYpWuBnImSuUdNmIpZqW0FraxdhDwB4voc0GkYyoaE/rKVOpHeelHkh1Yw40KyZnS7/2Uz2AKRd4wCU5WndR382eBQsTWfZ13kJzZBxEfuRv8QGA0KxuRqPSDxaBrBbGt3YBufX";//教程查看获取方式（必须配置）

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.html 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAheqx2U5wGEJ3Es8QCtCW2k6ZiWa+LKXlESOQQo7x+LDekbsUCAg/tDefpraItV2uDRTjF6Ax/56qwZVTYgEFQIFSyRIOswrOlkTdEdwg9qvvKXl8lzlB8PkqXmm3jS/bLgShM0NwFf33y6Djm5Zq1ZfUvhlbBNIybCYzNBebcE97fuh+C9EBVJ6T5x6cQkRTa1O6kXAcuJzWSJda1yvlhEJClvmVarDIx4AsT6UzEsc4pp92m0K2vozYWDc42X9a+eSSsR6kNjoAf7oGxMbFhSnijnQ35jilaRG1g6mNdV2De7vbL9EwMpVPlfbw9yuLmvs/RZ4GViyjLL+XAnzf8wIDAQAB" ;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = natUrl + "/alipay/alipayNotifyNotice";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String return_url = natUrl + "/alipay/alipayReturnNotice";

//    public static String return_url = "http://localhost:63342/agriculture/home/";

    public static String return_url = natUrl+"/rest/agriculture/alipayController/alipayReturnNotice";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "UTF-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";//注意：沙箱测试环境，正式环境为：https://openapi.alipay.com/gateway.do

}
