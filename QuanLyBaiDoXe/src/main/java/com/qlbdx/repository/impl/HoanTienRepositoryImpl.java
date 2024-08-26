/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.repository.impl;

import com.qlbdx.pojo.Userhoantien;
import com.qlbdx.repository.HoanTIenRepository;
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
public class HoanTienRepositoryImpl implements HoanTIenRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addHoanTien(Userhoantien uht) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(uht);
    }

}
