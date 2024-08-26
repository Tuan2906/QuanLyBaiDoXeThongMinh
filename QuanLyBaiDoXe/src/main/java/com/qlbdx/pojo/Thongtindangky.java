/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author quang
 */
@Entity
@Table(name = "thongtindangky")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Thongtindangky.findAll", query = "SELECT t FROM Thongtindangky t"),
    @NamedQuery(name = "Thongtindangky.findById", query = "SELECT t FROM Thongtindangky t WHERE t.id = :id"),
    @NamedQuery(name = "Thongtindangky.findByThoiGianVoBai", query = "SELECT t FROM Thongtindangky t WHERE t.thoiGianVoBai = :thoiGianVoBai"),
    @NamedQuery(name = "Thongtindangky.findByThoiGianRaBai", query = "SELECT t FROM Thongtindangky t WHERE t.thoiGianRaBai = :thoiGianRaBai"),
    @NamedQuery(name = "Thongtindangky.findByThoiGianDangKy", query = "SELECT t FROM Thongtindangky t WHERE t.thoiGianDangKy = :thoiGianDangKy"),
    @NamedQuery(name = "Thongtindangky.findByIsHuy", query = "SELECT t FROM Thongtindangky t WHERE t.isHuy = :isHuy")})
public class Thongtindangky implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "thoiGianVoBai")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianVoBai;
    @Basic(optional = false)
    @NotNull
    @Column(name = "thoiGianRaBai")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianRaBai;
    @Basic(optional = false)
    @NotNull
    @Column(name = "thoiGianDangKy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianDangKy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isHuy")
    private boolean isHuy;
    @JoinColumn(name = "choDo_id", referencedColumnName = "id")
    @ManyToOne
    private Chodo choDoid;
    @JoinColumn(name = "xe_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Xe xeId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userId")
    private Hoadon hoadon;

    public Thongtindangky() {
    }

    public Thongtindangky(Long id) {
        this.id = id;
    }

    public Thongtindangky(Long id, Date thoiGianVoBai, Date thoiGianRaBai, Date thoiGianDangKy, boolean isHuy) {
        this.id = id;
        this.thoiGianVoBai = thoiGianVoBai;
        this.thoiGianRaBai = thoiGianRaBai;
        this.thoiGianDangKy = thoiGianDangKy;
        this.isHuy = isHuy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getThoiGianVoBai() {
        return thoiGianVoBai;
    }

    public void setThoiGianVoBai(Date thoiGianVoBai) {
        this.thoiGianVoBai = thoiGianVoBai;
    }

    public Date getThoiGianRaBai() {
        return thoiGianRaBai;
    }

    public void setThoiGianRaBai(Date thoiGianRaBai) {
        this.thoiGianRaBai = thoiGianRaBai;
    }

    public Date getThoiGianDangKy() {
        return thoiGianDangKy;
    }

    public void setThoiGianDangKy(Date thoiGianDangKy) {
        this.thoiGianDangKy = thoiGianDangKy;
    }

    public boolean getIsHuy() {
        return isHuy;
    }

    public void setIsHuy(boolean isHuy) {
        this.isHuy = isHuy;
    }

    public Chodo getChoDoid() {
        return choDoid;
    }

    public void setChoDoid(Chodo choDoid) {
        this.choDoid = choDoid;
    }

    public Xe getXeId() {
        return xeId;
    }

    public void setXeId(Xe xeId) {
        this.xeId = xeId;
    }

    public Hoadon getHoadon() {
        return hoadon;
    }

    public void setHoadon(Hoadon hoadon) {
        this.hoadon = hoadon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Thongtindangky)) {
            return false;
        }
        Thongtindangky other = (Thongtindangky) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlbdx.pojo.Thongtindangky[ id=" + id + " ]";
    }
    
}
