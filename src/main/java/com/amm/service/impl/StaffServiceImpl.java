package com.amm.service.impl;

import com.amm.entity.Staff;
import com.amm.repository.StaffRepository;
import com.amm.service.StaffService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bilibili on 2015/10/23.
 */
@Component("staffService")
@Scope("prototype")
public class StaffServiceImpl implements StaffService {

    @Resource
    private StaffRepository staffRepository;


    public Staff find(Integer id) {
        return staffRepository.findOne(id);
    }


    public Staff create(Staff staff) {
        return staffRepository.save(staff);
    }

    public List<Staff> getAll() {

        return (List<Staff>) staffRepository.findAll();
    }

    public Staff save(Staff staff) {

        return staffRepository.save(staff);
    }
}
