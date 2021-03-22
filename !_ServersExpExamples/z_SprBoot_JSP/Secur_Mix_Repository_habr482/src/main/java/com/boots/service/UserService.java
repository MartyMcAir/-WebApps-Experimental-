package com.boots.service;

import com.boots.model.Role;
import com.boots.model.User;
import com.boots.model.enums.UserRoleEnum;
import com.boots.repository.RoleRepository;
import com.boots.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User s = new User();
        s.setUsername("root");
        // 1234
        s.setPassword("$2y$12$JPIWBBP6Te8xxrbFrBxrde/m1WiNIo9U0P/Pi1dLDbcRG0uMz.9Au");
        userRepository.save(s);

        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");

        return user;
    }

    // from javaguides
//    @Override
//    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername2(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority("USER"));
        grantedAuthoritySet.add(new SimpleGrantedAuthority("ADMIN"));

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUsername(), //
                user.getPassword(), grantedAuthoritySet);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                grantedAuthoritySet);
    }

    // from borajii tutorial
//    @Transactional(readOnly = true)
//    @Override
    public UserDetails loadUserByUsername3(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null) {

            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.disabled(!user.isEnabled());
            builder.password(user.getPassword());
            String[] authorities = user.getAuthorities()
                    .stream().map(a -> a.getAuthority()).toArray(String[]::new);

            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }

    //    @Override
    public UserDetails loadUserByUsername4(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUsername("colibri");
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);

        return userDetails;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    //..................................................................................................................
    public User findUserInfo(String userName) {
        String sql = "Select u.Username,u.Password "//
                + " from Users u where u.Username = ? ";
        Object[] params = new Object[]{userName};

//        try {
//            UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
//            return userInfo;
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
        return null;
    }

    public List<String> getUserRoles(String userName) {
        String sql = "Select r.User_Role "//
                + " from User_Roles r where r.Username = ? ";
        Object[] params = new Object[]{userName};
//        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);
        List<String> roles = new ArrayList<>();
        return roles;
    }
}
