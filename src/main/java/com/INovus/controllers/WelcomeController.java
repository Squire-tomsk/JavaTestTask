package com.INovus.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alexander Abugaliev on 17.01.17.
 */

@Controller
public class WelcomeController {
    @RequestMapping("/welcome")
    public ModelAndView showWelcomForAuntificatedUser() {
        ModelAndView mav = new ModelAndView("welcome");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        mav.addObject("username", username);
        return mav;
    }
}
