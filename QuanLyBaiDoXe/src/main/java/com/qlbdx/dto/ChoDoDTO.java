/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.dto;

/**
 *
 * @author quang
 */
public class ChoDoDTO {
        private Long id;
        private Integer vitri;
        private Double khoangCach;
        private String trangthai;

        public ChoDoDTO (Long id,Integer vitri,Double khoangCach,String trangthai){
            this.id = id;
            this.vitri = vitri;
            this.khoangCach=khoangCach;
            this.trangthai = trangthai;
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
    public Integer getVitri() {
        return vitri;
    }

    /**
     * @param vitri the vitri to set
     */
    public void setVitri(Integer vitri) {
        this.vitri = vitri;
    }

    /**
     * @return the khoangCach
     */
    public Double getKhoangCach() {
        return khoangCach;
    }

    /**
     * @param khoangCach the khoangCach to set
     */
    public void setKhoangCach(Double khoangCach) {
        this.khoangCach = khoangCach;
    }

    /**
     * @return the trangthai
     */
    public String getTrangthai() {
        return trangthai;
    }

    /**
     * @param trangthai the trangthai to set
     */
    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
