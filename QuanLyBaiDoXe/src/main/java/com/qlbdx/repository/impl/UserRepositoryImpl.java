/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.repository.impl;

import com.qlbdx.pojo.User;
import com.qlbdx.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@PropertySource("classpath:messages.properties")
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    private static final int PAGE_SIZE = 4;
    @Autowired
    private Environment env;
    @Autowired
    private LocalSessionFactoryBean factory; // tic
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public Map<String, Object> getUsers(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<User> countRoot = countQuery.from(User.class); // Tạo Root cho truy vấn đếm
        countQuery.select(cb.count(countRoot));
        long totalUsers = s.createQuery(countQuery).getSingleResult();
        // Truy vấn để lấy danh sách người dùng

        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class); // Tạo Root cho truy vấn danh sách
        cq.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            String kw = params.get("q");
            String qEmail = params.get("mail");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(cb.like(root.get("username"), "%" + kw + "%"));
            }
            if (qEmail != null && !qEmail.isEmpty()) {
                predicates.add(cb.like(root.get("email"), "%" + qEmail + "%"));
            }
        }
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
            totalUsers = 1;
        }
        // Truy vấn để đếm số lượng người dùng

        // Lấy danh sách người dùng với phân trang
        Query q = s.createQuery(cq);
        String page = params.get("page");
        int start = 0;
        if (page != null && !page.isEmpty()) {
            int p = Integer.parseInt(page);
            start = (p - 1) * PAGE_SIZE;
        }
        if (totalUsers == 1) {
            q.getResultList();
        } else {
            q.setMaxResults(PAGE_SIZE);

        }
        q.setFirstResult(start);

        List<User> users = q.getResultList();

        // Tạo Map để trả về danh sách người dùng và tổng số lượng
        Map<String, Object> result = new HashMap<>();
        result.put("totalUsers", (int) Math.ceil((double) totalUsers / PAGE_SIZE));
        result.put("users", users);

        return result;
    }

    @Override
    public void addorUpdateUser(User u) {
        Session s = factory.getObject().getCurrentSession();

        if (u.getId() != null) {
            s.update(u);
        } else {
            s.save(u);
        }
    }

    @Override
    public User getUserByUsername(String name) {
        Session s = factory.getObject().getCurrentSession();
        Query user = s.createNamedQuery("User.findByUsername", User.class);
        user.setParameter("username", name);
        return (User) user.getSingleResult();
    }

    @Override
    public void deleteUser(User u) {

        Session s = factory.getObject().getCurrentSession();
        User a = s.get(User.class, u.getId());
        System.out.println("11111111111111111");
        a.getXeSet().forEach(action -> System.out.println(action.getTenXe()));
        if (!a.getXeSet().isEmpty()) {
            throw new RuntimeException("Không thể xóa vì có xe liên quan.");
        }
        if (!a.getBaidoxeSet().isEmpty()) {
            throw new RuntimeException("Không thể xóa vì có bãi xe liên quan.");
        }
        s.delete(a);

    }

    @Override
    public User getUserById(long id) {
        Session s = factory.getObject().getCurrentSession();
        return s.get(User.class, id);
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);

        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public User addUser(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        if (u.getPhone().length() < 10 || u.getPhone().length() > 11) {
            throw new RuntimeException(env.getProperty("user.phone.sizeMsg"));
        }
        if (!u.getEmail().contains("@")) {
            throw new RuntimeException(env.getProperty("user.email.ValidFormMsg"));
        }
        Query user = s.createNamedQuery("User.findByPhone", User.class);
        user.setParameter("phone", u.getPhone());
        if (!user.getResultList().isEmpty()) {
            throw new RuntimeException(env.getProperty("user.phone.uniqueMsg"));
        }
        user = s.createNamedQuery("User.findByEmail", User.class);
        user.setParameter("email", u.getEmail());
        if (!user.getResultList().isEmpty()) {
            throw new RuntimeException(env.getProperty("user.email.uniqueMsg"));
        }
        user = s.createNamedQuery("User.findByUsername", User.class);
        user.setParameter("username", u.getUsername());
        if (!user.getResultList().isEmpty()) {
            throw new RuntimeException(env.getProperty("user.username.uniqueMsg"));
        }
        s.save(u);
        return u;
    }

    @Override
    public User updateUser(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        if (u.getPhone().length() < 10 || u.getPhone().length() > 11) {
            throw new RuntimeException(env.getProperty("user.phone.sizeMsg"));
        }
        if (!u.getEmail().contains("@")) {
            throw new RuntimeException(env.getProperty("user.email.ValidFormMsg"));
        }
        Query user = s.createNamedQuery("User.findByPhoneExceptID", User.class);
        user.setParameter("phone", u.getPhone());
        user.setParameter("id", u.getId()); // Thêm tham số id
        if (!user.getResultList().isEmpty()) {
            throw new RuntimeException(env.getProperty("user.phone.uniqueMsg"));
        }
        user = s.createNamedQuery("User.findByEmailExceptID", User.class);
        user.setParameter("email", u.getEmail());
        user.setParameter("id", u.getId()); // Thêm tham số id
        if (!user.getResultList().isEmpty()) {
            throw new RuntimeException(env.getProperty("user.email.uniqueMsg"));
        }
        user = s.createNamedQuery("User.findByUsernameExceptID", User.class);
        user.setParameter("username", u.getUsername());
        user.setParameter("id", u.getId()); // Thêm tham số id
        if (!user.getResultList().isEmpty()) {
            throw new RuntimeException(env.getProperty("user.username.uniqueMsg"));
        }
        s.update(u);
        return u;
    }

}
