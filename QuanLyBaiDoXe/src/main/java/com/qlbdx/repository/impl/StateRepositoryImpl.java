/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.repository.impl;

import com.qlbdx.pojo.State;
import com.qlbdx.repository.StateRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
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
public class StateRepositoryImpl implements StateRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<State> getState() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From State");
        return q.getResultList();
    }

}
