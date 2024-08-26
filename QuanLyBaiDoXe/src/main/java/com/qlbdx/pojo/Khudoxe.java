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
@Table(name = "khudoxe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Khudoxe.findAll", query = "SELECT k FROM Khudoxe k"),
    @NamedQuery(name = "Khudoxe.findById", query = "SELECT k FROM Khudoxe k WHERE k.id = :id"),
    @NamedQuery(name = "Khudoxe.findByTenDay", query = "SELECT k FROM Khudoxe k WHERE k.tenDay = :tenDay")})
public class Khudoxe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tenDay")
    private String tenDay;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "khuDoId")
    private Set<Chitietkhudo> chitietkhudoSet;

    public Khudoxe() {
    }

    public Khudoxe(Long id) {
        this.id = id;
    }

    public Khudoxe(Long id, String tenDay) {
        this.id = id;
        this.tenDay = tenDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenDay() {
        return tenDay;
    }

    public void setTenDay(String tenDay) {
        this.tenDay = tenDay;
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
        if (!(object instanceof Khudoxe)) {
            return false;
        }
        Khudoxe other = (Khudoxe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlbdx.pojo.Khudoxe[ id=" + id + " ]";
    }
    
}
