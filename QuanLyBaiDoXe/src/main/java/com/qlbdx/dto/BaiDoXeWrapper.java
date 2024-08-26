/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.dto;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Chitietkhudo;
import com.qlbdx.pojo.Khudoxe;
import java.util.List;

/**
 *
 * @author tuanc
 */
public class BaiDoXeWrapper {
      private Baidoxe baidoxe;
    private List<Chitietkhudo> khuDoXeList;

    /**
     * @return the baidoxe
     */
    public Baidoxe getBaidoxe() {
        return baidoxe;
    }

    /**
     * @param baidoxe the baidoxe to set
     */
    public void setBaidoxe(Baidoxe baidoxe) {
        this.baidoxe = baidoxe;
    }

    /**
     * @return the khuDoXeList
     */
    public List<Chitietkhudo> getKhuDoXeList() {
        return khuDoXeList;
    }

    /**
     * @param khuDoXeList the khuDoXeList to set
     */
    public void setKhuDoXeList(List<Chitietkhudo> khuDoXeList) {
        this.khuDoXeList = khuDoXeList;
    }


 

  

}
