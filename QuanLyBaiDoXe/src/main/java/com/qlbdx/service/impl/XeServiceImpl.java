/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.qlbdx.dto.XeDTO;
import com.qlbdx.pojo.User;
import com.qlbdx.pojo.Xe;
import com.qlbdx.repository.UserRepository;
import com.qlbdx.repository.XeRepository;
import com.qlbdx.service.XeService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author quang
 */
@Service
public class XeServiceImpl implements XeService {

    @Autowired
    private XeRepository xeRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
//       SecurityContextHolder.getContext().getAuthentication().getName()
        System.out.println("username" + userRepository.getUserByUsername("admin"));
        return userRepository.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()); // Tìm người dùng từ UserRepository
    }

    private XeDTO convertToDTO(Xe xe) {
        if (xe == null) {
            return null;
        }
        return new XeDTO(
                xe.getId(),
                xe.getTenXe(),
                xe.getBienSo(),
                xe.getImage(),
                xe.getUserId() != null ? xe.getUserId().getId() : null // Lấy userId từ đối tượng User
        );
    }

    @Override
    public List<XeDTO> getXe() {
        User currentUser = getCurrentUser();
        List<Xe> xeList = xeRepository.getXeByIdUser(currentUser);
        return xeList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Xe> getXeByIdUser(User u) {
        return this.xeRepository.getXeByIdUser(u);
    }

    private Xe convertToEntity(XeDTO xeDTO) {
        if (xeDTO == null) {
            return null;
        }
        if (xeDTO.getTenXe() == null || xeDTO.getBienSo() == null) {
            throw new IllegalArgumentException("TenXe and BienSo must not be null");
        }
        System.out.println("xe" + xeDTO.getBienSo() + xeDTO.getTenXe() + xeDTO.getImage());
        // Tìm User từ cơ sở dữ liệu dựa trên userId
        User user = getCurrentUser();
        System.out.println("user" + user.getId());
        return new Xe(
                xeDTO.getId(),
                xeDTO.getTenXe(),
                xeDTO.getBienSo(),
                xeDTO.getImage(),
                user
        );
    }

    @Override
    public void add_or_update(XeDTO xeDTO) {

        if (xeDTO.getFile() != null && !xeDTO.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(xeDTO.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                System.out.println(xeDTO.getFile());
                xeDTO.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            xeDTO.setImage(xeDTO.getImage());

        }
        System.out.println(xeDTO.getBienSo());
        System.out.println(xeDTO.getImage());
        System.out.println(xeDTO.getTenXe());
        Xe xe = convertToEntity(xeDTO);
        // Gọi phương thức của Repository để lưu Entity vào cơ sở dữ liệu
        this.xeRepository.add_or_update(xe);
    }

    @Override
    public XeDTO getXeById(long id) {
        return convertToDTO(this.xeRepository.getXeById(id));
    }

    @Override
    public void deleteXe(Long id) {
        System.out.println("id" + id);
        this.xeRepository.deleteXe(id);
        System.out.println("id pass");

    }
}
