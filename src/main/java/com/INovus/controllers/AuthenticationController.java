package com.INovus.controllers;

import com.INovus.enteties.User;
import com.INovus.forms.SingUpForm;
import com.INovus.service.UserService;
import com.INovus.service.exception.UserAlredyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Alexander Abugaliev on 17.01.17.
 */

@Controller
public class AuthenticationController implements AuthenticationSuccessHandler {
    @Autowired
    public UserService userService;
    @Autowired
    public UserDetailsService userDetailsService;
    @Autowired
    ApplicationContext context;

    @RequestMapping(value = "/sing-in", method = RequestMethod.GET)
    public ModelAndView showSingInForm(@RequestParam(value = "error", required = false) String error,
                                       @RequestParam(value = "logout", required = false) String logout,
                                       @CookieValue(value = "username", required = false) String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:welcome");
        } else {
            ModelAndView mav = new ModelAndView("sing-in");
            if (error != null) {
                mav.addObject("error", context.getMessage("form.sing-in.error.denyaccess", null, Locale.getDefault())); //TODO: set locale parameter for i18n
            }
            if (logout != null) {
                mav.addObject("message", context.getMessage("form.sing-in.message.sing-out", null, Locale.getDefault())); //TODO: set locale parameter for i18n
            }
            if (username != null) {
                mav.addObject("username", username);
            }
            return mav;
        }
    }

    @RequestMapping(value = "/sing-up", method = RequestMethod.GET)
    public ModelAndView showSingUpForm() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:welcome");
        } else {
            ModelAndView mav = new ModelAndView("sing-up");
            mav.addObject("singUpForm", new SingUpForm());
            return mav;
        }
    }

    @RequestMapping(value = "/sing-up", method = RequestMethod.POST)
    public ModelAndView singUp(@Validated @ModelAttribute("singUpForm") SingUpForm singUpForm, BindingResult result, HttpServletResponse response) {
        if (result.hasErrors()) {
            return new ModelAndView("sing-up", result.getModel());
        }
        try {
            userService.createFromForm(singUpForm);
        } catch (UserAlredyExistException ex) {
            result.rejectValue("username", "error.username", context.getMessage("from.sing-up.error.username", null, Locale.getDefault())); //TODO: set locale parameter for i18n
            return new ModelAndView("sing-up", result.getModel());
        }
        authenticateUser(userService.findByUserName(singUpForm.getUsername()));
        response.addCookie(new Cookie("username", singUpForm.getUsername()));
        return new ModelAndView("redirect:welcome");
    }

    private void authenticateUser(User user) {
        UserDetails newUserDetails = userDetailsService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUserDetails, newUserDetails.getPassword(), newUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.addCookie(new Cookie("username", authentication.getName()));
        httpServletResponse.sendRedirect("welcome");
    }
}
