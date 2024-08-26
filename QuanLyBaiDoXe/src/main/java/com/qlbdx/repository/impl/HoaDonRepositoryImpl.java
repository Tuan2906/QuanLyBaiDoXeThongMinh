/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.repository.impl;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Hoadon;
import com.qlbdx.repository.HoaDonRepository;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
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
public class HoaDonRepositoryImpl implements HoaDonRepository {

    private static final int PAGE_SIZE = 4;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Map<String, Object> getHoaDon(Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = b.createQuery(Long.class);
        Root<Hoadon> countRoot = countQuery.from(Hoadon.class); // Tạo Root cho truy vấn đếm
        countQuery.select(b.count(countRoot));
        long totalHoaDon = s.createQuery(countQuery).getSingleResult();

        CriteriaQuery<Hoadon> q = b.createQuery(Hoadon.class);
        Root<Hoadon> root = q.from(Hoadon.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            // Lọc theo địa chỉ
            String kw = params.get("q");
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = b.equal(root.get("soTien"), kw);
                predicates.add(p1);
            }

            // Lọc theo thời gian mở cửa
            String ngayCapNhat = params.get("ngaycapnhat");
            if (ngayCapNhat != null && !ngayCapNhat.isEmpty()) {
                try {
                    Timestamp thoigiancapnhat = new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(ngayCapNhat).getTime());
                    System.out.println("mo cua" + thoigiancapnhat);

                    Predicate p2 = b.equal(root.get("ngayCapNhat"), thoigiancapnhat);
                    predicates.add(p2);
                } catch (ParseException ex) {
                    System.out.println("loi" + ex);
                    Logger.getLogger(BaiDoXeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        // Sắp xếp theo ID hoặc địa chỉ nếu được yêu cầu
        System.out.println("param" + params.get("sort"));
        if (params != null) {
            String sort = params.get("sort");
            if (sort != null && !sort.isEmpty()) {
                if (sort.equals("id")) {
                    q.orderBy(b.desc(root.get("id")));
                } else if (sort.equals("soTien")) {
                    q.orderBy(b.asc(root.get("soTien")));
                }
            }
        }

        Query query = s.createQuery(q);
        String page = params.get("page");
        int start = 0;
        if (page != null && !page.isEmpty()) {
            int p = Integer.parseInt(page);
            start = (p - 1) * PAGE_SIZE;
        }
        if (totalHoaDon == 1) {
            query.getResultList();
        } else {
            query.setMaxResults(PAGE_SIZE);

        }
        query.setFirstResult(start);

        List<Hoadon> hoadons = query.getResultList();

        // Tạo Map để trả về danh sách người dùng và tổng số lượng
        Map<String, Object> result = new HashMap<>();
        result.put("totalHoaDon", (int) Math.ceil((double) totalHoaDon / PAGE_SIZE));
        result.put("HoaDon", hoadons);

        return result;
    }

    @Override
    public void addHoaDon(Hoadon b) {
        Session s = this.factory.getObject().getCurrentSession();

        if (b.getId() != null) {
            s.update(b);
        } else {
            s.save(b);
        }
    }

    @Override
    public void deleteHoaDon(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Hoadon hoadon = s.get(Hoadon.class, Long.valueOf(id));

        if (hoadon != null) {
            if (!hoadon.getUserhoantienSet().isEmpty()) {
                throw new RuntimeException("Không thể xóa vì có hoàn tiền liên quan.");
            }
            if (hoadon.getUserId().getId() != null) {
                throw new RuntimeException("Không thể xóa vì có thông tin đăng ký liên quan.");
            }
            s.delete(hoadon);
        } else {
            throw new RuntimeException("Đối tượng không tồn tại.");
        }
    }

    @Override
    public Hoadon getHoaDonById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Long idKhu = (long) id;
        return s.get(Hoadon.class, idKhu);
    }

    @Override
    public Hoadon getHoaDonByThongTinDangKyId(int ttdkId) {
        // Get the current Hibernate session
        Session session = this.factory.getObject().getCurrentSession();

        // Create CriteriaBuilder
        CriteriaBuilder cb = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Hoadon> cq = cb.createQuery(Hoadon.class);

        // Define the root for the query (the FROM clause)
        Root<Hoadon> hoadonRoot = cq.from(Hoadon.class);
        // Add the WHERE clause
        cq.where(cb.equal(hoadonRoot.get("userId"), ttdkId));

        // Create the query
        Query query = session.createQuery(cq);

        // Execute the query and get the result list
        return  (Hoadon) query.getSingleResult();
    }

}
