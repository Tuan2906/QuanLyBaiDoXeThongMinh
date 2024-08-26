/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.dto;

import java.util.Date;

/**
 *
 * @author tuanc
 */
public class LichSuDangKyChoDoDTO {
     private String thoiGianVoBai;
     private Long id;
    private String thoiGianRaBai;
    private String tenXe;
    private String tenBaiXe;
    private String diaChiBaiXe;
    private String khuDoXe;
    private int vitri;
    private Integer idPT;
    private Long id_chodo;
    private Integer id_Bai;
    private Integer id_khu;

    private boolean isHuy;
    /**
     * @return the thoiGianVoBai
     */
    public String getThoiGianVoBai() {
        return thoiGianVoBai;
    }

    /**
     * @return the thoiGianRaBai
     */
    public String getThoiGianRaBai() {
        return thoiGianRaBai;
    }

    /**
     * @return the tenXe
     */
    public String getTenXe() {
        return tenXe;
    }

    /**
     * @return the tenBaiXe
     */
    public String getTenBaiXe() {
        return tenBaiXe;
    }

    /**
     * @return the khuDoXe
     */
    public String getKhuDoXe() {
        return khuDoXe;
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

    /**
     * @param thoiGianVoBai the thoiGianVoBai to set
     */
    public void setThoiGianVoBai(String thoiGianVoBai) {
        this.thoiGianVoBai = thoiGianVoBai;
    }

    /**
     * @param thoiGianRaBai the thoiGianRaBai to set
     */
    public void setThoiGianRaBai(String thoiGianRaBai) {
        this.thoiGianRaBai = thoiGianRaBai;
    }

    /**
     * @param tenXe the tenXe to set
     */
    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    /**
     * @param tenBaiXe the tenBaiXe to set
     */
    public void setTenBaiXe(String tenBaiXe) {
        this.tenBaiXe = tenBaiXe;
    }

    /**
     * @param khuDoXe the khuDoXe to set
     */
    public void setKhuDoXe(String khuDoXe) {
        this.khuDoXe = khuDoXe;
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
     * @return the vitri
     */
    public int getVitri() {
        return vitri;
    }

    /**
     * @param vitri the vitri to set
     */
    public void setVitri(int vitri) {
        this.vitri = vitri;
    }

    /**
     * @return the idPT
     */
    public Integer getIdPT() {
        return idPT;
    }

    /**
     * @param idPT the idPT to set
     */
    public void setIdPT(Integer idPT) {
        this.idPT = idPT;
    }

    /**
     * @return the id_chodo
     */
    public Long getId_chodo() {
        return id_chodo;
    }

    /**
     * @param id_chodo the id_chodo to set
     */
    public void setId_chodo(Long id_chodo) {
        this.id_chodo = id_chodo;
    }

    /**
     * @return the id_Bai
     */
    public Integer getId_Bai() {
        return id_Bai;
    }

    /**
     * @param id_Bai the id_Bai to set
     */
    public void setId_Bai(Integer id_Bai) {
        this.id_Bai = id_Bai;
    }

    /**
     * @return the id_khu
     */
    public Integer getId_khu() {
        return id_khu;
    }

    /**
     * @param id_khu the id_khu to set
     */
    public void setId_khu(Integer id_khu) {
        this.id_khu = id_khu;
    }

    /**
     * @return the diaChiBaiXe
     */
    public String getDiaChiBaiXe() {
        return diaChiBaiXe;
    }

    /**
     * @param diaChiBaiXe the diaChiBaiXe to set
     */
    public void setDiaChiBaiXe(String diaChiBaiXe) {
        this.diaChiBaiXe = diaChiBaiXe;
    }
}
