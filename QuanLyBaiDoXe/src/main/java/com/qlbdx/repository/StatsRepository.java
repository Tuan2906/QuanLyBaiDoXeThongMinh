/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.qlbdx.repository;

import java.util.List;

/**
 *
 * @author quang
 */
public interface StatsRepository {
    List<Object[]> stats(int month);
}
