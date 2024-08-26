package com.qlbdx.pojo;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-26T16:03:13")
@StaticMetamodel(Rating.class)
public class Rating_ { 

    public static volatile SingularAttribute<Rating, String> nhanxet;
    public static volatile SingularAttribute<Rating, Baidoxe> baiDoid;
    public static volatile SingularAttribute<Rating, Date> createdDate;
    public static volatile SingularAttribute<Rating, Integer> rate;
    public static volatile SingularAttribute<Rating, Boolean> active;
    public static volatile SingularAttribute<Rating, Long> id;
    public static volatile SingularAttribute<Rating, Date> updatedDate;
    public static volatile SingularAttribute<Rating, User> userId;

}