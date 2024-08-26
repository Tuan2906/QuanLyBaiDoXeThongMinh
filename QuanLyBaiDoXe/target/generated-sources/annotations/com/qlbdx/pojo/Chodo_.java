package com.qlbdx.pojo;

import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Chitietkhudo;
import com.qlbdx.pojo.State;
import com.qlbdx.pojo.Thongtindangky;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-26T16:03:13")
@StaticMetamodel(Chodo.class)
public class Chodo_ { 

    public static volatile SingularAttribute<Chodo, Double> khoangCach;
    public static volatile SingularAttribute<Chodo, Chitietkhudo> khuDoXeid;
    public static volatile SingularAttribute<Chodo, State> stateId;
    public static volatile SingularAttribute<Chodo, Integer> vitri;
    public static volatile SingularAttribute<Chodo, Long> id;
    public static volatile SetAttribute<Chodo, Thongtindangky> thongtindangkySet;
    public static volatile SingularAttribute<Chodo, Baidoxe> baiDoXeid;

}