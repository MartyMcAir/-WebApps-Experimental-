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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import com.model.Password;
import com.model.User;
import com.model.Username;
import com.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Integration tests for {@link UserService}.
 *
 * @author Oliver Gierke
 */
public class UserServiceIntegrationTests extends AbstractIntegrationTests {

	@Autowired
	UserService userService;

	/**
	 * @see #65
	 */
	@Test
	public void encryptsPasswordWhenCreatingAUser() {

		User user = userService.register(new Username("olivergierke"), Password.raw("foobar"));

		assertThat(user.getPassword().isEncrypted(), is(true));
	}
}
