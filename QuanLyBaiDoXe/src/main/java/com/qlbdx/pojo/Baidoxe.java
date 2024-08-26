/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tuanc
 */
@Entity
@Table(name = "baidoxe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Baidoxe.findAll", query = "SELECT b FROM Baidoxe b"),
    @NamedQuery(name = "Baidoxe.findById", query = "SELECT b FROM Baidoxe b WHERE b.id = :id"),
    @NamedQuery(name = "Baidoxe.findByDiachi", query = "SELECT b FROM Baidoxe b WHERE b.diachi = :diachi"),
    @NamedQuery(name = "Baidoxe.findByThoigiancua", query = "SELECT b FROM Baidoxe b WHERE b.thoigiancua = :thoigiancua"),
    @NamedQuery(name = "Baidoxe.findByThoigiandongcua", query = "SELECT b FROM Baidoxe b WHERE b.thoigiandongcua = :thoigiandongcua")})
public class Baidoxe implements Serializable {
    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tenBai")
    private String tenBai;

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100, message = "{baiDoXe.diachi.errMsg}")
    @Column(name = "diachi")
    private String diachi;
    

    @Basic(optional = false)
    @NotNull
    @Column(name = "thoigiancua")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoigiancua;
    @Basic(optional = false)
    @NotNull
    @Column(name = "thoigiandongcua")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoigiandongcua;
     
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baiDoXeid")
    private Set<Baidoxepic> baidoxepicSet;
     
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baiDoid")
    private Set<Rating> ratingSet;
     
    @OneToMany(mappedBy = "baiDoXeid")
    private Set<Chodo> chodoSet;
    @Transient
    private MultipartFile[] files;

    public Baidoxe() {
    }

    public Baidoxe(Integer id) {
        this.id = id;
    }

    public Baidoxe(int id, String diachi, Date thoigiancua, Date thoigiandongcua) {
        this.id = id;
        this.diachi = diachi;
        this.thoigiancua = thoigiancua;
        this.thoigiandongcua = thoigiandongcua;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Date getThoigiancua() {
        return thoigiancua;
    }

    public void setThoigiancua(Date thoigiancua) {
        this.thoigiancua = thoigiancua;
    }

    public Date getThoigiandongcua() {
        return thoigiandongcua;
    }

    public void setThoigiandongcua(Date thoigiandongcua) {
        this.thoigiandongcua = thoigiandongcua;
    }

    @XmlTransient
    public Set<Baidoxepic> getBaidoxepicSet() {
        return baidoxepicSet;
    }

    public void setBaidoxepicSet(Set<Baidoxepic> baidoxepicSet) {
        this.baidoxepicSet = baidoxepicSet;
    }

    @XmlTransient
    public Set<Rating> getRatingSet() {
        return ratingSet;
    }

    public void setRatingSet(Set<Rating> ratingSet) {
        this.ratingSet = ratingSet;
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
        if (!(object instanceof Baidoxe)) {
            return false;
        }
        Baidoxe other = (Baidoxe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlbdx.pojo.Baidoxe[ id=" + id + " ]";
    }

    /**
     * @return the files
     */
    public MultipartFile[] getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public String getTenBai() {
        return tenBai;
    }

    public void setTenBai(String tenBai) {
        this.tenBai = tenBai;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

}
