/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.repository.impl;

import com.qlbdx.pojo.Chitietkhudo;
import com.qlbdx.pojo.Chodo;
import com.qlbdx.pojo.Gia;
import com.qlbdx.pojo.Khudoxe;
import com.qlbdx.pojo.Phuongtien;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class KhuDoXeRepositoryImpl implements KhuDoXeRepository {

    private static final int PAGE_SIZE = 4;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public List<Khudoxe> getKhuDoXe(Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Khudoxe> cq = cb.createQuery(Khudoxe.class);
        Root<Khudoxe> root = cq.from(Khudoxe.class);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            // Lọc theo tên khu vực
            String tenDay = params.get("tenDay");
            if (tenDay != null && !tenDay.isEmpty()) {
                Predicate p1 = cb.like(root.get("tenDay"), String.format("%%%s%%", tenDay));
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
                } else if (sort.equals("tenDay")) {
                    cq.orderBy(cb.asc(root.get("tenDay")));
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
    public Khudoxe getKhuDoXeByID(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Long idKhu = (long) id;
        return s.get(Khudoxe.class, idKhu);

    }

    @Override
    @Transactional
    public void add_or_update(Khudoxe p) {
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
    public void deleteKhuDoXe(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        Khudoxe khudoxe = s.get(Khudoxe.class, Long.valueOf(id));

        if (khudoxe != null) {
            if (!khudoxe.getChitietkhudoSet().isEmpty()) {
                throw new RuntimeException("Không thể xóa vì có chi tiết khu đổ liên quan.");
            }

            // Xóa đối tượng nếu không có ảnh liên quan
            s.delete(khudoxe);
        } else {
            throw new RuntimeException("Đối tượng không tồn tại.");
        }
    }

    @Override
    public List<Object[]> getKhuDoXeByBaiDoXeId(int baiDoXeId, Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        // Tạo CriteriaQuery
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        // Root entity: chodo
        Root<Chodo> chodoRoot = cq.from(Chodo.class);

        // Thực hiện join với các bảng liên quan
        Join<Chodo, Chitietkhudo> chitietkhudoJoin = chodoRoot.join("khuDoXeid");
        Join<Chitietkhudo, Khudoxe> khudoxeJoin = chitietkhudoJoin.join("khuDoId");
        Join<Chitietkhudo, Gia> giaJoin = chitietkhudoJoin.join("giaId");
        Join<Chitietkhudo, Phuongtien> phuongtienJoin = chitietkhudoJoin.join("phuongTienId");

        // Subquery để lấy MAX(ngayCapNhat) cho từng khuDoId
        Subquery<Date> subquery = cq.subquery(Date.class);
        Root<Chitietkhudo> chitietkhudoSubRoot = subquery.from(Chitietkhudo.class);
        subquery.select(cb.greatest(chitietkhudoSubRoot.<Date>get("ngayCapNhat")));
        subquery.where(cb.equal(chitietkhudoSubRoot.get("khuDoId"), chitietkhudoJoin.get("khuDoId")));

        // Điều kiện where cho subquery
        Predicate ngayCapNhatCondition = cb.equal(chitietkhudoJoin.get("ngayCapNhat"), subquery);

        // Lựa chọn các cột cần lấy: c.tenDay, cd.baiDoXe_id, c.id, g.gia, pt.loai
        cq.multiselect(
                chodoRoot.get("baiDoXeid").get("id"),
                
                chitietkhudoJoin.get("id"),
                khudoxeJoin.get("tenDay"),
                giaJoin.get("gia"),
                phuongtienJoin.get("loai")
        ).distinct(true);

        // Điều kiện chính
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(chodoRoot.get("baiDoXeid").get("id"), baiDoXeId));
        predicates.add(ngayCapNhatCondition);

        // Thêm điều kiện từ params
        if (params != null) {
            String pt = params.get("pt");
            String gia = params.get("gia");
            System.out.println("11111111111111111111");
            System.out.println(pt);
            System.out.println(gia);
            System.out.println(params);

            if (pt != null && !pt.isEmpty()) {
                predicates.add(cb.like(phuongtienJoin.get("loai"), pt));
            }
            if (gia != null && !gia.isEmpty()) {
                predicates.add(cb.equal(giaJoin.get("gia"), Double.parseDouble(gia)));
            }
        }

        // Áp dụng điều kiện where
        cq.where(predicates.toArray(new Predicate[0]));

        // Sắp xếp kết quả
        cq.orderBy(cb.asc(khudoxeJoin.get("tenDay")));

        // Tạo và thực thi query
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

}
