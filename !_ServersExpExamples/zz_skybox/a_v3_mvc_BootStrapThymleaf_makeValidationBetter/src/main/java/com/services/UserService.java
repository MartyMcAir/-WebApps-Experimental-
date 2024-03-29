package com.services;

import com.model.User;
import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
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

//    public Optional<User> getUseByName(String username) {
//        return repository.findByUsername(username);
//    }
//
//    public Optional<User> getUseById(int id) {
//        return repository.findById(id);
//    }
//
//    public Optional<User> getUserByEmail(String email) {
//        return repository.findByEmail(email);
//    }

}