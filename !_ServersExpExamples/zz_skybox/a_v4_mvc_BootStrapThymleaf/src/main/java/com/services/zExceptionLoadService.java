package com.services;

import com.model.UserHere;
import com.model.UserRoleEnum;
import com.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

//@Service
public class zExceptionLoadService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UserRepository userRepository;

    // Security Core
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    // z_SprWebMVC_XMLConf_JSP\+SecurValidRole_hellokoding
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }

    // z_SprWebMVC_XMLConf_JSP\+SecurValidRole_hellokoding
    public void autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", username));
        }
    }

    // ...
    public UserHere myRegister(String username, String password, String role) {

        UserHere userHere = new UserHere(username, password);
        userHere.setRole(role);
        return userRepository.save(userHere);
    }

    // z_SprBoot_Thymleaf\+Secur_Registration_Pagination
    public UserHere register(String username, String password) {
        return userRepository.save(new UserHere(username, password));
    }

    // z_SprBoot_Thymleaf\+Secur_Registration_Pagination
    public UserHere register(String username, String password, String email, String role) {
//        Password encryptedPassword = Password.encrypted(encoder.encode(password));
//        String encode = encoder.encode(password);

        return userRepository.save(new UserHere(username, password, email, role));
    }

//    from Trash
//     ............................................................................................................

    public boolean saveUser(UserHere userHere) {
//        User userFromDB = userRepository.findByUsername(user.getUsername());
//        if (userFromDB != null) {
//            return false;
//        }

//        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userHere.setPassword(bCryptPasswordEncoder.encode(userHere.getPassword()));
        userRepository.save(userHere);
        return true;
    }

    // Правильно обязательно с проверкой?
    public boolean deleteUser(Long userId) {
//        if (userRepository.findById(userId).isPresent()) {
//            userRepository.deleteById(userId);
//            return true;
//        }
        return false;
    }

    public UserHere loadUserByUsername(String username) throws UsernameNotFoundException {
        UserHere s = new UserHere();
        s.setUsername("root");
        // 1234
        s.setPassword("$2y$12$JPIWBBP6Te8xxrbFrBxrde/m1WiNIo9U0P/Pi1dLDbcRG0uMz.9Au");
        userRepository.save(s);
        UserHere userHere = userRepository.findByUsername(username).get();
        if (userHere == null) throw new UsernameNotFoundException("User not found");
        return userHere;
    }

    public UserDetails loadUserByUsername4(String email) throws UsernameNotFoundException {
        UserHere userHere = userRepository.findByUsername("colibri").get();
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(userHere.getUsername(), userHere.getPassword(), roles);
        return userDetails;
    }

    public UserDetails loadUserByUsername3(String username) throws UsernameNotFoundException {
        UserHere userHere = userRepository.findByUsername(username).get();
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (userHere != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
//            builder.disabled(!user.isEnabled());
            builder.password(userHere.getPassword());
//            String[] authorities = user.getAuthorities().stream().map(a -> a.getAuthority()).toArray(String[]::new);
//            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }

    public UserDetails loadUserByUsername2(String username) throws UsernameNotFoundException {
        UserHere userHere = userRepository.findByUsername(username).get();
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority("USER"));
        grantedAuthoritySet.add(new SimpleGrantedAuthority("ADMIN"));
        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(userHere.getUsername(), //
                userHere.getPassword(), grantedAuthoritySet);
        return new org.springframework.security.core.userdetails.User(userHere.getUsername(), userHere.getPassword(),
                grantedAuthoritySet);
    }

    // Standard methods Implements by Spring
    // ............................................................................................................

    public void save(UserHere userHere) {
        userRepository.save(userHere);
    }

}