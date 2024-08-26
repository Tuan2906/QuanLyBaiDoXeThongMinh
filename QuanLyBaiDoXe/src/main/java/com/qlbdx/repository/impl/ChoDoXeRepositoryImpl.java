/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.repository.impl;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Chodo;
import com.qlbdx.pojo.Khudoxe;
import com.qlbdx.pojo.State;
import com.qlbdx.pojo.Thongtindangky;
import com.qlbdx.repository.ChoDoXeRepository;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
public class ChoDoXeRepositoryImpl implements ChoDoXeRepository {

    private static final int PAGE_SIZE = 20;
    @Autowired
    private LocalSessionFactoryBean factory; // tic

    @Override
    public Map<String, Object> getChoDoXe(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Chodo> countRoot = countQuery.from(Chodo.class); // Tạo Root cho truy vấn đếm
        countQuery.select(cb.count(countRoot));
        long totalChoDo = s.createQuery(countQuery).getSingleResult();
        // Truy vấn để lấy danh sách người dùng

        CriteriaQuery<Chodo> cq = cb.createQuery(Chodo.class);
        Root< Chodo> root = cq.from(Chodo.class); // Tạo Root cho truy vấn danh sách
//        Join<Chodo, Baidoxe> baiDoXeJoin = root.join("baiDoXeid");
//        Join<Chodo, Khudoxe> khuDoXeJoin = root.join("khuDoXeid");
//        Join<Chodo, State> stateJoin = root.join("stateId");
        Order orderById = cb.asc(root.get("id"));
        cq.orderBy(orderById);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            String kw = params.get("q");
            String qKhoangCach = params.get("khoangcach");
            String qBaiDoXe = params.get("bdx");
            String qKhuDoXe = params.get("kdx");
            String qState = params.get("state");

            if (kw != null && !kw.isEmpty()) {
                predicates.add(cb.equal(root.get("vitri"), kw));
            }
            if (qKhoangCach != null && !qKhoangCach.isEmpty()) {
                predicates.add(cb.equal(root.get("khoangCach"), qKhoangCach));
            }
            if (qBaiDoXe != null && !qBaiDoXe.isEmpty()) {
                predicates.add(cb.equal(root.get("baiDoXeid"), Long.valueOf(qBaiDoXe)));
            }
            if (qKhuDoXe != null && !qKhuDoXe.isEmpty()) {
                predicates.add(cb.equal(root.get("khuDoXeid"), Long.valueOf(qKhuDoXe)));
            }
            if (qState != null && !qState.isEmpty()) {
                predicates.add(cb.equal(root.get("stateId"), Long.valueOf(qState)));
            }
        }
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
            totalChoDo = 1;
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
        if (totalChoDo == 1) {
            q.getResultList();
        } else {
            q.setMaxResults(PAGE_SIZE);

        }
        q.setFirstResult(start);

        List< Chodo> chodo = q.getResultList();

        // Tạo Map để trả về danh sách người dùng và tổng số lượng
        Map<String, Object> result = new HashMap<>();
        result.put("totalChoDo", (int) Math.ceil((double) totalChoDo / PAGE_SIZE));
        result.put("chodo", chodo);

