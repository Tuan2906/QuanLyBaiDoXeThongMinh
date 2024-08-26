/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.repository.impl;

import com.qlbdx.pojo.Chitietkhudo;
import com.qlbdx.pojo.Khudoxe;
import com.qlbdx.repository.ChiTietKhuDoXeRepository;
import com.qlbdx.repository.KhuDoXeRepository;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class ChiTietKhuDoXeRepositoryImpl implements ChiTietKhuDoXeRepository {

    private static final int PAGE_SIZE = 4;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public List<Chitietkhudo> getChiTietKhuDoXe(Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Chitietkhudo> cq = cb.createQuery(Chitietkhudo.class);
        Root<Chitietkhudo> root = cq.from(Chitietkhudo.class);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            // Lọc theo tên khu vực
            String tenDay = params.get("ghiChu");
            if (tenDay != null && !tenDay.isEmpty()) {
                Predicate p1 = cb.like(root.get("ghiChu"), String.format("%%%s%%", tenDay));
                predicates.add(p1);
            }

            // Thêm các Predicate vào CriteriaQuery
            if (!predicates.isEmpty()) {
                cq.where(predicates.toArray(new Predicate[0]));
            }

            // Sắp xếp theo ID hoặc tên khu vực nếu được yêu cầu
            String sort = params.get("sort");
            if (sort != null && !sort.isEmpty()) {
                if (sort.equals("id")) {
                    cq.orderBy(cb.desc(root.get("id")));
                } else if (sort.equals("createdDate")) {
                    cq.orderBy(cb.asc(root.get("ngayCapNhat")));
                }
            }
        }

        Query query = s.createQuery(cq);

        // Xử lý phân trang
        String page = params.get("page");
        if (page != null && !page.isEmpty()) {
            int p = Integer.parseInt(page);
            int start = (p - 1) * PAGE_SIZE;
            query.setFirstResult(start);
            query.setMaxResults(PAGE_SIZE);
        }

        return query.getResultList();
    }

    // In khu do xe de muc dich hien chi
    @Override
    public Chitietkhudo getChiTietKhuDoXeByID(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Chitietkhudo.class, id);

    }

    @Override
    @Transactional
    public void add_or_update(Chitietkhudo p) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (p.getId() != null) {
                System.out.println("vo update");
                session.update(p);
            } else {
                session.save(p);
            }
        } catch (Exception e) {
            // Xử lý các lỗi không mong muốn
            String errorMessage = env.getProperty("error.unexpected", "Đã xảy ra lỗi không mong muốn.");
            System.err.println("Unexpected Exception: " + e.getMessage());
            throw new RuntimeException(errorMessage, e);
        }
    }
    @Override
    public void deleteChiTietKhuDoXe(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Chitietkhudo chiTietKhuDoXe = s.get(Chitietkhudo.class, id);

        if (chiTietKhuDoXe != null) {
            // Kiểm tra xem đối tượng có liên kết với bất kỳ Baidoxepic nào không
            if (!chiTietKhuDoXe.getChodoSet().isEmpty()) {
                throw new RuntimeException("Không thể xóa vì truong khoa ngoai liên quan.");
            }
            // Xóa đối tượng nếu không có ảnh liên quan
            s.delete(chiTietKhuDoXe);
        } else {
            throw new RuntimeException("Đối tượng không tồn tại.");
        }
    }

}
