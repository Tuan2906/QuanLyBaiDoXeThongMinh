/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.controllers;

import com.qlbdx.dto.BaiDoXeWrapper;
import com.qlbdx.pojo.Baidoxe;
import com.qlbdx.pojo.Baidoxepic;
import com.qlbdx.pojo.Chitietkhudo;
import com.qlbdx.pojo.Gia;
import com.qlbdx.pojo.Khudoxe;
import com.qlbdx.pojo.Phuongtien;
import com.qlbdx.repository.PictureRepository;
import com.qlbdx.repository.impl.ChoDoXeRepositoryImpl;
import com.qlbdx.service.BaiDoXeService;
import com.qlbdx.service.ChiTietKhuDoXeService;
import com.qlbdx.service.ChoDoXeService;
import com.qlbdx.service.GiaService;
import com.qlbdx.service.KhuDoXeService;
import com.qlbdx.service.PhuongTienService;
import com.qlbdx.service.PictureBaiDoXeService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author quang
 */
@Controller
@PropertySource("classpath:messages.properties")
@ControllerAdvice
public class BaiDoXeController {

    @Autowired
    private BaiDoXeService baiSer;
    @Autowired
    private GiaService giaSer;
    @Autowired
    private KhuDoXeService khuSer;
    @Autowired
    private ChoDoXeService choDoXeService;
    @Autowired
    private ChiTietKhuDoXeService chitietKhuSer;
    @Autowired
    private PhuongTienService ptSer;

    @Autowired
//    private PictureBaiDoXeService picSer;
//    @Autowired
    private Environment env;

    @ModelAttribute
    public void commonAttribute(Model model) {
        model.addAttribute("gia", this.giaSer.getGia());
        model.addAttribute("phuongtien", this.ptSer.getPhuongTien());
        model.addAttribute("khudoXe", this.khuSer.getKhuDoXe(null));
        model.addAttribute("chiTietKhuDoXe", this.chitietKhuSer.getChiTietKhuDoXe(null));

//        model.addAttribute("picBaiXe", this.picSer.getPic());
    }

    @GetMapping("/baiDo")
    public String baiDoView(Model model) {
        model.addAttribute("baiDoXe", new Baidoxe());
        return "baido";
    }

