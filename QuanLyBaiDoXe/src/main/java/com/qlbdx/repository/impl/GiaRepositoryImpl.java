/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.repository.impl;

import com.qlbdx.pojo.Gia;
import com.qlbdx.repository.GiaRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
public class GiaRepositoryImpl implements GiaRepository{

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Gia> getGia() {
        Session s = this.factory.getObject().getCurrentSession();

        Query q = s.createQuery("From Gia");
        return q.getResultList();

    }

    @Override
    public Gia getGiaByID(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        return s.get(Gia.class, id);

    }

    public void add_or_update(Gia p) {
        Session s = this.factory.getObject().getCurrentSession();

        Transaction transaction = s.beginTransaction();
        try {
            // Nếu ID không phải null, thực hiện cập nhật
            if (p.getId() != 0) {
                s.update(p);
            } else {
                // Nếu ID là null, thực hiện thêm mới
                s.save(p);
            }
            // Commit giao dịch
            transaction.commit();
        } catch (Exception e) {
            // Nếu có lỗi xảy ra, rollback giao dịch
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

}