        return result;
    }

    @Override
    public void addorUpdateChoDo(Chodo cd) {
        Session s = factory.getObject().getCurrentSession();

        if (cd.getId() != null) {
            s.update(cd);
        } else {
            s.save(cd);
        }
    }

    @Override
    public void deleteChoDo(Chodo cd) {
        Session s = factory.getObject().getCurrentSession();
        Chodo a = s.get(Chodo.class, cd.getId());
        System.out.println("11111111111111111");
        if (a.getThongtindangkySet().isEmpty()) {
            s.delete(a);
        } else {
            throw new RuntimeException("Không thể xóa vì có người đăng ký liên quan.");

        }
    }

    @Override
    public Chodo getChoDoById(long id) {
        Session s = factory.getObject().getCurrentSession();
        return s.get(Chodo.class, id);
    }

    @Override
    @Transactional
    public void updateForeignKeyChoDo(Integer oldDetailId, Integer newId, Integer baiDoXeId) {
        Session session = this.factory.getObject().getCurrentSession();
        //int updatedEntities = 0;

        //try {
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaUpdate<Chodo> update = cb.createCriteriaUpdate(Chodo.class);
//            CriteriaQuery<Chodo> query = cb.createQuery(Chodo.class);
//
//            Root<Chodo> root = query.from(Chodo.class);
//            Root<Chodo> rootU = update.from(Chodo.class);
//
//            query.multiselect(root.get("id"));
//            query.where(
//                    cb.equal(root.get("khuDoXeid").get("id"), oldDetailId),
//                    cb.equal(root.get("baiDoXeid").get("id"), baiDoXeId)
//            );
//
//            Query q = session.createQuery(query);
//            List<Chodo> ids = q.getResultList();
//            List<Long> idss = new ArrayList<>();
//            ids.forEach(action -> idss.add(action.getId()));
//            idss.forEach(action ->System.out.println("adwd"+action));
//            Map<String, List<Long>> IdArr = new HashMap<>();
//            // Thực hiện truy vấn
//            // Thiết lập giá trị mới cho khudoxeId
//            update.set(rootU.get("khuDoXeid"), newId);
//
//            // Thiết lập điều kiện WHERE với Subquery
//            update.where(
//                    rootU.get("id").in(idss)
//            );
//            Query qu = session.createQuery(update);
//            // Thực hiện cập nhật
//            updatedEntities = qu.executeUpdate();
//            session.flush();
//            session.clear();
        //       } 
        String hql = "UPDATE Chodo SET khuDoXeid.id = :newDetailId "
                + "WHERE id IN (SELECT c.id FROM Chodo c WHERE c.khuDoXeid.id = :oldDetailId AND c.baiDoXeid.id = :baiDoXeId)";

        Query query = session.createQuery(hql);
        query.setParameter("newDetailId", newId);
        query.setParameter("oldDetailId", oldDetailId);
        query.setParameter("baiDoXeId", baiDoXeId);
        try {
            int updatedEntities = query.executeUpdate();
            session.flush();
            session.clear();

            System.out.println("Updated " + updatedEntities + " Chodo entities where khuDoXeid.id: " + oldDetailId + " and baiDoXeid.id: " + baiDoXeId + " to new ID: " + newId);
        } catch (Exception e) {
            String errorMessage = "Failed to update khuDoXeid.id for oldDetailId: " + oldDetailId;
            System.err.println(errorMessage + " Error: " + e.getMessage());
            throw new RuntimeException(errorMessage, e);
        }
    }

    @Override
    public List<Object[]> getChoDoByIdKhuAndBai(int baiId, int chiTietKhuId, Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        // Tạo CriteriaQuery
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        // Root entity: chodo
        Root<Chodo> chodoRoot = cq.from(Chodo.class);

        // Thực hiện join với bảng state
        // Lựa chọn các cột cần lấy: cd.id, cd.vitri, cd.khoangCach, s.loai
        cq.multiselect(
                chodoRoot.get("id"), // cd.id
                chodoRoot.get("vitri"), // cd.vitri
                chodoRoot.get("khoangCach"), // cd.khoangCach
                chodoRoot.get("stateId").get("loai") // s.loai
        );

        // Điều kiện where
        List<Predicate> predicates = new ArrayList<>();

        // Thêm điều kiện từ params
        if (params != null) {
            String kc = params.get("kc");
            String vt = params.get("vt");
            System.out.println(kc);
            System.out.println(vt);

            if (kc != null && !kc.isEmpty()) {
                // Kiểm tra và ép kiểu dữ liệu
                try {
                    double khoangCach = Double.parseDouble(kc);
                    predicates.add(cb.equal(chodoRoot.get("khoangCach"), khoangCach));
                } catch (NumberFormatException e) {
                    // Xử lý lỗi khi không thể ép kiểu
                    e.printStackTrace(); // Hoặc ghi log lỗi
                }
            }
            if (vt != null && !vt.isEmpty()) {
                // Kiểm tra và ép kiểu dữ liệu
                try {
                    int vitri = Integer.parseInt(vt);
                    predicates.add(cb.equal(chodoRoot.get("vitri"), vitri));
                } catch (NumberFormatException e) {
                    // Xử lý lỗi khi không thể ép kiểu
                    e.printStackTrace(); // Hoặc ghi log lỗi
                }
            }
        }

        // Thêm điều kiện chính
        predicates.add(cb.equal(chodoRoot.get("baiDoXeid").get("id"), baiId));
        predicates.add(cb.equal(chodoRoot.get("khuDoXeid").get("id"), chiTietKhuId));

        // Áp dụng điều kiện where
        cq.where(predicates.toArray(new Predicate[0]));

        // Tạo và thực thi query
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Long> getDistinctChodoIds(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        // Khởi tạo CriteriaBuilder và CriteriaQuery
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);

        // Định nghĩa root cho truy vấn
        Root<Chodo> choDoRoot = query.from(Chodo.class);
        Join<Chodo, Thongtindangky> thongTinDangKyJoin = choDoRoot.join("thongtindangkySet");

        // Chọn distinct id từ bảng `chodo`
        query.select(choDoRoot.get("id")).distinct(true);

        // Tạo các điều kiện cho truy vấn
        List<Predicate> predicates = new ArrayList<>();

        // Lấy giá trị từ params
        String startTime = params.get("startTime");
        String endTime = params.get("endTime");
        String baiDoXeId = params.get("baiDoXe_id");
        String khuDoXeId = params.get("khuDoXe_id");
        String phuongtien = params.get("phuongtien");
        if (startTime != null && endTime != null) {
            Timestamp startTimestamp = Timestamp.valueOf(startTime);
            Timestamp endTimestamp = Timestamp.valueOf(endTime);

            Predicate p1 = builder.between(thongTinDangKyJoin.get("thoiGianVoBai"), startTimestamp, endTimestamp);
            Predicate p2 = builder.between(thongTinDangKyJoin.get("thoiGianRaBai"), startTimestamp, endTimestamp);
            Predicate p3 = builder.and(
                    builder.lessThan(thongTinDangKyJoin.get("thoiGianVoBai"), startTimestamp),
                    builder.greaterThan(thongTinDangKyJoin.get("thoiGianRaBai"), endTimestamp)
            );
            // Gộp các điều kiện `OR` lại
            predicates.add(builder.or(p1, p2, p3));
        }

        // Điều kiện isHuy = 0
        predicates.add(builder.equal(thongTinDangKyJoin.get("isHuy"), 0));

        // Điều kiện baiDoXe_id và khuDoXe_id
        if (baiDoXeId != null) {
            predicates.add(builder.equal(choDoRoot.get("baiDoXeid").get("id"), Integer.valueOf(baiDoXeId)));
        }
        if (khuDoXeId != null) {
            predicates.add(builder.equal(choDoRoot.get("khuDoXeid").get("id"), Long.valueOf(khuDoXeId)));
        }
        if (phuongtien != null) {
            predicates.add(builder.equal(choDoRoot.get("khuDoXeid").get("phuongTienId").get("id"), Integer.valueOf(phuongtien)));
        }
        // Áp dụng các điều kiện vào truy vấn
        query.where(predicates.toArray(new Predicate[0]));

        // Thực thi truy vấn và trả về kết quả
        Query finalQuery = session.createQuery(query);
        return finalQuery.getResultList();
    }

}