    @PostMapping("/baiDo")
    public String createBaiDoView(Model model,
            @ModelAttribute(value = "baiDoXe") @Valid Baidoxe p, @RequestParam("files") MultipartFile[] files,
            BindingResult rs) {
        System.out.println("vo dat" + rs);
        if (rs.hasErrors()) {
            System.out.println("vo nay" + rs.getAllErrors());
            return "baido";
        }
        System.out.println("dada" + p.getThoigiandongcua().before(p.getThoigiancua()));
        if (p.getThoigiandongcua().before(p.getThoigiancua())) {
            model.addAttribute("timeError", "Thời gian đóng cửa không được trước thời gian mở cửa.");
            return "baido";
        }

        if (files == null || files.length == 0 || (files.length == 1 && files[0].isEmpty())) {

            model.addAttribute("timeError", "Vui lòng chọn ít nhất một ảnh.");
            return "baido"; // Quay lại trang nếu không có ảnh
        }
        try {
            System.out.println(p.getThoigiancua() + "\n" + p.getThoigiandongcua());
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    System.out.println("File name: " + file.getOriginalFilename());
                    System.out.println("File size: " + file.getSize());
                    // Thực hiện các xử lý khác với file
                }
            }
            Map<String, Baidoxepic> epicMap = new HashMap<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    Baidoxepic epic = new Baidoxepic();
                    epic.setFile(file);
                    epic.setImage(file.getOriginalFilename()); // Hoặc sử dụng thuộc tính khác làm khóa
                    epicMap.put(epic.getImage(), epic); // Dùng tên tệp làm khóa
                }
            }
            Set<Baidoxepic> epicSet = new HashSet<>(epicMap.values());
            p.setBaidoxepicSet(epicSet);
            this.baiSer.addBaiDoXe(p);
            return "redirect:/baiDo";
        } catch (Exception ex) {
            System.out.println("loi h");
            model.addAttribute("timeError", ex.getMessage());
        }
        return "baido";
    }

    @GetMapping("/baiDo/{baido_id}")
    public String baiDoView(Model model, @PathVariable(value = "baido_id") int id) {
        System.out.println("hung dl" + id);
        List<Object[]> chiTietBaiXe = this.baiSer.getChiTietBaiXeById(id);

        System.out.println("hung dldadda" + chiTietBaiXe);
        BaiDoXeWrapper baiDoXeWrapper = new BaiDoXeWrapper();
        Baidoxe baiDoXe = new Baidoxe();
        List<Chitietkhudo> khuDoXeList = new ArrayList<>();

        // Assume you know the index of the details you need
        if (!chiTietBaiXe.isEmpty()) {
            Object[] details = chiTietBaiXe.get(0); // Or any logic to get the appropriate detail
            baiDoXe.setId((Integer) details[0]);
            baiDoXe.setDiachi((String) details[1]);
            baiDoXe.setTenBai((String) details[2]);
            baiDoXe.setThoigiancua((Date) details[3]);
            baiDoXe.setThoigiandongcua((Date) details[4]);
            // Add more fields as needed
        }

        System.out.println("aaa" + baiDoXe.getThoigiancua());
        // Populate the wrapper
        baiDoXeWrapper.setBaidoxe(baiDoXe);
        baiDoXeWrapper.setKhuDoXeList(khuDoXeList);

        // Add to the model
        model.addAttribute("baiDoXeWrapper", baiDoXeWrapper);
        model.addAttribute("chiTietBaiXe", chiTietBaiXe);

        return "chiTietbaiDo";
    }

    @PostMapping("/updateBaiDo")
    public String updateBaiDoView(
            Model model,
            @ModelAttribute("baiDoXeWrapper") @Valid BaiDoXeWrapper baiDoXeWrapper,
            BindingResult bindingResult,
            HttpServletRequest request) {

        Baidoxe baidoxe = baiDoXeWrapper.getBaidoxe();
        List<Chitietkhudo> khuDoXeList = baiDoXeWrapper.getKhuDoXeList();
        Set<Integer> idSet = new HashSet<>();
        // Check for duplicate IDs
// Set để lưu trữ các ID mới sẽ được cập nhật
        Set<Integer> newIds = new HashSet<>();
        Integer oldDetailId = null;

        for (int i = 0; i < khuDoXeList.size(); i++) {
            Chitietkhudo khuDoXe = khuDoXeList.get(i);

            // Lấy giá trị oldDetailId từ request
            oldDetailId = Integer.valueOf(request.getParameter("khuDoXeList[" + i + "].oldDetailId"));
            System.out.println("old" + oldDetailId);
            System.out.println("new" + khuDoXe.getId());

            //Kiểm tra nếu oldDetailId trùng với newId
            if (newIds.contains(oldDetailId)) {
                System.out.println("Lỗi: oldDetailId " + oldDetailId + " trùng với ID mới.");
                model.addAttribute("duplicateError", "ID chi tiết " + oldDetailId + " trùng với ID mới. Vui lòng kiểm tra lại.");
                List<Object[]> chiTietBaiXe = baiSer.getChiTietBaiXeById(baiDoXeWrapper.getBaidoxe().getId());
                model.addAttribute("baiDoXeWrapper", baiDoXeWrapper);
                model.addAttribute("chiTietBaiXe", chiTietBaiXe);
                return "chiTietbaiDo";
            }

            // Thực hiện cập nhật chỉ nếu ID mới không trùng lặp và chưa được xử lý
            if (!idSet.contains(khuDoXe.getId())) {
                idSet.add(khuDoXe.getId());
                newIds.add(khuDoXe.getId());
            } else {
                System.out.println("Lỗi: ID " + khuDoXe.getId() + " bị trùng, không thể cập nhật.");
                model.addAttribute("duplicateError", "ID " + khuDoXe.getId() + " bị trùng, không thể cập nhật.");
                List<Object[]> chiTietBaiXe = baiSer.getChiTietBaiXeById(baiDoXeWrapper.getBaidoxe().getId());
                model.addAttribute("baiDoXeWrapper", baiDoXeWrapper);
                model.addAttribute("chiTietBaiXe", chiTietBaiXe);
                return "chiTietbaiDo";
            }

            // Optional: Update KhuDoXe references
        }

        try {
            System.out.println("vo upadate");
            for (int i = 0; i < khuDoXeList.size(); i++) {
                Chitietkhudo khuDoXe = khuDoXeList.get(i);

                // Lấy giá trị oldDetailId từ request
                oldDetailId = Integer.valueOf(request.getParameter("khuDoXeList[" + i + "].oldDetailId"));

                // Kiểm tra điều kiện cập nhật
                if (oldDetailId != null && !oldDetailId.equals(khuDoXe.getId())) {
                    System.out.println("ID Chi Tiết: " + khuDoXe.getId());
                    System.out.println("Ghi Chú: " + khuDoXe.getGhiChu());
                    System.out.println("Old Detail ID: " + oldDetailId);

                    // Gọi service để cập nhật tham chiếu ngoại khóa
                    choDoXeService.updateForeignKeyChoDo(oldDetailId, khuDoXe.getId(), baidoxe.getId());
                }
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update KhuDoXe references: " + e.getMessage());
            List<Object[]> chiTietBaiXe = baiSer.getChiTietBaiXeById(baiDoXeWrapper.getBaidoxe().getId());
            model.addAttribute("chiTietBaiXe", chiTietBaiXe);
            return "chiTietbaiDo";
        }

        // Handle validation errors
        if (bindingResult.hasErrors()) {
            model.addAttribute("baiDoXeWrapper", baiDoXeWrapper);
            List<Object[]> chiTietBaiXe = baiSer.getChiTietBaiXeById(baiDoXeWrapper.getBaidoxe().getId());
            model.addAttribute("chiTietBaiXe", chiTietBaiXe);
            model.addAttribute("errorMessage", "Validation failed.");
            return "chiTietbaiDo";
        }

        if (baidoxe.getThoigiandongcua().before(baidoxe.getThoigiancua())) {
            model.addAttribute("timeError", "Thời gian đóng cửa không được trước thời gian mở cửa.");
            List<Object[]> chiTietBaiXe = baiSer.getChiTietBaiXeById(baiDoXeWrapper.getBaidoxe().getId());
            model.addAttribute("chiTietBaiXe", chiTietBaiXe);
            return "chiTietbaiDo";
        }

        try {
            // Update Bãi Đỗ Xe
            baiSer.addBaiDoXe(baidoxe);

            model.addAttribute("successMessage", "Bãi Đỗ Xe đã cập nhật thành công!");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            List<Object[]> chiTietBaiXe = baiSer.getChiTietBaiXeById(baiDoXeWrapper.getBaidoxe().getId());
            model.addAttribute("chiTietBaiXe", chiTietBaiXe);
            model.addAttribute("errorMessage", "Update failed: " + e.getMessage());
            return "chiTietbaiDo";
        }
        return "redirect:/baido"; // Redirect to a success page or listing
    }

}
