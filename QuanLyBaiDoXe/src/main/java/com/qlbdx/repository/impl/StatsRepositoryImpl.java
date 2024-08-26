package com.qlbdx.repository.impl;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Chodo;
import com.qlbdx.pojo.Hoadon;
import com.qlbdx.pojo.Thongtindangky;
import com.qlbdx.pojo.Userhoantien;
import com.qlbdx.repository.StatsRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> stats(int month) {
        Session s = this.factory.getObject().getCurrentSession();

        String sql = "select  e.diachi, e.tenBai, sum(f.soTien) as TongTien, count(f.choDo_id) as soLuongCho\n"
                + "from (\n"
                + "	select a.soTien, b.id, b.choDo_id, d.baiDoXe_id ,c.id as idHoanTien, a.ngayCapNhat from thongtindangky b\n"
                + "	join hoadon a on a.user_id = b.id\n"
                + "	join chodo d on d.id = b.choDo_id\n"
                + "	left join userhoantien c on  a.id= c.hoaDon_id \n"
                + "	where (b.isHuy = 0 or c.id is null) and month(a.ngayCapNhat) =:month"
                + ") f\n"
                + "right join baidoxe e on f.baiDoXe_id = e.id\n"
                + "group by e.diachi, e.tenBai\n"
                + "order by TongTien desc";

        Query query = s.createNativeQuery(sql);
        query.setParameter("month", month);
        return query.getResultList();
        
    }

}
