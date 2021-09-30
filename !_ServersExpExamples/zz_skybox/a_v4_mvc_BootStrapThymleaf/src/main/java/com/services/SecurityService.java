package com.services;

import com.model.User;
import com.model.UserRoleEnum;
import com.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SecurityService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;
//    @Autowired
//    private PasswordEncoder encoder;

    public User register(String username, String password) {
        return repository.save(new User(username, password));
    }

    public User register(String username, String password, String email, String role) {
        // это проверяется в валидаторе
//        repository.findByUsername(username).ifPresent(user -> {
//            throw new IllegalArgumentException("User with that name already exists!");
//        });

//        Password encryptedPassword = Password.encrypted(encoder.encode(password));
//        String encode = encoder.encode(password);

        return repository.save(new User(username, password, email, role));
    }

    // ............................................................................................................

    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.repository.findAll(pageable);
    }

    // Standard methods Implements by Spring
    // ............................................................................................................

    public void save(User obj) {
        repository.save(obj);
    }

    public List<User> getListAll() {
        return (List<User>) repository.findAll();
    }

    public void deleteAllData() {
        repository.deleteAll();
    }

    public User getByID(int id) {
        return repository.findById(id).get();
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public List<User> search(String keyword) {
        return repository.search(keyword);
    }

    // ............................................................................................................

    public Optional<User> getUseByName(String username) {
        return repository.findByUsername(username);
    }

    public Optional<User> getUseById(int id) {
        return repository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.getUser("colibri");
        User user = userService.getUseByName(username).get();
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