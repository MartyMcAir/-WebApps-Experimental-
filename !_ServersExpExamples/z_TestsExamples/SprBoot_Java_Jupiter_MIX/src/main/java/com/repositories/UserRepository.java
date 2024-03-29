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
package com.repositories;

import java.util.Optional;

import com.model.User;
import com.model.Username;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A Spring Data repository to persist {@link User}s.
 *
 * @author Oliver Gierke
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    /**
     * Returns the user with the given {@link Username}.
     *
     * @param username can be {@literal null}.
     * @return
     */
    Optional<User> findByUsername(Username username);
}
