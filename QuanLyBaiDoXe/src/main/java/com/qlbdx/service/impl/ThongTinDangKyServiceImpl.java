/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.qlbdx.Mapper.DateMapper;
import com.qlbdx.dto.LichSuDangKyChoDoDTO;
import com.qlbdx.dto.ThongTinDangKyDTO_v2;
import com.qlbdx.pojo.Chodo;
import com.qlbdx.pojo.Hoadon;
import com.qlbdx.pojo.Thongtindangky;
import com.qlbdx.pojo.Xe;
import com.qlbdx.repository.ChoDoXeRepository;
import com.qlbdx.repository.HoaDonRepository;
import com.qlbdx.repository.ThongTinDangKiRepository;
import com.qlbdx.repository.XeRepository;
import com.qlbdx.service.ThongBaoThoiGianDoTuDong;
import com.qlbdx.service.ThongTinDangKyService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quang
 */
@Service
public class ThongTinDangKyServiceImpl implements ThongTinDangKyService {

    @Autowired
    private ThongTinDangKiRepository TTDKRep;

    @Autowired
    private ChoDoXeRepository chodoRepository;

    @Autowired
    private HoaDonRepository hoadonRepository;

    @Autowired
    private XeRepository xeRepository;
    
    @Autowired
    private ThongBaoThoiGianDoTuDong tbtdSer;

    @Autowired
    private ThongTinDangKiRepository thongTinDangKiRepository;

    @Override
    public List<Thongtindangky> getThongTinDangKy(Map<String, String> params) {
        return TTDKRep.getThongTinDangKy(params);
    }

    @Override
    public List<Object[]> getThongTinChoDaDangKy(int ChoDoId, Map<String, String> params) {
        return this.TTDKRep.getThongTinChoDaDangKy(ChoDoId, params);
    }

    @Override
    public void HuyDangKy(int id) {
        this.TTDKRep.HuyDangKy(id);
    }

    @Override
    public void add_or_update(ThongTinDangKyDTO_v2 xe) {
        TTDKRep.add_or_update(xe);
//        List<Thongtindangky> overdueRegistrations = null;
//        if (overdueRegistrations != null){
//              List<Thongtindangky> a = 
//        }
//        List<Thongtindangky> overdueRegistrations = thongTinDangKiRepository.findByThoiGianRaBaiBefore();
//        tbtdSer.performScheduledTask2(overdueRegistrations);
    }

    private LichSuDangKyChoDoDTO convertToDTO(Thongtindangky registration) {
        LichSuDangKyChoDoDTO dto = new LichSuDangKyChoDoDTO();
        dto.setId(registration.getId());
        if (registration.getThoiGianVoBai() != null) {
            dto.setThoiGianVoBai(DateMapper.formatter.format(registration.getThoiGianVoBai()));
            System.out.println("d" + dto.getThoiGianVoBai());
        }
        dto.setIsHuy(registration.getIsHuy());

        dto.setThoiGianRaBai(DateMapper.formatter.format(registration.getThoiGianRaBai()));
        dto.setTenXe(registration.getXeId().getTenXe());
        dto.setVitri(registration.getChoDoid().getVitri());
        dto.setIdPT(registration.getChoDoid().getKhuDoXeid().getPhuongTienId().getId());
        dto.setId_chodo(registration.getChoDoid().getId());
        dto.setId_Bai(registration.getChoDoid().getBaiDoXeid().getId());
        dto.setId_khu(registration.getChoDoid().getKhuDoXeid().getId());

        dto.setTenBaiXe(registration.getChoDoid().getBaiDoXeid().getTenBai());
        dto.setDiaChiBaiXe(registration.getChoDoid().getBaiDoXeid().getDiachi());
        dto.setKhuDoXe(registration.getChoDoid().getKhuDoXeid().getKhuDoId().getTenDay());
        return dto;
    }

    @Override
    public List<LichSuDangKyChoDoDTO> getActiveRegistrationsByUserId(Long currentUserId) {

        List<Thongtindangky> registrations = TTDKRep.findAllActiveRegistrations(currentUserId);
        return registrations.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
