/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.pojo;

import com.qlbdx.dto.XeDTO;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author quang
 */
@Entity
@Table(name = "xe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Xe.findAll", query = "SELECT x FROM Xe x"),
    @NamedQuery(name = "Xe.findById", query = "SELECT x FROM Xe x WHERE x.id = :id"),
    @NamedQuery(name = "Xe.findByTenXe", query = "SELECT x FROM Xe x WHERE x.tenXe = :tenXe"),
    @NamedQuery(name = "Xe.findByBienSo", query = "SELECT x FROM Xe x WHERE x.bienSo = :bienSo"),
    @NamedQuery(name = "Xe.findByImage", query = "SELECT x FROM Xe x WHERE x.image = :image")})
public class Xe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tenXe")
    private String tenXe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "bienSo")
    private String bienSo;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "xeId")
    private Set<Thongtindangky> thongtindangkySet;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Xe() {
    }

    public Xe(Long id) {
        this.id = id;
    }

    public Xe(Long id, String tenXe, String bienSo, String image, User user) {
        this.id = id;
        this.tenXe = tenXe;
        this.bienSo = bienSo;
        this.image = image;
        this.userId = user;
    }

    public Xe(XeDTO xeDTO, User user) {
        this.tenXe = xeDTO.getTenXe();
        this.bienSo = xeDTO.getBienSo();
        this.image = xeDTO.getImage();
        this.userId = user != null ? user : new User(); // Ensure userId is not null
    }
    
    public Xe(Long id, String tenXe, String bienSo) {
        this.id = id;
        this.tenXe = tenXe;
        this.bienSo = bienSo;
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

    @XmlTransient
    public Set<Thongtindangky> getThongtindangkySet() {
        return thongtindangkySet;
    }

    public void setThongtindangkySet(Set<Thongtindangky> thongtindangkySet) {
        this.thongtindangkySet = thongtindangkySet;
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
        if (!(object instanceof Xe)) {
            return false;
        }
        Xe other = (Xe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlbdx.pojo.Xe[ id=" + id + " ]";
    }

}
