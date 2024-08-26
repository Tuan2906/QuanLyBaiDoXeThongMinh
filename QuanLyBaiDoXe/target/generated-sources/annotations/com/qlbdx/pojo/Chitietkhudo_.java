package com.qlbdx.pojo;

import com.qlbdx.pojo.Chodo;
import com.qlbdx.pojo.Gia;
import com.qlbdx.pojo.Khudoxe;
import com.qlbdx.pojo.Phuongtien;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-26T16:03:13")
@StaticMetamodel(Chitietkhudo.class)
public class Chitietkhudo_ { 

    public static volatile SingularAttribute<Chitietkhudo, String> ghiChu;
    public static volatile SingularAttribute<Chitietkhudo, Gia> giaId;
    public static volatile SetAttribute<Chitietkhudo, Chodo> chodoSet;
    public static volatile SingularAttribute<Chitietkhudo, Date> ngayCapNhat;
    public static volatile SingularAttribute<Chitietkhudo, Khudoxe> khuDoId;
    public static volatile SingularAttribute<Chitietkhudo, Integer> id;
    public static volatile SingularAttribute<Chitietkhudo, Phuongtien> phuongTienId;

}