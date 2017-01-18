package com.INovus.controllers;

import com.INovus.forms.SingUpForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

/**
 * Created by Alexander Abugaliev on 17.01.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:WEB-INF/applicationContext.xml",
        "classpath:WEB-INF/dispatcher-servlet.xml",
        "classpath:WEB-INF/spring-security.xml"})
@TestExecutionListeners(listeners = {ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
public class AuthenticationControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).dispatchOptions(true).build();
    }

    @Test
    @WithAnonymousUser
    public void showSingInFormShouldShowSingInForm() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/sing-in");
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("sing-in"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("error"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("message"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("username"));
    }

    @Test
    @WithMockUser
    public void showSingInFormShouldRedirectToWelcomePage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/sing-in");
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("welcome"));
    }

    @Test
    @WithAnonymousUser
    public void showSingInFormShouldShowSingInFormWithError() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/sing-in").param("error", "");
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("sing-in"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("error"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("message"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("username"));
    }

    @Test
    @WithAnonymousUser
    public void showSingInFormShouldShowSingInFormWithMessage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/sing-in").param("logout", "");
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("sing-in"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("error"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("message"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("username"));
        ;
    }

    @Test
    @WithAnonymousUser
    public void showSingInFormShouldShowSingInFormWithUsernameFromCookie() throws Exception {
        String testname = "testname";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/sing-in").cookie(new Cookie("username", testname));
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("sing-in"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("error"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("message"))
                .andExpect(MockMvcResultMatchers.model().attribute("username", testname));
    }

    @Test
    @WithMockUser
    public void showSingUpFormRedirectToWelcomePage() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/sing-up");
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("welcome"));
    }

    @Test
    @WithAnonymousUser
    public void showSingUpFormShowSingUpForm() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/sing-up");
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("sing-up"))
                .andExpect(MockMvcResultMatchers.model().attribute("singUpForm", new SingUpForm()));
    }
}