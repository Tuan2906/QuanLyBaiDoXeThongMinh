package com.qlbdx.repository.impl;

import com.qlbdx.pojo.Thongtindangky;
import com.qlbdx.pojo.User;
import com.qlbdx.pojo.Xe;
import com.qlbdx.repository.XeRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
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
public class XeRepositoryImpl implements XeRepository {

    @Autowired
    private LocalSessionFactoryBean factory; // tic

    @Override
    public List<Xe> getXeByIdUser(User u) {
        // Lấy session hiện tại
        Session session = factory.getObject().getCurrentSession();

        // Tạo CriteriaBuilder
        CriteriaBuilder cb = session.getCriteriaBuilder();

        // Tạo CriteriaQuery cho đối tượng Xe
        CriteriaQuery<Xe> XeQuery = cb.createQuery(Xe.class);

        // Tạo root từ Xe class
        Root<Xe> XeRoot = XeQuery.from(Xe.class);

        // Thiết lập điều kiện where cho truy vấn
        XeQuery.select(XeRoot).where(cb.equal(XeRoot.get("userId"), u));

        // Tạo truy vấn từ CriteriaQuery
        Query query = session.createQuery(XeQuery);

        // Thực thi truy vấn và lấy kết quả
        List<Xe> listXe = query.getResultList();

        // Trả về danh sách các đối tượng Xe
        return listXe;
    }

    @Override
    public void add_or_update(Xe xe) {
        System.out.println(xe.getBienSo());
        System.out.println(xe.getImage());
        System.out.println(xe.getTenXe());
        try {
            Session s = this.factory.getObject().getCurrentSession();
            System.out.println("xe" + xe.getId());
            if (xe.getId() != null) {
                // Kiểm tra xem đối tượng có tồn tại trong cơ sở dữ liệu không
                System.out.println("111111111111111111");
                System.out.println(xe.getBienSo());
                System.out.println(xe.getImage());
                System.out.println(xe.getTenXe());

                // Cập nhật đối tượng đã tồn tại
                s.update(xe);

            } else {
                // Thêm mới đối tượng nếu ID không có
                s.save(xe);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteXe(Long id) {
        try {
            Session session = this.factory.getObject().getCurrentSession();
            System.out.println("ABCCCCC");
            // Tìm đối tượng Xe
            System.out.println("vao deletet");
            Xe xe = session.get(Xe.class, id);
            if (xe != null) {
                // Cập nhật các đối tượng Thongtindangky để thiết lập khóa ngoại thành null
                for (Thongtindangky tt : xe.getThongtindangkySet()) {
                    System.out.println("tt" + tt.getXeId());
                    tt.setXeId(null); // Thiết lập khóa ngoại thành null
                    session.update(tt); // Cập nhật đối tượng
                }

                // Xóa đối tượng Xe
                session.delete(xe);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Xe getXeById(long id) {
        Session s = factory.getObject().getCurrentSession();
        return s.get(Xe.class, id);
    }

}
