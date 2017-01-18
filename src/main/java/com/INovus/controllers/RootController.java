package com.INovus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alexander Abugaliev on 17.01.17.
 */
@Controller
public class RootController {

    @RequestMapping("/")
    public ModelAndView redirectToWelcome() {
        return new ModelAndView("redirect:welcome");
    }
}
