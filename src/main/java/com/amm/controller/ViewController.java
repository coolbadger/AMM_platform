package com.amm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by csw on 2016/3/16.
 */
@RestController
@RequestMapping("/api")
public class ViewController {

    @RequestMapping(value = "/goUserInfo", method = RequestMethod.GET)
    public ModelAndView goUserInfo() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/userInfo");
        return modelAndView;
    }

    @RequestMapping(value = "/goBootStrapUI", method = RequestMethod.GET)
    public ModelAndView goBootStrapUI() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/bootStrapUI");

        return modelAndView;
    }

    @RequestMapping(value = "/goHomeUI", method = RequestMethod.GET)
    public ModelAndView goHomeUI() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home");

        return modelAndView;
    }

    @RequestMapping(value = "/goLogin", method = RequestMethod.GET)
    public ModelAndView goLoginUI() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");

        return modelAndView;
    }

    @RequestMapping(value = "/goEasyUI", method = RequestMethod.GET)
    public ModelAndView goEasyUI() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/staffInfo");

        return modelAndView;
    }

    @RequestMapping(value = "/goAMMHomeUI", method = RequestMethod.GET)
    public ModelAndView goAMMHomeUI() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/AMMHome");

        return modelAndView;
    }

}
