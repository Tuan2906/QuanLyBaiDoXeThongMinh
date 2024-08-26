/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 *
 * @author quang
 */
public interface MomoService {

    String createPaymentRequest(String amount, String redirect_Url) throws Exception;

    String generateSignature(String rawSignature) throws NoSuchAlgorithmException;

    Map<String, String> refundPayment(String transactionId, String amount) throws Exception;

    String createHmacSignature(String data) throws Exception;

    public String signData(String data, String privateKeyBase64) throws Exception;

    String createJsonString(String partnerRefId, String amount, String momoTransId) throws Exception;

    public void refundPaymentRSA(String partnerRefId, String amount, String momoTransId) throws Exception;
}
