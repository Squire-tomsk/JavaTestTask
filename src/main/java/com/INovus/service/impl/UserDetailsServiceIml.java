package com.INovus.service.impl;

import com.INovus.enteties.UserRole;
import com.INovus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Alexander Abugaliev on 17.01.17.
 */
@Service
public class UserDetailsServiceIml implements UserDetailsService {
    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.INovus.enteties.User user = userService.findByUserName(s);
        List<GrantedAuthority> authorities = buildUserAuthorities(user.getUserRole());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(com.INovus.enteties.User user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getUsername(),
                user.getPassword(), user.isEnabled(),
                true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthorities(Set<UserRole> userRoles) {

        Set<GrantedAuthority> authoritiesSet = new HashSet<GrantedAuthority>();

        for (UserRole userRole : userRoles) {
            authoritiesSet.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> authoritiesList = new ArrayList<GrantedAuthority>(authoritiesSet);

        return authoritiesList;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
