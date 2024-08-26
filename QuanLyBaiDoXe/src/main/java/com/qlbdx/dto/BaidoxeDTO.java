/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.dto;

import java.util.Date;

/**
 *
 * @author quang
 */
public class BaidoxeDTO {

    private String tenBai;
    private Integer id;
    private String diachi;
    private String thoigiancua;
    private String thoigiandongcua;
    private String picture;
    private Double avgRate;

    public BaidoxeDTO(String tenBai, Integer id, String diachi, String thoigiancua, String thoigiandongcua) {
        this.tenBai = tenBai;
        this.id = id;
        this.diachi = diachi;
        this.thoigiancua = thoigiancua;
        this.thoigiandongcua = thoigiandongcua;
    }

    public BaidoxeDTO(String tenBai, Integer id, String diachi,
            String thoigiancua, String thoigiandongcua, String pic, Double avgRate) {
        this.tenBai = tenBai;
        this.id = id;
        this.diachi = diachi;
        this.thoigiancua = thoigiancua;
        this.thoigiandongcua = thoigiandongcua;
        this.picture= pic;
        this.avgRate = avgRate;
    }

    /**
     * @return the tenBai
     */
    public String getTenBai() {
        return tenBai;
    }

    /**
     * @param tenBai the tenBai to set
     */
    public void setTenBai(String tenBai) {
        this.tenBai = tenBai;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the diachi
     */
    public String getDiachi() {
        return diachi;
    }

    /**
     * @param diachi the diachi to set
     */
    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    /**
     * @return the thoigiancua
     */
    public String getThoigiancua() {
        return thoigiancua;
    }

    /**
     * @param thoigiancua the thoigiancua to set
     */
    public void setThoigiancua(String thoigiancua) {
        this.thoigiancua = thoigiancua;
    }

    /**
     * @return the thoigiandongcua
     */
    public String getThoigiandongcua() {
        return thoigiandongcua;
    }

    /**
     * @param thoigiandongcua the thoigiandongcua to set
     */
    public void setThoigiandongcua(String thoigiandongcua) {
        this.thoigiandongcua = thoigiandongcua;
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @return the avgRate
     */
    public Double getAvgRate() {
        return avgRate;
    }

    /**
     * @param avgRate the avgRate to set
     */
    public void setAvgRate(Double avgRate) {
        this.avgRate = avgRate;
    }

}
