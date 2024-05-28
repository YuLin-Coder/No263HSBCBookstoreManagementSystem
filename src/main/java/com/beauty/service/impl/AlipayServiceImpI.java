package com.beauty.service.impl;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.*;
import com.beauty.config.AlipayConfig;
import com.beauty.service.AlipayService;
import org.springframework.stereotype.Service;

@Service("alipayService")
public class AlipayServiceImpI implements AlipayService {
    /** 调取支付宝接口 web端支付*/
    DefaultAlipayClient alipayClient = new DefaultAlipayClient(
            AlipayConfig.GATEWAYURL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);

    /** 调取支付宝接口app端支付*/
    AlipayClient alipayClients = new DefaultAlipayClient(
            AlipayConfig.GATEWAYURL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);


    /**
     * web端订单支付
     *
     * @param outTradeNo  订单编号（唯一）
     * @param totalAmount 订单价格
     * @param subject     商品名称
     */
    @Override
    public String webPagePay(String outTradeNo, Integer totalAmount, String subject) throws Exception {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        /** 同步通知，支付完成后，支付成功页面*/
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        /** 异步通知，支付完成后，需要进行的异步处理*/
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+outTradeNo+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+totalAmount+"," +
                "    \"subject\":\""+subject+"\"," +
                "    \"body\":\""+"商品介绍"+"\"," +
                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        /**转换格式*/
        String result = alipayClient.pageExecute(alipayRequest).getBody().replace('\"','\'').replace('\n',' ');
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) throws Exception {
        AlipayServiceImpI s = new AlipayServiceImpI();
        String s1 = s.webPagePay("123", 123, "123");
        System.out.println(s1);
    }
    /**
     * app端订单支付
     *
     * @param outTradeNo  订单编号
     * @param totalAmount 订单价格
     * @param subject     商品名称
     */
    @Override
    public String appPagePay(String outTradeNo, Integer totalAmount, String subject) throws Exception {
        AlipayTradeWapPayRequest alipayRequest=new AlipayTradeWapPayRequest();

        /** 同步通知，支付完成后，支付成功页面*/
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        /** 异步通知，支付完成后，需要进行的异步处理*/
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        /**销售产品码（固定）*/
        String productCode="QUICK_WAP_WAY";

        /** 进行赋值 */
        AlipayTradeWapPayModel wapPayModel=new AlipayTradeWapPayModel();
        wapPayModel.setOutTradeNo(outTradeNo);
        wapPayModel.setSubject(subject);
        wapPayModel.setTotalAmount(totalAmount.toString());
        wapPayModel.setBody("商品名称");
        wapPayModel.setTimeoutExpress("200000m");
        wapPayModel.setProductCode(productCode);
        alipayRequest.setBizModel(wapPayModel);

        /** 格式转换*/
        String result = alipayClients.pageExecute(alipayRequest).getBody().replace('\"','\'').replace('\n',' ');
        return result;
    }

    /**
     * 退款
     *
     * @param outTradeNo   订单编号
     * @param refundReason 退款原因
     * @param refundAmount 退款金额
     * @param outRequestNo 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     */
    @Override
    public String refund(String outTradeNo, String refundReason, Integer refundAmount, String outRequestNo) throws AlipayApiException {
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        /** 调取接口*/
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                + "\"refund_amount\":\""+ refundAmount +"\","
                + "\"refund_reason\":\""+ refundReason +"\","
                + "\"out_request_no\":\""+ outRequestNo +"\"}");
        String result = alipayClient.execute(alipayRequest).getBody();
        return result;
    }

    /**
     * 交易查询
     *
     * @param outTradeNo 订单编号（唯一）
     */
    @Override
    public String query(String outTradeNo) throws AlipayApiException {
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        /**请求接口*/
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","+"\"trade_no\":\""+ "" +"\"}");
        /**转换格式*/
        String result = alipayClient.execute(alipayRequest).getBody();
        return result;
    }

    /**
     * 交易关闭
     *
     * @param outTradeNo 订单编号（唯一）
     */
    @Override
    public String close(String outTradeNo) throws AlipayApiException {
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\"," +"\"trade_no\":\""+ "" +"\"}");

        String result = alipayClient.execute(alipayRequest).getBody();

        return result;
    }

    /**
     * 退款查询
     *
     * @param outTradeNo   订单编号（唯一）
     * @param outRequestNo 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     */
    @Override
    public String refundQuery(String outTradeNo, String outRequestNo) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

        /** 请求接口*/
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                +"\"out_request_no\":\""+ outRequestNo +"\"}");

        /** 格式转换*/
        String result = alipayClient.execute(alipayRequest).getBody();

        return result;
    }
}
