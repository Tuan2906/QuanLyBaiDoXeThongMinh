/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.pojo;

import java.io.Serializable;
import java.util.Objects;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tuanc
 */
@Entity
@Table(name = "baidoxepic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Baidoxepic.findAll", query = "SELECT b FROM Baidoxepic b"),
    @NamedQuery(name = "Baidoxepic.findById", query = "SELECT b FROM Baidoxepic b WHERE b.id = :id"),
    @NamedQuery(name = "Baidoxepic.findByImage", query = "SELECT b FROM Baidoxepic b WHERE b.image = :image")})
public class Baidoxepic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "baiDoXe_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Baidoxe baiDoXeid;

    public Baidoxepic() {
    }

    public Baidoxepic(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Baidoxe getBaiDoXeid() {
        return baiDoXeid;
    }

    public void setBaiDoXeid(Baidoxe baiDoXeid) {
        this.baiDoXeid = baiDoXeid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image); // Bao gồm tất cả các thuộc tính cần thiết
    }

     @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Baidoxepic other = (Baidoxepic) obj;
        return Objects.equals(id, other.id) &&
               Objects.equals(image, other.image);
    }
    @Transient
    private MultipartFile file;
    
    @Override
    public String toString() {
        return "com.qlbdx.pojo.Baidoxepic[ id=" + id + " ]";
    }
     /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }
    
    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}