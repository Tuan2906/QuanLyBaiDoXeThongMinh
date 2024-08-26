/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * @author quang
 */
public class ChodoMany {

    private List<Integer> vitri;        // Đổi kiểu từ List sang mảng
    private List<Double> khoangCach;    // Đổi kiểu từ List sang mảng
    private Long kdxId;
    private Long giaId;
    private Long ptId;
    private String baiDoXeid;
    private Integer stateId;

    public ChodoMany() {
    }

   
    

    /**
     * @return the kdxId
     */
    public Long getKdxId() {
        return kdxId;
    }

    /**
     * @param kdxId the kdxId to set
     */
    public void setKdxId(Long kdxId) {
        this.kdxId = kdxId;
    }

    /**
     * @return the giaId
     */
    public Long getGiaId() {
        return giaId;
    }

    /**
     * @param giaId the giaId to set
     */
    public void setGiaId(Long giaId) {
        this.giaId = giaId;
    }

    /**
     * @return the ptId
     */
    public Long getPtId() {
        return ptId;
    }

    /**
     * @param ptId the ptId to set
     */
    public void setPtId(Long ptId) {
        this.ptId = ptId;
    }

    /**
     * @return the baiDoXeid
     */
    public String getBaiDoXeid() {
        return baiDoXeid;
    }

    /**
     * @param baiDoXeid the baiDoXeid to set
     */
    public void setBaiDoXeid(String baiDoXeid) {
        this.baiDoXeid = baiDoXeid;
    }

    /**
     * @return the stateId
     */
    public Integer getStateId() {
        return stateId;
    }

    /**
     * @param stateId the stateId to set
     */
    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    /**
     * @return the vitri
     */
    public List<Integer> getVitri() {
        return vitri;
    }

    /**
     * @param vitri the vitri to set
     */
    public void setVitri(List<Integer> vitri) {
        this.vitri = vitri;
    }

    /**
     * @return the khoangCach
     */
    public List<Double> getKhoangCach() {
        return khoangCach;
    }

    /**
     * @param khoangCach the khoangCach to set
     */
    public void setKhoangCach(List<Double> khoangCach) {
        this.khoangCach = khoangCach;
    }

    
}
