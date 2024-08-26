/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlbdx.service.impl;

import com.qlbdx.pojo.State;
import com.qlbdx.repository.StateRepository;
import com.qlbdx.service.StateService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quang
 */
@Service
public class StateServiceImpl implements StateService{

    @Autowired
    private StateRepository stateRep;
    @Override
    public List<State> getState() {
            return stateRep.getState();
    }
    
}
