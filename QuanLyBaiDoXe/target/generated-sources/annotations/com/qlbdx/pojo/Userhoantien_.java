package com.qlbdx.pojo;

import com.qlbdx.pojo.Hoadon;
import com.qlbdx.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-26T16:03:13")
@StaticMetamodel(Userhoantien.class)
public class Userhoantien_ { 

    public static volatile SingularAttribute<Userhoantien, Date> ngayHoan;
    public static volatile SingularAttribute<Userhoantien, Hoadon> hoaDonid;
    public static volatile SingularAttribute<Userhoantien, Long> id;
    public static volatile SingularAttribute<Userhoantien, User> userId;

}