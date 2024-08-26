package com.qlbdx.repository.impl;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Rating;
import com.qlbdx.pojo.Thongtindangky;
import com.qlbdx.pojo.User;
import com.qlbdx.pojo.Xe;
import com.qlbdx.repository.RatingRepository;
import com.qlbdx.repository.UserRepository;
import com.qlbdx.repository.XeRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author quang
 */
@Repository
@Transactional
public class RatingRepositoryImpl implements RatingRepository {

    @Autowired
    private LocalSessionFactoryBean factory; // tic

    @Override
    public List<Rating> getRatingsByBaidoxeId(Long baidoxeId, Map<String, Object> params) {
        // Lấy session hiện tại
        Session session = factory.getObject().getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Rating> ratingQuery = cb.createQuery(Rating.class);

        // Tạo root từ Rating class
        Root<Rating> ratingRoot = ratingQuery.from(Rating.class);

        // Danh sách các điều kiện lọc
        List<Predicate> predicates = new ArrayList<>();

        // Thiết lập điều kiện where cho truy vấn
        Predicate baidoxePredicate = cb.equal(ratingRoot.get("baiDoid").get("id"), baidoxeId);
        predicates.add(baidoxePredicate);

        if (params != null) {
            // Lọc theo ngày tạo của rating
            String startDateStr = (String) params.get("startDate");
            if (startDateStr != null) {
                try {
                    // Chuyển đổi từ String sang LocalDate

                    Date startDate = Date.valueOf(startDateStr);

                    // Tạo Predicate cho khoảng thời gian
                    Predicate datePredicate = cb.lessThanOrEqualTo(ratingRoot.get("createdDate"), startDate);

                    predicates.add(datePredicate);
                } catch (DateTimeParseException e) {
                    // Xử lý lỗi phân tích ngày tháng (nếu cần)
                    e.printStackTrace();
                }
            }
        }

        // Thêm các Predicate vào CriteriaQuery
        if (!predicates.isEmpty()) {
            ratingQuery.where(predicates.toArray(new Predicate[0]));
        }

        // Sắp xếp theo điểm số từ cao xuống thấp
        ratingQuery.orderBy(cb.desc(ratingRoot.get("rate")));

        // Tạo truy vấn từ CriteriaQuery
        Query ratingQueryResult = session.createQuery(ratingQuery);

        // Thực thi truy vấn và lấy kết quả
        List<Rating> listRatings = ratingQueryResult.getResultList();

        // Trả về danh sách các đối tượng Rating
        return listRatings;
    }

    /**
     *
     * @param rate
     */
    @Override
    public void add_or_update(Rating rate) {
        System.out.println("rate" + rate.getId() + rate.getNhanxet() + rate.getBaiDoid() + rate.getRate());
        try {
            Session session = this.factory.getObject().getCurrentSession();

            // Kiểm tra xem BaiDoid có tồn tại không
            if (rate.getBaiDoid() != null && rate.getBaiDoid().getId() != null) {
                // Tải đối tượng Baidoxe từ cơ sở dữ liệu để đảm bảo nó hợp lệ
                Baidoxe baiDoid = session.get(Baidoxe.class, rate.getBaiDoid().getId());
                if (baiDoid != null) {
                    rate.setBaiDoid(baiDoid);
                } else {
                    throw new RuntimeException("Baidoxe with ID " + rate.getBaiDoid().getId() + " not found");
                }
            } else {
                throw new RuntimeException("Baidoxe ID is null");
            }

            session.saveOrUpdate(rate); // Lưu hoặc cập nhật đối tượng Rating
        } catch (Exception ex) {
            throw new RuntimeException("Error saving or updating rating: " + ex.getMessage(), ex);
        }
    }

}
