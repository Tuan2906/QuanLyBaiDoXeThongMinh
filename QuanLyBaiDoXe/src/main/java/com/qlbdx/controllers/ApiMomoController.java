/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.service.MomoService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author quang
 */
@RestController
@RequestMapping("/api/momo")
public class ApiMomoController {

    @Autowired
    private MomoService momoService;

    @PostMapping("/createPayment")
    public ResponseEntity<?> createPayment(
            @RequestParam("amount") String amount,
            @RequestParam("redirectUrl") String redirectUrl) {
        try {
            return ResponseEntity.ok(momoService.createPaymentRequest(amount, redirectUrl));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/refund")
    public void refundPayment(
            @RequestParam("transactionId") String transactionId,
            @RequestParam("amount") String amount,
            @RequestParam("orderId") String orderId
    ) throws Exception {
        System.out.println(transactionId);
        System.out.println(amount);
         momoService.refundPaymentRSA(orderId,amount,transactionId);
//        if (response != null && "0".equals(response.get("resultCode"))) { // resultCode = "0" indicates success
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.status(500).body(response);
//        }
    }
}
