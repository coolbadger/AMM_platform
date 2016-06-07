package com.amm.controller;

import com.amm.entity.Staff;
import com.amm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bilibili on 2015/10/23.
 */

@RestController
@RequestMapping("/api/staffs")
public class StaffController extends BaseController{

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Staff find(@PathVariable Integer id) {

        return staffService.find(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Staff> getAllStaff() {
        System.out.println("查找所有员工");

        return staffService.getAll();
    }

    @RequestMapping(method=RequestMethod.POST)
    public Staff create(@RequestBody(required = false) Staff staff) {

        System.out.println(staff);
        return staffService.save(staff);
    }
}
