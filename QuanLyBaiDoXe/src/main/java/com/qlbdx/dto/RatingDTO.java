/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.dto;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author tuanc
 */
public class RatingDTO {

    private Long id;
    private Integer baidoxeId;
    private int rate;
    private Date createdDate;
    private String comments;
    private String username;
    private String avatar;

  
    // Constructor, getters, and setters
    public RatingDTO() {
    }

    public RatingDTO(Long id, Integer baidoxeId, int rate, Date createdDate, String comments) {
        this.id = id;
        this.baidoxeId = baidoxeId;
        this.rate = rate;
        this.createdDate = createdDate;
        this.comments = comments;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBaidoxeId() {
        return baidoxeId;
    }

    public void setBaidoxeId(Integer baidoxeId) {
        this.baidoxeId = baidoxeId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}
