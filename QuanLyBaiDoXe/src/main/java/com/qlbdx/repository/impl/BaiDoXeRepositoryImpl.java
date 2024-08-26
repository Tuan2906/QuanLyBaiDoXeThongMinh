package com.qlbdx.repository.impl;

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Baidoxepic;
import com.qlbdx.pojo.Chitietkhudo;
import com.qlbdx.pojo.Chodo;
import com.qlbdx.pojo.Khudoxe;
import com.qlbdx.pojo.Rating;
import com.qlbdx.repository.BaiDoXeRepository;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class BaiDoXeRepositoryImpl implements BaiDoXeRepository {

    private static final int PAGE_SIZE = 4;
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Object[]> getChiTietBaiXeById(int id) {
        System.out.println("id" + id);
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rBai = q.from(Baidoxe.class);
        Root rCho = q.from(Chodo.class);
        Root rKhu = q.from(Chitietkhudo.class);

        q.where(b.equal(rCho.get("baiDoXeid"), rBai.get("id")),
                b.equal(rBai.get("id"), id), b.equal(rKhu.get("id"), rCho.get("khuDoXeid")));

        q.multiselect(
                rBai.get("id"),
                rBai.get("diachi"),
                rBai.get("tenBai"),
                rBai.get("thoigiancua"),
                rBai.get("thoigiandongcua"),
                b.count(rCho.get("id")), // Số lượng chỗ
                rKhu.get("id"), // ID của Khudoxe
                rKhu.get("ghiChu"),
                rKhu.get("giaId").get("id"), // ID của Gia
                rKhu.get("khuDoId").get("id"), // ID của Gia

                rKhu.get("phuongTienId").get("id") // ID của Phuongtien
        ).groupBy(
                rBai.get("id"),
                rBai.get("diachi"),
                rBai.get("tenBai"),
                rBai.get("thoigiancua"),
                rBai.get("thoigiandongcua"),
                rKhu.get("id"),
                rKhu.get("ghiChu"),
                rKhu.get("giaId").get("id"),
                rKhu.get("khuDoId").get("id"), // ID của Gia
                rKhu.get("phuongTienId").get("id")
        );
        System.out.println("done thanh cong");

        Query query = s.createQuery(q);
        return query.getResultList();

    }

    @Override
    public Map<String, Object> getBaiDoXe(Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = b.createQuery(Long.class);
        Root<Baidoxe> countRoot = countQuery.from(Baidoxe.class); // Tạo Root cho truy vấn đếm
        countQuery.select(b.count(countRoot));
        long totalBaidoxe = s.createQuery(countQuery).getSingleResult();

        CriteriaQuery<Baidoxe> q = b.createQuery(Baidoxe.class);
        Root<Baidoxe> root = q.from(Baidoxe.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            // Lọc theo địa chỉ
            String kw = params.get("q");
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = b.like(root.get("diachi"), String.format("%%%s%%", kw));
                predicates.add(p1);
            }

            // Lọc theo thời gian mở cửa
            String thoigiancua = params.get("thoigiancua");
            if (thoigiancua != null && !thoigiancua.isEmpty()) {
                try {
                    Timestamp thoigianmocua = new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(thoigiancua).getTime());
                    System.out.println("mo cua" + thoigianmocua);

                    Predicate p2 = b.greaterThanOrEqualTo(root.get("thoigiancua"), thoigianmocua);
                    predicates.add(p2);
                } catch (ParseException ex) {
                    System.out.println("loi" + ex);
                    Logger.getLogger(BaiDoXeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Lọc theo thời gian đóng cửa
            String thoigiandongcua = params.get("thoigiandongcua");
            if (thoigiandongcua != null && !thoigiandongcua.isEmpty()) {
                try {
                    Timestamp thoigiandong = new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(thoigiandongcua).getTime());
                    Predicate p3 = b.lessThanOrEqualTo(root.get("thoigiandongcua"), thoigiandong);
                    predicates.add(p3);
                } catch (ParseException ex) {
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
                } else if (sort.equals("diachi")) {
                    q.orderBy(b.asc(root.get("diachi")));
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
        if (totalBaidoxe == 1) {
            query.getResultList();
        } else {
            query.setMaxResults(PAGE_SIZE);

        }
        query.setFirstResult(start);

        List<Baidoxe> users = query.getResultList();

        // Tạo Map để trả về danh sách người dùng và tổng số lượng
        Map<String, Object> result = new HashMap<>();
        result.put("totalBaidoxe", (int) Math.ceil((double) totalBaidoxe / PAGE_SIZE));
        result.put("Baidoxe", users);

        return result;
    }

    @Override
    public void addBaiDoXe(Baidoxe b) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (b != null) {
                if (b.getId() != null) {
                    // Update existing Baidoxe
                    Baidoxe existingBaidoxe = s.get(Baidoxe.class, b.getId());
                    if (existingBaidoxe != null) {
                        updateBaidoxe(existingBaidoxe, b);
                        s.update(existingBaidoxe);
                    }
                } else {
                    // Save new Baidoxe
                    Baidoxe newBaidoxe = new Baidoxe();
                    saveBaidoxe(newBaidoxe, b);
                    s.save(newBaidoxe);

                    // Save related Baidoxepic
                    saveBaidoxepics(newBaidoxe, b.getBaidoxepicSet(), s);
                }
            }
        } catch (PersistenceException e) {
            handlePersistenceException(e);
        }
    }

    private void updateBaidoxe(Baidoxe existing, Baidoxe update) {
        existing.setDiachi(update.getDiachi());
        existing.setTenBai(update.getTenBai());

        existing.setThoigiancua(new Timestamp(update.getThoigiancua().getTime()));
        existing.setThoigiandongcua(new Timestamp(update.getThoigiandongcua().getTime()));
    }

    private void saveBaidoxe(Baidoxe newBaidoxe, Baidoxe b) {
        newBaidoxe.setDiachi(b.getDiachi());
        newBaidoxe.setTenBai(b.getTenBai());

        newBaidoxe.setThoigiancua(new Timestamp(b.getThoigiancua().getTime()));
        newBaidoxe.setThoigiandongcua(new Timestamp(b.getThoigiandongcua().getTime()));
    }

    private void saveBaidoxepics(Baidoxe baidoxe, Set<Baidoxepic> baidoxepicSet, Session session) {
        for (Baidoxepic pic : baidoxepicSet) {
            Baidoxepic newBaidoxepic = new Baidoxepic();
            newBaidoxepic.setImage(pic.getImage());
            newBaidoxepic.setBaiDoXeid(baidoxe);
            session.save(newBaidoxepic);
        }
    }

    private void handlePersistenceException(PersistenceException e) {
        Throwable cause = e.getCause();
        String errorMessage;
        System.out.println("vo bat loi");

        if (cause instanceof SQLIntegrityConstraintViolationException) {
            errorMessage = env.getProperty("error.baidoxe.duplicate", "Tên bai đã tồn tại. Vui lòng chọn tên khác.");
        } else if (cause instanceof ConstraintViolationException) {
            errorMessage = env.getProperty("error.baidoxe.duplicate", "Tên bai đã tồn tại. Vui lòng chọn tên khác.");
        } else if (cause instanceof DataIntegrityViolationException) {
            errorMessage = env.getProperty("error.data.integrity", "Có lỗi liên quan đến dữ liệu, vui lòng kiểm tra lại.");
        } else {
            errorMessage = env.getProperty("error.general", "Có lỗi xảy ra khi lưu khu vực.");
        }

        // Logging the cause
        System.err.println("Cause of PersistenceException: " + cause.getClass().getName());
        System.err.println("Message: " + cause.getMessage());

        // Throwing a runtime exception with a meaningful message
        throw new RuntimeException(errorMessage, e);
    }

    @Override
    public void deleteBaiDoXe(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Baidoxe baidoxe = s.get(Baidoxe.class, id);

        if (baidoxe != null) {
            if (!baidoxe.getChodoSet().isEmpty()) {
                throw new RuntimeException("Không thể xóa vì có chổ liên quan.");
            }

            // Xóa đối tượng nếu không có ảnh liên quan
            s.delete(baidoxe);
        } else {
            throw new RuntimeException("Đối tượng không tồn tại.");
        }
    }

    @Override
    public List<Object[]> getChiTietBaiXeByUserId(int id, Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Baidoxe> root = q.from(Baidoxe.class);

        Join<Baidoxe, Baidoxepic> joinPic = root.join("baidoxepicSet");
        Join<Baidoxe, Rating> joinRating = root.join("ratingSet", JoinType.LEFT);

        // Tạo các biểu thức cần thiết
        Expression<Double> avgRate = b.avg(joinRating.get("rate"));
        Expression<Long> countRate = b.count(joinRating.get("rate"));

        // Chọn các trường cần thiết
        q.multiselect(
                root.get("thoigiancua"),
                root.get("thoigiandongcua"),
                root.get("diachi"),
                root.get("id"),
                root.get("tenBai"),
                joinPic.get("image"),
                avgRate.alias("avgRate")
        );

        // Thêm group by
        q.groupBy(
                root.get("thoigiancua"),
                root.get("thoigiandongcua"),
                root.get("diachi"),
                root.get("id"),
                root.get("tenBai"),
                joinPic.get("image")
        );
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            // Lọc theo địa chỉ
            String kw = params.get("q");
            System.out.println("111111111111111111111111111");
            System.out.println(kw);
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = b.like(root.get("diachi"), String.format("%%%s%%", kw));
                predicates.add(p1);
            }

            // Lọc theo thời gian mở cửa
            String thoigiancua = params.get("thoigiancua");
            System.out.println(thoigiancua);
            if (thoigiancua != null && !thoigiancua.isEmpty()) {
                try {
                    Timestamp thoigianmocua = new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(thoigiancua).getTime());
                    System.out.println("mo cua" + thoigianmocua);

                    Predicate p2 = b.greaterThanOrEqualTo(root.get("thoigiancua"), thoigianmocua);
                    predicates.add(p2);
                } catch (ParseException ex) {
                    System.out.println("loi" + ex);
                    Logger.getLogger(BaiDoXeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Lọc theo thời gian đóng cửa
            String thoigiandongcua = params.get("thoigiandongcua");
            if (thoigiandongcua != null && !thoigiandongcua.isEmpty()) {
                try {
                    Timestamp thoigiandong = new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(thoigiandongcua).getTime());
                    Predicate p3 = b.lessThanOrEqualTo(root.get("thoigiandongcua"), thoigiandong);
                    predicates.add(p3);
                } catch (ParseException ex) {
                    Logger.getLogger(BaiDoXeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Predicate p4 = b.equal(root.get("userId"), id);
            predicates.add(p4);
            q.where(predicates.toArray(Predicate[]::new));
            String avg = params.get("avg");
            if (avg != null && !avg.isEmpty()) {
                try {
                    double avgValue = Double.parseDouble(avg);
                    Predicate havingPredicate = b.equal(avgRate, avgValue);
                    q.having(havingPredicate); // Áp dụng điều kiện HAVING
                } catch (NumberFormatException ex) {
                    Logger.getLogger(BaiDoXeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        // Tạo truy vấn và lấy kết quả
        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Object[]> getAllBai(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Baidoxe> root = q.from(Baidoxe.class);

        // Thực hiện join với bảng `baidoxepic` và `rating`
        Join<Baidoxe, Baidoxepic> joinPic = root.join("baidoxepicSet");
        Join<Baidoxe, Rating> joinRating = root.join("ratingSet", JoinType.LEFT);

        // Tạo các biểu thức cần thiết
        Expression<Double> avgRate = b.avg(joinRating.get("rate"));
        Expression<Long> countRate = b.count(joinRating.get("rate"));

        // Chọn các trường cần thiết
        q.multiselect(
                root.get("thoigiancua"),
                root.get("thoigiandongcua"),
                root.get("diachi"),
                root.get("id"),
                root.get("tenBai"),
                joinPic.get("image"),
                avgRate.alias("avgRate")
        );

        // Thêm group by
        q.groupBy(
                root.get("thoigiancua"),
                root.get("thoigiandongcua"),
                root.get("diachi"),
                root.get("id"),
                root.get("tenBai"),
                joinPic.get("image")
        );
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            // Lọc theo địa chỉ
            String kw = params.get("q");
            System.out.println("111111111111111111111111111");
            System.out.println(kw);
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = b.like(root.get("diachi"), String.format("%%%s%%", kw));
                predicates.add(p1);
            }

            // Lọc theo thời gian mở cửa
            String thoigiancua = params.get("thoigiancua");
            if (thoigiancua != null && !thoigiancua.isEmpty()) {
                try {
                    Timestamp thoigianmocua = new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(thoigiancua).getTime());
                    System.out.println("mo cua" + thoigianmocua);

                    Predicate p2 = b.greaterThanOrEqualTo(root.get("thoigiancua"), thoigianmocua);
                    predicates.add(p2);
                } catch (ParseException ex) {
                    System.out.println("loi" + ex);
                    Logger.getLogger(BaiDoXeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Lọc theo thời gian đóng cửa
            String thoigiandongcua = params.get("thoigiandongcua");
            if (thoigiandongcua != null && !thoigiandongcua.isEmpty()) {
                try {
                    Timestamp thoigiandong = new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(thoigiandongcua).getTime());
                    Predicate p3 = b.lessThanOrEqualTo(root.get("thoigiandongcua"), thoigiandong);
                    predicates.add(p3);
                } catch (ParseException ex) {
                    Logger.getLogger(BaiDoXeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            q.where(predicates.toArray(Predicate[]::new));
            String avg = params.get("avg");
            if (avg != null && !avg.isEmpty()) {
                try {
                    double avgValue = Double.parseDouble(avg);
                    Predicate havingPredicate = b.equal(avgRate, avgValue);
                    q.having(havingPredicate); // Áp dụng điều kiện HAVING
                } catch (NumberFormatException ex) {
                    Logger.getLogger(BaiDoXeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        // Tạo truy vấn và lấy kết quả
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Object[]> getAllPicWithBaiId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Baidoxepic> root = q.from(Baidoxepic.class);
        q.multiselect(
                root.get("image"),
                root.get("id")
        ).where(b.equal(root.get("baiDoXeid"), id));
        Query query = s.createQuery(q);
        System.out.println("22222222222222222222222222222222222");
        query.getResultList().forEach(action -> {
            System.out.println(action);
        });
        return query.getResultList();
    }

}
