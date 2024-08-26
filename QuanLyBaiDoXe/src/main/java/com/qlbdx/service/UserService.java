/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service;

import com.qlbdx.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author quang
 */
public interface UserService extends UserDetailsService {

    Map<String, Object> getUsers(Map<String, String> params);

    void addorUpdateUser(User u);

    void deleteUser(User u);

    User getUserByUsername(String name);

    User getUserById(long id);

    boolean authUser(String username, String password);

    User addUser(Map<String, String> params, MultipartFile avatar);

    User addUser(User u);

    User updateUser(Map<String, String> params, MultipartFile avatar);

}
