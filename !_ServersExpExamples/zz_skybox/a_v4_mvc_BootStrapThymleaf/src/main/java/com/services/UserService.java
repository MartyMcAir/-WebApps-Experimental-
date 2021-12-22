package com.services;

import com.model.UserHere;
import com.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UserRepository repository;
//    @Autowired
//    private PasswordEncoder encoder;

    public UserHere register(String username, String password) {
        return repository.save(new UserHere(username, password));
    }

    public UserHere register(String username, String password, String email, String role) {
        // это проверяется в валидаторе
//        repository.findByUsername(username).ifPresent(user -> {
//            throw new IllegalArgumentException("User with that name already exists!");
//        });

//        Password encryptedPassword = Password.encrypted(encoder.encode(password));
//        String encode = encoder.encode(password);

        return repository.save(new UserHere(username, password, email, role));
    }

    // ............................................................................................................

    public Page<UserHere> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.repository.findAll(pageable);
    }

    // Standard methods Implements by Spring
    // ............................................................................................................

    public void save(UserHere obj) {
        repository.save(obj);
    }

    public List<UserHere> getListAll() {
        return (List<UserHere>) repository.findAll();
    }

    public void deleteAllData() {
        repository.deleteAll();
    }

    public UserHere getByID(int id) {
        return repository.findById(id).get();
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public List<UserHere> search(String keyword) {
        return repository.search(keyword);
    }

    // ............................................................................................................

    public Optional<UserHere> getUseByName(String username) {
        return repository.findByUsername(username);
    }

    public Optional<UserHere> getUseById(int id) {
        return repository.findById(id);
    }

    public Optional<UserHere> getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

}