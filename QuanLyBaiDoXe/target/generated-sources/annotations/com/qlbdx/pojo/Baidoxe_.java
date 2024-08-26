package com.qlbdx.pojo;

import com.qlbdx.pojo.Baidoxepic;
import com.qlbdx.pojo.Chodo;
import com.qlbdx.pojo.Rating;
import com.qlbdx.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-26T16:03:13")
@StaticMetamodel(Baidoxe.class)
public class Baidoxe_ { 

    public static volatile SingularAttribute<Baidoxe, String> diachi;
    public static volatile SingularAttribute<Baidoxe, Date> thoigiandongcua;
    public static volatile SetAttribute<Baidoxe, Baidoxepic> baidoxepicSet;
    public static volatile SingularAttribute<Baidoxe, Date> thoigiancua;
    public static volatile SetAttribute<Baidoxe, Chodo> chodoSet;
    public static volatile SingularAttribute<Baidoxe, String> tenBai;
    public static volatile SingularAttribute<Baidoxe, Integer> id;
    public static volatile SetAttribute<Baidoxe, Rating> ratingSet;
    public static volatile SingularAttribute<Baidoxe, User> userId;

}