/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.repository.impl;

import com.qlbdx.pojo.Chitietkhudo;
import com.qlbdx.pojo.Gia;
import com.qlbdx.pojo.Khudoxe;
import com.qlbdx.pojo.Phuongtien;
import com.qlbdx.repository.ChiTietKhuDoRepository;
import java.util.Date;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author quang
 */
@Repository
@Transactional
public class ChiTietKhuDoRepositoryImpl implements ChiTietKhuDoRepository {

    @Autowired
    private LocalSessionFactoryBean factory; // tic

    @Override
    public Chitietkhudo GetOrCreateCTKD(Long idKhu, Long idGia, Long idPhuongTIen) {
        Session s = factory.getObject().getCurrentSession();

//        String hql = "FROM Chitietkhudo c WHERE c.khuDoId = :idKhu AND c.giaId = :idGia AND c.phuongTienId = :idPhuongTIen";
//
//        // Tạo và thiết lập truy vấn
//        Query query = s.createQuery(hql, Chitietkhudo.class);
//        query.setParameter("idKhu", idKhu);
//        query.setParameter("idGia", idGia);
//        query.setParameter("idPhuongTIen", idPhuongTIen);
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Chitietkhudo> cq = cb.createQuery(Chitietkhudo.class);
        Root<Chitietkhudo> root = cq.from(Chitietkhudo.class);

        Predicate predicate1 = cb.equal(root.get("khuDoId").get("id"), idKhu);
        Predicate predicate2 = cb.equal(root.get("giaId").get("id"), idGia);
        Predicate predicate3 = cb.equal(root.get("phuongTienId").get("id"), idPhuongTIen);

        cq.where(predicate1, predicate2, predicate3);
        Query query = s.createQuery(cq);
        System.out.println("VO ham r");
        Chitietkhudo result = null;
        try {
            result = (Chitietkhudo) query.getSingleResult();
            return result;
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            System.out.println("vo else");
            Khudoxe kdx = s.get(Khudoxe.class, idKhu);
            Gia gia = s.get(Gia.class, idGia.intValue());
            Phuongtien pt = s.get(Phuongtien.class, idPhuongTIen.intValue());
            Chitietkhudo newChitietkhudo = new Chitietkhudo();
            newChitietkhudo.setKhuDoId(kdx);
            newChitietkhudo.setGiaId(gia);
            newChitietkhudo.setPhuongTienId(pt);
            newChitietkhudo.setNgayCapNhat(new Date());
            System.out.println(newChitietkhudo);
            s.save(newChitietkhudo);
            return newChitietkhudo;
        }

    }

}
