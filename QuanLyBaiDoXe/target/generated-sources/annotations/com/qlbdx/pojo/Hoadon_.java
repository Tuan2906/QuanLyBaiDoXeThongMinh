package com.qlbdx.pojo;

import com.qlbdx.pojo.Thongtindangky;
import com.qlbdx.pojo.Userhoantien;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-26T16:03:13")
@StaticMetamodel(Hoadon.class)
public class Hoadon_ { 

    public static volatile SingularAttribute<Hoadon, String> uid;
    public static volatile SingularAttribute<Hoadon, Date> ngayCapNhat;
    public static volatile SingularAttribute<Hoadon, Long> id;
    public static volatile SingularAttribute<Hoadon, Double> soTien;
    public static volatile SetAttribute<Hoadon, Userhoantien> userhoantienSet;
    public static volatile SingularAttribute<Hoadon, Thongtindangky> userId;

}