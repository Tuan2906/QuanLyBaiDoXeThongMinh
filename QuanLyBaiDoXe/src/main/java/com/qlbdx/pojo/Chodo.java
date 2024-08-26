/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.pojo;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author quang
 */
@Entity
@Table(name = "chodo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chodo.findAll", query = "SELECT c FROM Chodo c"),
    @NamedQuery(name = "Chodo.findById", query = "SELECT c FROM Chodo c WHERE c.id = :id"),
    @NamedQuery(name = "Chodo.findByVitri", query = "SELECT c FROM Chodo c WHERE c.vitri = :vitri"),
    @NamedQuery(name = "Chodo.findByKhoangCach", query = "SELECT c FROM Chodo c WHERE c.khoangCach = :khoangCach")})
public class Chodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vitri")
    private int vitri;
    @Basic(optional = false)
    @NotNull
    @Column(name = "khoangCach")
    private double khoangCach;
    @OneToMany(mappedBy = "choDoid")
    private Set<Thongtindangky> thongtindangkySet;
    @JoinColumn(name = "baiDoXe_id", referencedColumnName = "id")
    @ManyToOne
    private Baidoxe baiDoXeid;
    @JoinColumn(name = "khuDoXe_id", referencedColumnName = "id")
    @ManyToOne
    private Chitietkhudo khuDoXeid;
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    @ManyToOne
    private State stateId;

    @Transient
    private Long kdxId;
    @Transient
    private Long giaId;
    @Transient
    private Long ptId;

    public Chodo() {
    }

    public Chodo(Long id) {
        this.id = id;
    }

    public Chodo(Long id, int vitri, double khoangCach) {
        this.id = id;
        this.vitri = vitri;
        this.khoangCach = khoangCach;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVitri() {
        return vitri;
    }

    public void setVitri(int vitri) {
        this.vitri = vitri;
    }

    public double getKhoangCach() {
        return khoangCach;
    }

    public void setKhoangCach(double khoangCach) {
        this.khoangCach = khoangCach;
    }

    @XmlTransient
    public Set<Thongtindangky> getThongtindangkySet() {
        return thongtindangkySet;
    }

    public void setThongtindangkySet(Set<Thongtindangky> thongtindangkySet) {
        this.thongtindangkySet = thongtindangkySet;
    }

    public Baidoxe getBaiDoXeid() {
        return baiDoXeid;
    }

    public void setBaiDoXeid(Baidoxe baiDoXeid) {
        this.baiDoXeid = baiDoXeid;
    }

    public Chitietkhudo getKhuDoXeid() {
        return khuDoXeid;
    }

    public void setKhuDoXeid(Chitietkhudo khuDoXeid) {
        this.khuDoXeid = khuDoXeid;
    }

    public State getStateId() {
        return stateId;
    }

    public void setStateId(State stateId) {
        this.stateId = stateId;
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
        if (!(object instanceof Chodo)) {
            return false;
        }
        Chodo other = (Chodo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.qlbdx.pojo.Chodo[ id=" + id + " ]";
    }

    /**
     * @return the kdxId
     */
    public Long getKdxId() {
        return kdxId;
    }

    /**
     * @param kdxId the kdxId to set
     */
    public void setKdxId(Long kdxId) {
        this.kdxId = kdxId;
    }

    /**
     * @return the giaId
     */
    public Long getGiaId() {
        return giaId;
    }

    /**
     * @param giaId the giaId to set
     */
    public void setGiaId(Long giaId) {
        this.giaId = giaId;
    }

    /**
     * @return the ptId
     */
    public Long getPtId() {
        return ptId;
    }

    /**
     * @param ptId the ptId to set
     */
    public void setPtId(Long ptId) {
        this.ptId = ptId;
    }

}
