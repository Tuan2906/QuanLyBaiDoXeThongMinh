/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.service;

import com.qlbdx.dto.XeDTO;
import com.qlbdx.pojo.User;
import com.qlbdx.pojo.Xe;
import java.util.List;

/**
 *
 * @author quang
 */
public interface XeService {
    List<XeDTO> getXe();  // Phương thức này lấy xe của người dùng hiện tại
    void add_or_update(XeDTO xe);
    XeDTO getXeById(long id); // Thêm phương thức lấy xe theo ID
    void deleteXe(Long id);
    public List<Xe> getXeByIdUser(User u);
}
