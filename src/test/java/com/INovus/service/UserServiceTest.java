package com.INovus.service;

import com.INovus.enteties.User;
import com.INovus.enteties.UserRole;
import com.INovus.forms.SingUpForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexander Abugaliev on 17.01.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:WEB-INF/applicationContext.xml"})
public class UserServiceTest {
    @Autowired
    public UserService userService;

    @Test
    public void userCreationFromForm() {
        boolean hasUserRole = false;
        SingUpForm singUpForm = new SingUpForm();
        singUpForm.setUsername("TestUser");
        singUpForm.setPassword("1234");
        singUpForm.setPasswordConfirmation("1234");
        singUpForm.setPasswordConfirmed(true);

        long id = userService.createFromForm(singUpForm);
        User testUser = userService.findById(id);

        assertEquals(singUpForm.getUsername(), testUser.getUsername());
        assertEquals(singUpForm.getPassword(), testUser.getPassword());
        for (UserRole role : testUser.getUserRole()) {
            assertEquals(role.getUser().getUsername(), testUser.getUsername());
            if (role.getRole().equals("ROLE_USER")) {
                hasUserRole = true;
            }
        }
        assertTrue(hasUserRole);
    }
}