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

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Password implements CharSequence {
    final String password;
    transient boolean encrypted;

    public String getPassword() {
        return password;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    public Password(String password, boolean encrypted) {
        this.password = password;
        this.encrypted = encrypted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return encrypted == password1.encrypted &&
                Objects.equals(password, password1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, encrypted);
    }

    Password() {
        this.password = null;
        this.encrypted = true;
    }

    public static Password raw(String password) {
        return new Password(password, false);
    }

    public static Password encrypted(String password) {
        return new Password(password, true);
    }

    public String toString() {
        return encrypted ? password : "********";
    }


    // .....................................................................
    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}