package com.INovus.service.impl;

import com.INovus.enteties.User;
import com.INovus.enteties.UserRole;
import com.INovus.forms.SingUpForm;
import com.INovus.repositories.UserRepository;
import com.INovus.service.exception.UserAlredyExistException;
import com.INovus.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexander Abugaliev on 17.01.17.
 */

@Service
public class UserServiceImpl implements com.INovus.service.UserService {
    @Autowired
    UserRepository userRepository;

    public long createFromForm(SingUpForm singUpForm) {
        if (userRepository.existsByName(singUpForm.getUsername())) {
            throw new UserAlredyExistException("User with name: ".concat(singUpForm.getUsername()).concat(" already exist."));
        }
        User newUser = new User();
        newUser.setUsername(singUpForm.getUsername());
        newUser.setPassword(singUpForm.getPassword());

        UserRole newUserRole = new UserRole();
        newUserRole.setRole("ROLE_USER");
        newUserRole.setUser(newUser);

        Set<UserRole> rolesSet = new HashSet<UserRole>();
        rolesSet.add(newUserRole);

        newUser.setUserRole(rolesSet);
        newUser.setEnabled(true);
        userRepository.save(newUser);
        return newUser.getId();
    }

    public User findById(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("User not found. By id: ".concat(id.toString()));
        } else {
            return user;
        }
    }

    public User findByUserName(String name) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new UserNotFoundException("User not found. By name: ".concat(name));
        } else {
            return user;
        }
    }
}
