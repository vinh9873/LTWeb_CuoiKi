package vn.ute.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import vn.ute.dto.VnPayRequest;

public class VnPayRequestUtil {

    private static final String VNP_HASH_SECRET = "NB88MNJTDAX7RBI5ZJ6C8VEHQ4UFNBHN";
    private static final String VNP_DATETIME_PATTERN = "yyyyMMddHHmmss";
    private static final int VNP_EXPIRE_AFTER_SECONDS = 600;

    private VnPayRequestUtil() {}

    public static String buildUrlQuery(VnPayRequest req) {
        var fields = new HashMap<String, String>();
        fields.put("vnp_Version", req.getVnp_Version());
        fields.put("vnp_Command", req.getVnp_Command());
        fields.put("vnp_TmnCode", req.getVnp_TmnCode());
        fields.put("vnp_CurrCode", req.getVnp_CurrCode());
        fields.put("vnp_IpAddr", req.getVnp_IpAddr());
        fields.put("vnp_Locale", req.getVnp_Locale());
        fields.put("vnp_ReturnUrl", req.getVnp_ReturnUrl());
        fields.put("vnp_OrderType", req.getVnp_OrderType());
        fields.put("vnp_TxnRef", req.getVnp_TxnRef());
        fields.put("vnp_CreateDate", req.getVnp_CreateDate());
        fields.put("vnp_ExpireDate", req.getVnp_ExpireDate());
        fields.put("vnp_OrderInfo", req.getVnp_OrderInfo());
        fields.put("vnp_Amount", "" + (long) (req.getVnp_Amount() * 100));
        fields.put("vnp_BankCode", req.getVnp_BankCode());

        var sortedFields = fields.keySet().stream().sorted().toList();

        var paramsToHash = sortedFields.stream()
                .map(field -> {
                    try {
                        return "%s=%s".formatted(
                                field,
                                URLEncoder.encode(fields.get(field), StandardCharsets.US_ASCII.toString()));
                    }
                    catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.joining("&"));

        var queryParams = sortedFields.stream()
                .map(field -> {
                    try {
                        return "%s=%s".formatted(
                                URLEncoder.encode(field, StandardCharsets.US_ASCII.toString()),
                                URLEncoder.encode(fields.get(field), StandardCharsets.US_ASCII.toString()));
                    }
                    catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.joining("&"));

        var secureHash = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, VNP_HASH_SECRET).hmacHex(paramsToHash);
        return queryParams + "&vnp_SecureHash=" + secureHash;
    }

    public static String[] getPaymentTimed() {
        var sdt = new SimpleDateFormat();
        sdt.applyPattern(VNP_DATETIME_PATTERN);
        var createInst = Instant.now();
        var createDate = sdt.format(new Date(createInst.toEpochMilli()));
        var expireDate =
                sdt.format(new Date(createInst.plusSeconds(VNP_EXPIRE_AFTER_SECONDS).toEpochMilli()));
        return new String[] {createDate, expireDate};
    }
}
