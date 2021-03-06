/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.model;


import javax.persistence.*;
import java.util.Objects;

/**
 * A {@link User} domain object. The primary entity of this example. Basically a combination of a {@link example.users.Username} and
 * {@link Password}.
 *
 * @author Oliver Gierke
 */
@Entity
public class User {

    private @GeneratedValue
    @Id
    Long id;
    private final Username username;
    private final Password password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                '}';
    }

    public User(Long id, Username username, Password password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Username username, Password password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    User() {
        this.username = null;
        this.password = null;
    }

    /**
     * Makes sure only {@link User}s with encrypted {@link Password} can be persisted.
     */
    @PrePersist
    @PreUpdate
    void assertEncrypted() {

        if (!password.isEncrypted()) {
            throw new IllegalStateException("Tried to persist/load a user with a non-encrypted password!");
        }
    }
}
