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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author quang
 */
@Entity
@Table(name = "gia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gia.findAll", query = "SELECT g FROM Gia g"),
    @NamedQuery(name = "Gia.findById", query = "SELECT g FROM Gia g WHERE g.id = :id"),
    @NamedQuery(name = "Gia.findByGia", query = "SELECT g FROM Gia g WHERE g.gia = :gia")})
public class Gia implements Serializable {

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gia")
    private double gia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "giaId")
    private Set<Chitietkhudo> chitietkhudoSet;

    public Gia() {
    }

    public Gia(Integer id) {
        this.id = id;
    }

    public Gia(Integer id, double gia) {
        this.id = id;
        this.gia = gia;
    }



    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
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
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gia)) {
            return false;
        }
        Gia other = (Gia) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlbdx.pojo.Gia[ id=" + getId() + " ]";
    }
    
}
