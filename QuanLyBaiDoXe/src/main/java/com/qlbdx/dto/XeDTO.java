/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tuanc
 */
public class XeDTO {

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    private Long id;
    private String tenXe;
    private String bienSo;
    private String image;
    private Long userId;
    private MultipartFile file;

    
    // Constructors, Getters, and Setters

    public XeDTO() {
    }

    public XeDTO(Long id, String tenXe, String bienSo, String image, Long userId) {
        this.id = id;
        this.tenXe = tenXe;
        this.bienSo = bienSo;
        this.image = image;
        this.userId = userId;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
