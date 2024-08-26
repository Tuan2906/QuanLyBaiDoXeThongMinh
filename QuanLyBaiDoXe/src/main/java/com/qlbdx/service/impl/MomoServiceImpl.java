/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qlbdx.service.MomoService;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author quang
 */
@Service
@PropertySource("classpath:momo.properties")
public class MomoServiceImpl implements MomoService {

    private String partnerCode;

    private String accessKey;

    private String secretKey;

    private String endpoint;

    private final RestTemplate restTemplate = new RestTemplate();

//    @Autowired
//    public MomoServiceImpl(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
    @Autowired
    private Environment env;

    @Override
    public String createPaymentRequest(String amount, String redirect_Url) throws Exception {
        String orderInfo = "Thanh toan qua MoMo";
        String redirectUrl = "http://localhost:3000/success";
        String ipnUrl = "http://192.168.1.17:8000/momo_ipn";
        String orderId = UUID.randomUUID().toString();
        String requestId = UUID.randomUUID().toString();
        String requestType = "payWithATM";
        String extraData = ""; // Pass empty value or encode base64 JSON String
        this.accessKey = env.getProperty("momo.accessKey");
        this.partnerCode = env.getProperty("momo.partnerCode");
        this.endpoint = env.getProperty("momo.endpoint");
        this.secretKey = env.getProperty("momo.secretKey");
        String rawSignature = String.format("accessKey=%s&amount=%s&extraData=%s&ipnUrl=%s&orderId=%s&orderInfo=%s&partnerCode=%s&redirectUrl=%s&requestId=%s&requestType=%s",
                accessKey, amount, extraData, ipnUrl, orderId, orderInfo, partnerCode, redirectUrl, requestId, requestType);

        String signature = generateSignature(rawSignature);

        JSONObject data = new JSONObject();
        data.put("partnerCode", partnerCode);
        data.put("partnerName", "Test");
        data.put("storeId", "MomoTestStore");
        data.put("requestId", requestId);
        data.put("amount", amount);
        data.put("orderId", orderId);
        data.put("orderInfo", orderInfo);
        data.put("redirectUrl", redirectUrl);
        data.put("ipnUrl", ipnUrl);
        data.put("lang", "vi");
        data.put("extraData", extraData);
        data.put("requestType", requestType);
        data.put("signature", signature);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(data.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            JSONObject responseBody = new JSONObject(response.getBody());
            return responseBody.optString("payUrl", ""); // Return the payment URL
        } else {
            throw new RuntimeException("Failed to create payment request: " + response.getStatusCode());
        }
    }

