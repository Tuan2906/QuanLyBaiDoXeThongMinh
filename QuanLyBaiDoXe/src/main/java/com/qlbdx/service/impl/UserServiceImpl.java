/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.qlbdx.pojo.User;
import com.qlbdx.repository.UserRepository;
import com.qlbdx.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.qlbdx.repository.XeRepository;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author quang
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public Map<String, Object> getUsers(Map<String, String> params) {
        return userRepo.getUsers(params);
    }
    @Autowired
    private Cloudinary cloudinary;

//    @Autowired
//    private BCryptPasswordEncoder passswordEncoder;
    @Override
    public void addorUpdateUser(User u) {
        if (!u.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(u.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));

                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        u.setPassword(this.passEncoder.encode(u.getPassword()));
        userRepo.addorUpdateUser(u);
    }

    @Override
    public void deleteUser(User u) {
        try {
            userRepo.deleteUser(u);

        } catch (RuntimeException e) {
            // Xử lý ngoại lệ hoặc ghi log
            throw e; // Hoặc ném một ngoại lệ khác nếu cần
        }
    }

    @Override
    public User getUserByUsername(String name) {
        return userRepo.getUserByUsername(name);
    }

    @Override
    public User getUserById(long id) {
        return userRepo.getUserById(id);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.userRepo.authUser(username, password);
    }

    @Override
    public User addUser(Map<String, String> params, MultipartFile avatar) {

        User u = new User();
        u.setFirstName(params.getOrDefault("firstName", ""));
        u.setLastName(params.getOrDefault("lastName", ""));
        u.setPhone(params.getOrDefault("phone", ""));
        u.setEmail(params.getOrDefault("email", ""));
        u.setUsername(params.get("username"));
        u.setPassword(this.passEncoder.encode(params.get("password")));
        u.setUserRole("ROLE_USER");
        u.setActive(true);
        u.setDateJoined(new Date());
        if (!avatar.isEmpty()) {
            System.out.println("1211212");

            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                System.out.println(res.get("secure_url").toString());
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                System.out.println("ABC");
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("22222222222222222");
        System.out.println(u.getAvatar());
        try {
            this.userRepo.addUser(u);
        } catch (RuntimeException ex) {
            throw ex;
        }
        return u;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid User!");
        }
        System.out.println(u.getUserRole());
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getUserRole()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public User updateUser(Map<String, String> params, MultipartFile avatar) {
        User u = this.userRepo.getUserById(Long.parseLong(params.get("id")));
        u.setFirstName(params.getOrDefault("firstName", u.getFirstName()));
        u.setLastName(params.getOrDefault("lastName", u.getLastName()));
        u.setPhone(params.getOrDefault("phone", u.getPhone()));
        u.setEmail(params.getOrDefault("email", u.getEmail()));
        u.setUsername(params.getOrDefault("username", u.getUsername()));
        if (avatar != null) {
            if (!avatar.isEmpty()) {
                try {
                    Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                    System.out.println(res.get("secure_url").toString());
                    u.setAvatar(res.get("secure_url").toString());
                } catch (IOException ex) {
                    System.out.println("ABC");
                    Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            u.setAvatar(u.getAvatar());
        }
        System.out.println("11111111111111");
        try {
            this.userRepo.updateUser(u);
        } catch (RuntimeException ex) {
            throw ex;
        }
        return u;
    }

    @Override
    public User addUser(User u) {
        User s = new User();
        Random random = new Random();
        s.setUsername(u.getUsername());
        s.setPassword(this.passEncoder.encode("123456"));
        s.setActive(true);
        s.setAvatar("https://lh3.googleusercontent.com/a/ACg8ocLx4rU-d7-2UVY9JGYergqilaJFTbWA5w5ZrGhBK31r5QZv=s96-c");
        s.setDateJoined(new Date());
        long randomNumber = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        System.out.println(String.valueOf(randomNumber));
        s.setPhone(String.valueOf(randomNumber));
        s.setEmail(u.getUsername());
        s.setUserRole("ROLE_USER");
        s.setFirstName("");
        s.setLastName("");
        return this.userRepo.addUser(s);
    }

}
