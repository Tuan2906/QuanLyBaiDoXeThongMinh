/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author quang
 */
public class ListMapper<T,R> {
    
    // Phương thức để chuyển đổi danh sách các đối tượng thành một danh sách khác
     public static <T, R> List<R> mapList(List<T> inputList, Function<T, R> mapperFunction) {
        List<R> resultList = new ArrayList<>();
        inputList.forEach(input -> resultList.add(mapperFunction.apply(input)));
        return resultList;
    }
     public static <T> List<T> mapListObject(List<Object[]> inputList, Function<Object[], T> mapperFunction) {
        List<T> resultList = new ArrayList<>();
        for (Object[] obj : inputList) {
            resultList.add(mapperFunction.apply(obj));
        }
         System.out.println(resultList.isEmpty());
        return resultList;
    }
     
}

