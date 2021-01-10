package com.hellokoding.account.service;

import com.hellokoding.account.model.Role;
import com.hellokoding.account.model.User;
import com.hellokoding.account.model.UserRoleEnum;
import com.hellokoding.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = userRepository.findByUsername(username);
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : user.getRoles()) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userService.getUser("colibri");
        User user = userService.findByUsername("root");
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

        // if add in user field _ Error..
//        Set<UserRoleEnum> roles1 = new HashSet<>();
//        roles1.add(UserRoleEnum.USER);
//        user.setRolesUser(roles1);

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);

        return userDetails;
    }


}
