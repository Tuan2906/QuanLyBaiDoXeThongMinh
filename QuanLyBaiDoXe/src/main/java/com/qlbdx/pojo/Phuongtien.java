/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author quang
 */
@Entity
@Table(name = "phuongtien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Phuongtien.findAll", query = "SELECT p FROM Phuongtien p"),
    @NamedQuery(name = "Phuongtien.findById", query = "SELECT p FROM Phuongtien p WHERE p.id = :id"),
    @NamedQuery(name = "Phuongtien.findByLoai", query = "SELECT p FROM Phuongtien p WHERE p.loai = :loai")})
public class Phuongtien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "loai")
    private String loai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phuongTienId")
    private Set<Chitietkhudo> chitietkhudoSet;

    public Phuongtien() {
    }

    public Phuongtien(Integer id) {
        this.id = id;
    }

    public Phuongtien(Integer id, String loai) {
        this.id = id;
        this.loai = loai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    @XmlTransient
    public Set<Chitietkhudo> getChitietkhudoSet() {
        return chitietkhudoSet;
    }

    public void setChitietkhudoSet(Set<Chitietkhudo> chitietkhudoSet) {
        this.chitietkhudoSet = chitietkhudoSet;
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
        if (!(object instanceof Phuongtien)) {
            return false;
        }
        Phuongtien other = (Phuongtien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlbdx.pojo.Phuongtien[ id=" + id + " ]";
    }
    
}
