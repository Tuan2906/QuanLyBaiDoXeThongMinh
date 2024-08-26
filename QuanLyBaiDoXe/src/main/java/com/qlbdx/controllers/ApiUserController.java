/*
 * Click nbfs: nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs: nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.Mapper.UserMapper;
import com.qlbdx.components.JwtService;
import com.qlbdx.dto.UserDTO;
import com.qlbdx.pojo.User;
import com.qlbdx.service.UserService;
import java.security.Principal;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author quang
 */
@RestController
@RequestMapping("/api")
@CrossOrigin

public class ApiUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User user) {
        System.out.println("4121121124124124124");
        if (this.userService.authUser(user.getUsername(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getUsername());
            user.setLastLogin(new Date());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/loginGG")
    @CrossOrigin
    public ResponseEntity<String> loginGG(@RequestBody User user) {

        try {
            this.userService.getUserByUsername(user.getUsername());
            String token = this.jwtService.generateTokenLogin(user.getUsername());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception ex) {
            
            this.userService.addUser(user);
            String token = this.jwtService.generateTokenLogin(user.getUsername());

            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }

    }

    @PostMapping(path = "/users",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<?> addUser(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar
    ) {
        try {
            User user = this.userService.addUser(params, avatar);
            return new ResponseEntity<>(user, HttpStatus.CREATED);

        } catch (RuntimeException ex) {
            System.err.println("Lỗi khác: " + ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<UserDTO> details(Principal user
    ) {
        User u = this.userService.getUserByUsername(user.getName());
        return new ResponseEntity<>(UserMapper.toDTO(u), HttpStatus.OK);
    }

    @PostMapping(path = "/user/{user_id}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @CrossOrigin
    public ResponseEntity<?> UpdateUser(@RequestParam Map<String, String> params, @PathVariable(value = "user_id") int id, @RequestPart(required = false) MultipartFile avatar
    ) {
        try {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");

            params.put("id", String.valueOf(id));
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
            this.userService.updateUser(params, avatar);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/user/{user_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "user_id") int id
    ) {
        System.out.println("Bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

        try {
            this.userService.deleteUser(this.userService.getUserById(id));
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
