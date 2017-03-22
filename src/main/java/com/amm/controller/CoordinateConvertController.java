package com.amm.controller;

import com.amm.service.CoordinateConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ThinkPad on 2017-03-10.
 */
@RequestMapping("api/convert")
@Controller
public class CoordinateConvertController {

    @Autowired
    private CoordinateConvertService coordinateConvertService;


    @RequestMapping(method = RequestMethod.GET)
    public void covert(){
        coordinateConvertService.convert();
    }
}
