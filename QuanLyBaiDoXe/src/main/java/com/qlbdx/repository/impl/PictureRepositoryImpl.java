/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.repository.impl;

import com.qlbdx.pojo.Baidoxepic;
import com.qlbdx.repository.PictureRepository;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class PictureRepositoryImpl implements PictureRepository{

    @Autowired
    private LocalSessionFactoryBean factory;

    public List<Baidoxepic> getPic() {
        Session s = this.factory.getObject().getCurrentSession();

        Query q = s.createQuery("From Baidoxepic");
        return q.getResultList();

    }

    public List<Baidoxepic> getPicturesByParkingLot(int baiDoXeId) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Baidoxepic> cq = cb.createQuery(Baidoxepic.class);
        Root<Baidoxepic> root = cq.from(Baidoxepic.class);

        // Thêm điều kiện vào truy vấn
        cq.select(root).where(cb.equal(root.get("baiDoXe"), baiDoXeId));

        return s.createQuery(cq).getResultList();
    }

    public void add_or_update(Baidoxepic p) {
        Session s = this.factory.getObject().getCurrentSession();
        if (p.getId() != null) {
            s.update(p);
        } else {
            s.save(p);
        }

    }
}
