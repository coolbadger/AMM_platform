package com.amm.service;

import com.amm.entity.Staff;

import java.util.List;

/**
 * Created by bilibili on 2015/10/23.
 */
public interface StaffService {
    public Staff find(Integer id);
    Staff create(Staff staff);

    List<Staff> getAll();

    Staff save(Staff staff);
}
