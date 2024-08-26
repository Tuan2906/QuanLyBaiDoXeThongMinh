/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.dto;

import java.util.List;

/**
 *
 * @author quang
 */
public class KhuDoXeDTO {

    /**
     * @return the pics
     */
    public List<BaiDoPic_DTO> getPics() {
        return pics;
    }

    /**
     * @param pics the pics to set
     */
    public void setPics(List<BaiDoPic_DTO> pics) {
        this.pics = pics;
    }

    private Long idBai;
    private Long idChiTietKhu;
    private String  tenDay;
    private Double gia;
    private String phuongtien;
    private List<ChoDoDTO> chodo;
    private List<BaiDoPic_DTO> pics;

    /**
     * @param idBai
     * @param idKhu
     * @param tenDay
     * @param gia
     * @param phuongtien
     */
    public KhuDoXeDTO (Long idBai,Long idKhu,String  tenDay, Double gia,String phuongtien){
        this.idBai = idBai;
        this.idChiTietKhu = idKhu;
        this.tenDay =tenDay;
        this.gia = gia;
        this.phuongtien = phuongtien;
    }
    
    public KhuDoXeDTO (Long idBai,Long idKhu,String  tenDay, Double gia,String phuongtien, List<ChoDoDTO> chodo, List<BaiDoPic_DTO> pics){
        this.idBai = idBai;
        this.idChiTietKhu = idKhu;
        this.tenDay =tenDay;
        this.gia = gia;
        this.phuongtien = phuongtien;
        this.chodo = chodo;
        this.pics = pics;
    }
    
    public Long getIdBai() {
        return idBai;
    }

    /**
     * @param idBai the idBai to set
     */
    public void setIdBai(Long idBai) {
        this.idBai = idBai;
    }

    /**
     * @return the idKhu
     */
    public Long getIdKhu() {
        return idChiTietKhu;
    }

    /**
     * @param idKhu the idKhu to set
     */
    public void setIdKhu(Long idKhu) {
        this.idChiTietKhu = idKhu;
    }

    /**
     * @return the tenDay
     */
    public String getTenDay() {
        return tenDay;
    }

    /**
     * @param tenDay the tenDay to set
     */
    public void setTenDay(String tenDay) {
        this.tenDay = tenDay;
    }

    /**
     * @return the gia
     */
    public Double getGia() {
        return gia;
    }

    /**
     * @param gia the gia to set
     */
    public void setGia(Double gia) {
        this.gia = gia;
    }

    /**
     * @return the phuongtien
     */
    public String getPhuongtien() {
        return phuongtien;
    }

    /**
     * @param phuongtien the phuongtien to set
     */
    public void setPhuongtien(String phuongtien) {
        this.phuongtien = phuongtien;
    }

    /**
     * @return the chodo
     */
    public List<ChoDoDTO> getChodo() {
        return chodo;
    }

    /**
     * @param chodo the chodo to set
     */
    public void setChodo(List<ChoDoDTO> chodo) {
        this.chodo = chodo;
    }

}
