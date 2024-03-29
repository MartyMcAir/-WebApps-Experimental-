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
package com.users;

import com.model.Password;
import com.model.User;
import com.model.Username;
import com.repositories.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

/**
 * Integration tests for {@link UserRepository}.
 *
 * @author Oliver Gierke
 */
public class UserRepositoryIntegrationTests extends AbstractIntegrationTests {

	@Autowired UserRepository users;

	/**
	 * @see #65
	 */
	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void repositoryRejectsUnencryptedPassword() {
		users.save(new User(new Username("olivergierke"), Password.raw("foobar")));
	}
}
