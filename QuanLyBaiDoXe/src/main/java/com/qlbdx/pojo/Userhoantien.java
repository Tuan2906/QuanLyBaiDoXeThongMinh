/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.pojo;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "userhoantien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userhoantien.findAll", query = "SELECT u FROM Userhoantien u"),
    @NamedQuery(name = "Userhoantien.findById", query = "SELECT u FROM Userhoantien u WHERE u.id = :id"),
    @NamedQuery(name = "Userhoantien.findByNgayHoan", query = "SELECT u FROM Userhoantien u WHERE u.ngayHoan = :ngayHoan")})
public class Userhoantien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngayHoan")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayHoan;
    @JoinColumn(name = "hoaDon_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hoadon hoaDonid;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Userhoantien() {
    }

    public Userhoantien(Long id) {
        this.id = id;
    }

    public Userhoantien(Long id, Date ngayHoan) {
        this.id = id;
        this.ngayHoan = ngayHoan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNgayHoan() {
        return ngayHoan;
    }

    public void setNgayHoan(Date ngayHoan) {
        this.ngayHoan = ngayHoan;
    }

    public Hoadon getHoaDonid() {
        return hoaDonid;
    }

    public void setHoaDonid(Hoadon hoaDonid) {
        this.hoaDonid = hoaDonid;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof Userhoantien)) {
            return false;
        }
        Userhoantien other = (Userhoantien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlbdx.pojo.Userhoantien[ id=" + id + " ]";
    }
    
}
