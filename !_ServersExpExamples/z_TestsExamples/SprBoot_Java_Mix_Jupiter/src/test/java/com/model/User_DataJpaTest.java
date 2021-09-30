package com.model;

import com.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// so long time
@DataJpaTest
class User_DataJpaTest {
    @Autowired
    private UserRepository repository;

    // TestContextBootstrapper [null]. Specify @BootstrapWith's 'value' attribute or make
    // the default bootstrapper class available.
    //  There are problems with the dependencies in the pom.xml file (if you are using Maven).
    //  There could be many reasons for that: a conflict in versions, or conflict declaring the same dependency
    //  twice: in the current pom.xml and also in the parent pom.xml, like using different versions.
    @Test
    void testSave() {
        User user = repository.save(new User(1, "Wim"));
        Assertions.assertThat(user).isNotNull();
    }

//    @Test
//    void testGet() {
//        User user = repository.getOne(1L);
//        Assertions.assertThat(user.getUsername()).isEqualTo("Wim");
//    }
}