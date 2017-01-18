package com.INovus.service;

import com.INovus.enteties.User;
import com.INovus.forms.SingUpForm;

/**
 * Created by Alexander Abugaliev on 17.01.17.
 */
public interface UserService {
    long createFromForm(SingUpForm singUpForm);

    User findById(Long id);

    User findByUserName(String name);
}
