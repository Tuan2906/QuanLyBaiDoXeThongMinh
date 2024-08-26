package com.qlbdx.pojo;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Rating;
import com.qlbdx.pojo.Userhoantien;
import com.qlbdx.pojo.Xe;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-26T16:03:13")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Date> lastLogin;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, Date> dateJoined;
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile SetAttribute<User, Baidoxe> baidoxeSet;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SetAttribute<User, Rating> ratingSet;
    public static volatile SetAttribute<User, Userhoantien> userhoantienSet;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> userRole;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SetAttribute<User, Xe> xeSet;

}