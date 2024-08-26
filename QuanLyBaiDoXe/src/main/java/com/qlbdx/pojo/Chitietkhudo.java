/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author quang
 */
@Entity
@Table(name = "chitietkhudo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chitietkhudo.findAll", query = "SELECT c FROM Chitietkhudo c"),
    @NamedQuery(name = "Chitietkhudo.findById", query = "SELECT c FROM Chitietkhudo c WHERE c.id = :id"),
    @NamedQuery(name = "Chitietkhudo.findByNgayCapNhat", query = "SELECT c FROM Chitietkhudo c WHERE c.ngayCapNhat = :ngayCapNhat"),
    @NamedQuery(name = "Chitietkhudo.findByGhiChu", query = "SELECT c FROM Chitietkhudo c WHERE c.ghiChu = :ghiChu")})
public class Chitietkhudo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngayCapNhat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCapNhat;
    @Size(max = 255)
    @Column(name = "ghiChu")
    private String ghiChu;
    @JoinColumn(name = "giaId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Gia giaId;
    @JoinColumn(name = "khuDoId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Khudoxe khuDoId;
    @JoinColumn(name = "phuongTienId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Phuongtien phuongTienId;
    @OneToMany(mappedBy = "khuDoXeid")
    private Set<Chodo> chodoSet;

    public Chitietkhudo() {
    }

    public Chitietkhudo(Integer id) {
        this.id = id;
    }

    public Chitietkhudo(Integer id, Date ngayCapNhat) {
        this.id = id;
        this.ngayCapNhat = ngayCapNhat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Gia getGiaId() {
        return giaId;
    }

    public void setGiaId(Gia giaId) {
        this.giaId = giaId;
    }

    public Khudoxe getKhuDoId() {
        return khuDoId;
    }

    public void setKhuDoId(Khudoxe khuDoId) {
        this.khuDoId = khuDoId;
    }

    public Phuongtien getPhuongTienId() {
        return phuongTienId;
    }

    public void setPhuongTienId(Phuongtien phuongTienId) {
        this.phuongTienId = phuongTienId;
    }

    @XmlTransient
    public Set<Chodo> getChodoSet() {
        return chodoSet;
    }

    public void setChodoSet(Set<Chodo> chodoSet) {
        this.chodoSet = chodoSet;
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
        if (!(object instanceof Chitietkhudo)) {
            return false;
        }
        Chitietkhudo other = (Chitietkhudo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlbdx.pojo.Chitietkhudo[ id=" + id + " ]";
    }
    
}
