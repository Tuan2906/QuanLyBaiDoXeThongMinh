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
public class BaiDoPic_DTO {
    private Long id;
    private String namePic;
    public BaiDoPic_DTO (Long id, String name){
        this.id = id;
        this.namePic = name;
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
     * @return the namePic
     */
    public String getNamePic() {
        return namePic;
    }

    /**
     * @param namePic the namePic to set
     */
    public void setNamePic(String namePic) {
        this.namePic = namePic;
    }
    
}
