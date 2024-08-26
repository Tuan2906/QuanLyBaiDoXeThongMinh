/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.repository.impl;

import com.qlbdx.Mapper.DateMapper;
import com.qlbdx.dto.ThongTinDangKyDTO_v2;
import com.qlbdx.pojo.Chodo;
import com.qlbdx.pojo.Hoadon;
import com.qlbdx.pojo.Khudoxe;
import com.qlbdx.pojo.Thongtindangky;
import com.qlbdx.pojo.User;
import com.qlbdx.pojo.Xe;
import com.qlbdx.repository.ChoDoXeRepository;
import com.qlbdx.repository.HoaDonRepository;
import com.qlbdx.repository.ThongTinDangKiRepository;
import com.qlbdx.repository.XeRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.LockMode;
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
public class ThongTinDangKiRepositoryImpl implements ThongTinDangKiRepository {

    private static final int PAGE_SIZE = 4;

    @Autowired
    private ChoDoXeRepository chodoRepository;
    @Autowired
    private XeRepository xeRepository;
    @Autowired
    private HoaDonRepository hoadonRepository;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Thongtindangky> getThongTinDangKy(Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Thongtindangky> cq = cb.createQuery(Thongtindangky.class);
        Root<Thongtindangky> root = cq.from(Thongtindangky.class);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

//        if (params != null) {
//            // Lọc theo tên khu vực
//            String tenDay = params.get("tenDay");
//            if (tenDay != null && !tenDay.isEmpty()) {
//                Predicate p1 = cb.like(root.get("tenDay"), String.format("%%%s%%", tenDay));
//                predicates.add(p1);
//            }
//
//            // Thêm các Predicate vào CriteriaQuery
//            if (!predicates.isEmpty()) {
//                cq.where(predicates.toArray(new Predicate[0]));
//            }
//
//            // Sắp xếp theo ID hoặc tên khu vực nếu được yêu cầu
//            String sort = params.get("sort");
//            if (sort != null && !sort.isEmpty()) {
//                if (sort.equals("id")) {
//                    cq.orderBy(cb.desc(root.get("id")));
//                } else if (sort.equals("tenDay")) {
//                    cq.orderBy(cb.asc(root.get("tenDay")));
//                }
//            }
//        }
//
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
    
    // nhan vien
    @Override
    public List<Object[]> getThongTinChoDaDangKy(int choDoId, Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        // Tạo CriteriaQuery
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        // Root entity: thongtindangky
        Root<Thongtindangky> ttdkRoot = cq.from(Thongtindangky.class);

        // Join với các bảng liên quan
        Join<Thongtindangky, Xe> xeJoin = ttdkRoot.join("xeId");
        Join<Xe, User> userJoin = xeJoin.join("userId");

        // Lựa chọn các cột cần lấy
        cq.multiselect(
                ttdkRoot.get("id"),
                ttdkRoot.get("isHuy"),
                ttdkRoot.get("thoiGianRaBai"),
                ttdkRoot.get("thoiGianVoBai"),
                ttdkRoot.get("thoiGianDangKy"),
                xeJoin.get("tenXe"),
                xeJoin.get("bienSo"),
                xeJoin.get("image"),
                userJoin.get("username")
        );

        // Tạo danh sách các điều kiện
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            System.out.println("acadawdaw");
            String ishuy = params.get("ishuy");
            String thoigianvobai = params.get("vb");
            String thoigianrabai = params.get("rb");
            String thoigiandangky = params.get("dk");
            String username = params.get("name");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (username != null && !username.isEmpty()) {
                System.out.println("acadawdaw1");

                predicates.add(cb.like(userJoin.get("username"), "%" + username + "%"));
            }
            if (ishuy != null && !ishuy.isEmpty()) {
                System.out.println("acadawdaw1");

                boolean isHuyBoolean = Boolean.parseBoolean(ishuy);
                predicates.add(cb.equal(ttdkRoot.get("isHuy"), isHuyBoolean));
            }
            if (thoigiandangky != null && !thoigiandangky.isEmpty()) {
                Date thoiGianDangKyDate = null;
                try {
                    thoiGianDangKyDate = dateFormat.parse(thoigiandangky);
                } catch (ParseException ex) {
                    Logger.getLogger(ThongTinDangKiRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Thêm điều kiện vào predicates
                predicates.add(cb.equal(ttdkRoot.get("thoiGianDangKy"), thoiGianDangKyDate));
            }
            if (thoigianvobai != null && !thoigianvobai.isEmpty() && thoigianrabai != null && !thoigianrabai.isEmpty()) {
                System.out.println("acadawdaw3");

                // Chuyển đổi tham số ngày tháng từ String sang Date
                try {
                    Date vobaiDate = dateFormat.parse(thoigianvobai);
                    Date rabaiDate = dateFormat.parse(thoigianrabai);

                    // Thêm điều kiện BETWEEN cho trường thoiGianRaBai và thoiGianVoBai
                    predicates.add(cb.between(ttdkRoot.get("thoiGianRaBai"), vobaiDate, rabaiDate));
                    predicates.add(cb.between(ttdkRoot.get("thoiGianVoBai"), vobaiDate, rabaiDate));
                } catch (ParseException e) {
                    e.printStackTrace(); // Xử lý lỗi phân tích ngày tháng nếu cần
                }
            }
        }

        // Thêm điều kiện cho trường choDoid.id
        predicates.add(cb.equal(ttdkRoot.get("choDoid").get("id"), choDoId));

        // Điều kiện where
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }

        // Tạo và thực thi query
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void HuyDangKy(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Thongtindangky ttdk = session.get(Thongtindangky.class, Long.valueOf(id));
        ttdk.setIsHuy(true);
        session.update(ttdk);
    }

    @Override
    public List<Thongtindangky> findByThoiGianRaBaiBefore() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date();
        // Thời gian bắt đầu của ngày hôm nay (00:00:00)
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startOfDay = calendar.getTime();

        // Thời gian kết thúc của ngày hôm nay (23:59:59)
        calendar.add(Calendar.DAY_OF_MONTH, 1); // Chuyển sang ngày tiếp theo
        Date endOfDay = calendar.getTime();

        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        System.out.println(currentDate);
        CriteriaQuery<Thongtindangky> query = cb.createQuery(Thongtindangky.class);
        Root<Thongtindangky> root = query.from(Thongtindangky.class);
        query.select(root)
                .where(cb.and(
                        cb.lessThanOrEqualTo(root.get("thoiGianRaBai"), currentDate),
                        cb.greaterThanOrEqualTo(root.get("thoiGianRaBai"), startOfDay),
                        cb.lessThan(root.get("thoiGianRaBai"), endOfDay),
                        cb.equal(root.get("isHuy"), false)
                ));

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public void add_or_update(ThongTinDangKyDTO_v2 dk) {
        Session session = factory.getObject().getCurrentSession();
        System.out.println("00000000000000000000000000000000");

        if (dk.getId() != null) {
            System.out.println("11111111111111111111111111111111");
            Thongtindangky ttdk = this.getthongTinDangKyById(dk.getId());
            ttdk.setChoDoid(session.get(Chodo.class, dk.getChoDoId()));
            ttdk.setIsHuy(false);
            ttdk.setThoiGianRaBai(dk.getThoiGianRaBai());
            ttdk.setThoiGianVoBai(dk.getThoiGianVoBai());
            if (dk.getHoadonId() != null) {
                Hoadon hoadon = hoadonRepository.getHoaDonById(Math.toIntExact(dk.getHoadonId()));
                if (hoadon != null) {
                    // Nếu hóa đơn tồn tại, không thay đổi nó
                    ttdk.setHoadon(hoadon);
                }
            }
            session.update(ttdk);
        } else {
            System.out.println("22222222222222222222222222222222222");

            Hoadon hd = new Hoadon();
            Thongtindangky ttdk = new Thongtindangky();
            // Cập nhật thông tin đăng ký
            ttdk.setThoiGianVoBai(dk.getThoiGianVoBai());
            ttdk.setThoiGianRaBai(dk.getThoiGianRaBai());
            ttdk.setThoiGianDangKy(dk.getThoiGianDangKy() != null ? dk.getThoiGianDangKy() : new Date());
            ttdk.setIsHuy(dk.isHuy());

            // Liên kết đối tượng
            Xe xe2 = xeRepository.getXeById(dk.getXeId());
            ttdk.setXeId(xe2);

            Chodo chodo = chodoRepository.getChoDoById(dk.getChoDoId());
            ttdk.setChoDoid(chodo);
            session.save(ttdk);
            hd.setNgayCapNhat(new Date());
            hd.setSoTien(dk.getSoTien());
            hd.setUid(dk.getUid());
            hd.setUserId(ttdk);
            session.save(hd);

        }
    }

    @Override
    public Thongtindangky getthongTinDangKyById(long id) {
        Session s = factory.getObject().getCurrentSession();
        return s.get(Thongtindangky.class, id);
    }

    @Override
    public List<Thongtindangky> findAllActiveRegistrations(Long currentUserId) {
        Session s = factory.getObject().getCurrentSession();
        // Tạo đối tượng CriteriaBuilder từ EntityManager
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();

        // Tạo đối tượng CriteriaQuery cho Thongtindangky
        CriteriaQuery<Thongtindangky> dk = criteriaBuilder.createQuery(Thongtindangky.class);

        // Xác định nguồn dữ liệu cho truy vấn (từ bảng Thongtindangky)
        Root<Thongtindangky> root = dk.from(Thongtindangky.class);

        // Tạo điều kiện truy vấn (WHERE t.active = true)
        Predicate timePredicate = criteriaBuilder.greaterThan(root.get("thoiGianVoBai"), new Date());
        Predicate userPredicate = criteriaBuilder.equal(root.get("xeId").get("userId"), currentUserId);

        // Kết hợp các điều kiện
        dk.where(criteriaBuilder.and(userPredicate, timePredicate));

        // Tạo và thực thi truy vấn
        Query query = s.createQuery(dk); // Trả về danh sách kết quả
        System.out.println("ccho"+query.getResultList());
        return query.getResultList();

    }
}
