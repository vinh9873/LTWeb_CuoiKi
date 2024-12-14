package vn.ute.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class VnPayRequest {

    public static final String VNP_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?";

    private String vnp_Version = "2.1.1";
    private String vnp_Command = "pay";
    private String vnp_TmnCode = "SDTCL245";
    private String vnp_CurrCode = "VND";
    private String vnp_IpAddr = "123.231.213.132";
    private String vnp_Locale = "vn";
    private String vnp_ReturnUrl = "http://localhost:8080/orders/pay-with-vnpay-return";
    private String vnp_OrderType = "billpayment";
    private String vnp_TxnRef = "" + UUID.randomUUID();

    private String vnp_CreateDate;
    private String vnp_ExpireDate;
    private String vnp_OrderInfo = "Pay at Shop Group 15";
    private Float vnp_Amount;
    private String vnp_BankCode;

}
