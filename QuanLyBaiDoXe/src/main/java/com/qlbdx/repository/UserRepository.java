package com.qlbdx.repository;

import com.qlbdx.pojo.User;
import java.util.List;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author quang
 *
 */
public interface UserRepository {

    Map<String, Object> getUsers(Map<String, String> params);

    void addorUpdateUser(User u);

    void deleteUser(User u);

    User getUserByUsername(String name);

    User getUserById(long id);

    boolean authUser(String username, String password);

    User addUser(User u);

    User updateUser(User u);

}
