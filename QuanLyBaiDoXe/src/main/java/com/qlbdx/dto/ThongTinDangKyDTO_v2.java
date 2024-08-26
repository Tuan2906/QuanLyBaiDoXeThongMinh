/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author tuanc
 */
public class ThongTinDangKyDTO_v2 {

    /**
     * @return the thoiGianDangKy
     */
    public Date getThoiGianDangKy() {
        return thoiGianDangKy;
    }

    /**
     * @param thoiGianDangKy the thoiGianDangKy to set
     */
    public void setThoiGianDangKy(Date thoiGianDangKy) {
        this.thoiGianDangKy = thoiGianDangKy;
    }

    private Long id;
    private Long xeId;
    private Long hoadonId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date thoiGianVoBai;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date thoiGianRaBai;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date thoiGianDangKy;
    private Long choDoId;
    private double soTien;
    private String uid;
    private boolean isHuy;
    // Chuyển đổi dấu thời gian thành đối tượng Date

    public ThongTinDangKyDTO_v2() {
        this.thoiGianDangKy = new Date(); // Thiết lập ngày hiện tại
    }

    public ThongTinDangKyDTO_v2(Long id, Date thoiGianVoBai, Date thoiGianRaBai, Date thoiGianDangKy, boolean isHuy) {
        this.id = id;
        this.thoiGianVoBai = thoiGianVoBai;
        this.thoiGianRaBai = thoiGianRaBai;
        this.thoiGianDangKy = thoiGianDangKy != null ? thoiGianDangKy : new Date(); // Thiết lập ngày hiện tại nếu giá trị null
        this.isHuy = isHuy;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getXeId() {
        return xeId;
    }

    public void setXeId(Long xeId) {
        this.xeId = xeId;
    }

    public Long getHoadonId() {
        return hoadonId;
    }

    public void setHoadonId(Long hoadonId) {
        this.hoadonId = hoadonId;
    }

    public Date getThoiGianVoBai() {
        return thoiGianVoBai;
    }

    public void setThoiGianVoBai(Date thoiGianVoBai) {
        this.thoiGianVoBai = thoiGianVoBai;
    }

    public Date getThoiGianRaBai() {
        return thoiGianRaBai;
    }

    public void setThoiGianRaBai(Date thoiGianRaBai) {
        this.thoiGianRaBai = thoiGianRaBai;
    }

    public boolean isHuy() {
        return isIsHuy();
    }

    public void setHuy(boolean isHuy) {
        this.isHuy = isHuy;
    }

    /**
     * @return the choDoId
     */
    public Long getChoDoId() {
        return choDoId;
    }

    /**
     * @return the soTien
     */
    public double getSoTien() {
        return soTien;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @return the isHuy
     */
    public boolean isIsHuy() {
        return isHuy;
    }

}
