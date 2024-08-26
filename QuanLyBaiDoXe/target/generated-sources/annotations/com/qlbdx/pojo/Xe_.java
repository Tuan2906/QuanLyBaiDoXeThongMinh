package com.qlbdx.pojo;

import com.qlbdx.pojo.Thongtindangky;
import com.qlbdx.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-26T16:03:13")
@StaticMetamodel(Xe.class)
public class Xe_ { 

    public static volatile SingularAttribute<Xe, String> image;
    public static volatile SingularAttribute<Xe, String> tenXe;
    public static volatile SingularAttribute<Xe, Long> id;
    public static volatile SingularAttribute<Xe, User> userId;
    public static volatile SingularAttribute<Xe, String> bienSo;
    public static volatile SetAttribute<Xe, Thongtindangky> thongtindangkySet;

}