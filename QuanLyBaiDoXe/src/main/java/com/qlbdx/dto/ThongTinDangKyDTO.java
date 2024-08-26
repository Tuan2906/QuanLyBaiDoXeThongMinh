/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.dto;

/**
 *
 * @author quang
 */
public class ThongTinDangKyDTO {

    private Long id;
    private boolean isHuy;
    private String thoiGianRaBai;
    private String thoiGianVoBai;
    private String thoiGianDangKy;
    private String tenXe;
    private String bienSo;
    private String image;
    private String username;

    public ThongTinDangKyDTO(Long id, boolean isHuy, String thoiGianRaBai, String thoiGianVoBai, String thoiGianDangKy,
            String tenXe, String bienSo, String image, String username) {
        this.id =id;
        this.isHuy=isHuy;
        this.thoiGianRaBai = thoiGianRaBai;
        this.thoiGianVoBai = thoiGianVoBai;
        this.thoiGianDangKy = thoiGianDangKy;
        this.tenXe = tenXe;
        this.bienSo = bienSo;
        this.image = image;
        this.username = username;
    }

    /**
     * @return the thoiGianRaBai
     */
    public String getThoiGianRaBai() {
        return thoiGianRaBai;
    }

    /**
     * @param thoiGianRaBai the thoiGianRaBai to set
     */
    public void setThoiGianRaBai(String thoiGianRaBai) {
        this.thoiGianRaBai = thoiGianRaBai;
    }

    /**
     * @return the thoiGianVoBai
     */
    public String getThoiGianVoBai() {
        return thoiGianVoBai;
    }

    /**
     * @param thoiGianVoBai the thoiGianVoBai to set
     */
    public void setThoiGianVoBai(String thoiGianVoBai) {
        this.thoiGianVoBai = thoiGianVoBai;
    }

    /**
     * @return the thoiGianDangKy
     */
    public String getThoiGianDangKy() {
        return thoiGianDangKy;
    }

    /**
     * @param thoiGianDangKy the thoiGianDangKy to set
     */
    public void setThoiGianDangKy(String thoiGianDangKy) {
        this.thoiGianDangKy = thoiGianDangKy;
    }

    /**
     * @return the tenXe
     */
    public String getTenXe() {
        return tenXe;
    }

    /**
     * @param tenXe the tenXe to set
     */
    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    /**
     * @return the bienSo
     */
    public String getBienSo() {
        return bienSo;
    }

    /**
     * @param bienSo the bienSo to set
     */
    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the isHuy
     */
    public boolean isIsHuy() {
        return isHuy;
    }

    /**
     * @param isHuy the isHuy to set
     */
    public void setIsHuy(boolean isHuy) {
        this.isHuy = isHuy;
    }

}
