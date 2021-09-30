package com.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// fast
class UserAssertJ_Test {
    @Test
    void testUser() {
        User user = new User(1L, "Wim");

        Assertions.assertThat(user).isNotNull().satisfies(u -> {
            Assertions.assertThat(u.getId()).isEqualTo(1);
            Assertions.assertThat(u.getUsername().toString()).isEqualTo("Wim");
        });

    }
}