    @Override
    public String generateSignature(String rawSignature) throws NoSuchAlgorithmException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        try {
            sha256_HMAC.init(secret_key);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(MomoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        byte[] hashBytes = sha256_HMAC.doFinal(rawSignature.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    public Map<String, String> refundPayment(String transactionId, String amount) throws Exception {
        System.out.println("Processing refund payment");

        this.accessKey = "F8BBA842ECF85";
        this.partnerCode = "MOMO";
        this.endpoint = "https://test-payment.momo.vn/v2/gateway/api/refund";
        this.secretKey = env.getProperty("momo.secretKey");

        // Tạo một orderId mới cho giao dịch hoàn tiền
        String newOrderId = UUID.randomUUID().toString();
        String requestId = UUID.randomUUID().toString();

        // Chuyển đổi transactionId và amount thành kiểu Long
        Long transIdLong;
        Long amountLong;

        try {
            transIdLong = Long.parseLong(transactionId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid transactionId format. It should be a number.", e);
        }

        try {
            amountLong = Long.parseLong(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid amount format. It should be a number.", e);
        }

        // Tạo rawSignature
        String rawSignature = String.format(
                "accessKey=%s&amount=%d&orderId=%s&partnerCode=%s&requestId=%s&transId=%d",
                accessKey, amountLong, newOrderId, partnerCode, requestId, transIdLong
        );

        // In rawSignature ra để kiểm tra
        System.out.println("Raw Signature: " + rawSignature);

        // Tạo chữ ký HMAC SHA256
        String signature = generateSignature(rawSignature);

        // In chữ ký ra để kiểm tra
        System.out.println("Signature: " + signature);

        // Dữ liệu JSON gửi đến MoMo để thực hiện hoàn tiền
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("partnerCode", partnerCode);
        requestData.put("requestId", requestId);
        requestData.put("orderId", newOrderId);
        requestData.put("amount", amountLong); // Gửi amount dưới dạng Long
        requestData.put("transId", transIdLong); // Gửi transId dưới dạng Long
        requestData.put("signature", signature);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestData, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(endpoint, requestEntity, Map.class);
            return response.getBody(); // Trả về phản hồi từ MoMo
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.singletonMap("error", e.getMessage());
        }
    }

    @Override
    public String createHmacSignature(String rawSignature) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        byte[] hashBytes = sha256_HMAC.doFinal(rawSignature.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    private String privateKeyBase64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsvg2E/9yjMsjM7QwctU+\n"
            + "QsNWGXwfBWBhtUZkHzV4FFu7mSLlBtan9d08Tap91nw7CUBIeeT9zMSQjnnKAxvt\n"
            + "H2M688p9R3Rij43G7vVJjNGCjTjaikZ0T1q5IA7YTSyIILUWD3SBs+12QUZD1kg5\n"
            + "3MjtSVjrjTnuBrCMXmtfh1tNw6Z01+D3HJQ5qjcvhydS2ojsgpFiYSBhcrZWa51A\n"
            + "50luD0nsimFRiJALzSsGgXkf4yDBX88BTKimS6vCjOVoYFH3NewQLLd9CFPDlgYk\n"
            + "xGNct91MXRTz8kWgCATF/sm99cRr7guEo7i4W/RWN+vkUJDL9WO8IwJb2PJL2RBr\n"
            + "swIDAQAB";

    // Tạo chuỗi JSON với các tham số cần thiết
    public String createJsonString(String partnerRefId, String amount, String momoTransId) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("partnerCode", "MOMO");
        jsonMap.put("partnerRefId", partnerRefId);
        jsonMap.put("amount", amount);
        jsonMap.put("momoTransId", momoTransId);

        return objectMapper.writeValueAsString(jsonMap);
    }

    // Ký dữ liệu bằng RSA
    public String signData(String data, String privateKeyBase64) throws Exception {
       String cleanedBase64Key = cleanBase64(privateKeyBase64);

        byte[] keyBytes = Base64.getDecoder().decode(cleanedBase64Key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        // Tạo đối tượng Signature và cấu hình với chế độ ký RSA
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);

        // Ký dữ liệu
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        signature.update(dataBytes);
        byte[] signatureBytes = signature.sign();

        // Mã hóa kết quả bằng Base64
        return Base64.getEncoder().encodeToString(signatureBytes);
    }

    private String cleanBase64(String base64String) {
        return base64String.replaceAll("\\s", "");
    }

    // Phương thức hoàn tiền
    public void refundPaymentRSA(String partnerRefId, String amount, String momoTransId) throws Exception {
        // Tạo chuỗi JSON
        String jsonString = createJsonString(partnerRefId, amount, momoTransId);
        System.out.println("JSON String: " + jsonString);

        // Ký dữ liệu
        String signature = signData(jsonString, privateKeyBase64);
        System.out.println("Signature: " + signature);

        // Gửi yêu cầu hoàn tiền
        String endpoint = "https://test-payment.momo.vn/v2/gateway/api/refund";

        // Dữ liệu gửi đến MoMo
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("partnerCode", "MOMO");
        requestData.put("partnerRefId", partnerRefId);
        requestData.put("amount", amount);
        requestData.put("momoTransId", momoTransId);
        requestData.put("signature", signature);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestData, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(endpoint, requestEntity, Map.class);
            System.out.println("Response: " + response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
