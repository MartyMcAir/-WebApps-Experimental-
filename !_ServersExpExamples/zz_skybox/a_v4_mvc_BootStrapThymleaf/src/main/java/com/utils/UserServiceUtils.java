package com.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.UserHere;
import com.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserServiceUtils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService service;

    // id author title size
    public List<UserHere> getFilteredList(String idKey, String userNameKey, String emailKey, String roleKey, String regex) {
        List<UserHere> listAll = service.getListAll();
        Set<UserHere> set = new HashSet<>();

        if (regex == null) { // if (regex.isEmpty())
            for (UserHere obj : listAll) {
                if (!idKey.isEmpty())
                    if (String.valueOf(obj.getId()).contains(idKey)) {
                        set.add(obj);
                        continue;
                    }
                if (!userNameKey.isEmpty())
                    if (obj.getUsername().contains(userNameKey)) {
                        set.add(obj);
                        continue;
                    }
                if (!roleKey.isEmpty())
                    if (obj.getRole().contains(roleKey)) {
                        set.add(obj);
                        continue;
                    }
                if (!emailKey.isEmpty())
                    if (String.valueOf(obj.getEmail()).contains(emailKey))
                        set.add(obj);
            }
            return new ArrayList<>(set);
        }

        // if regex True (on)

        boolean flag;
        for (UserHere obj : listAll) {
            flag = false;

            if (!idKey.isEmpty()) {
                Matcher matcher = Pattern.compile(idKey).matcher(String.valueOf(obj.getId()));
                flag = isFlag(set, flag, obj, matcher);
                if (flag) continue;
            }
            if (!userNameKey.isEmpty()) {
                Matcher matcher = Pattern.compile(userNameKey).matcher(obj.getUsername());
                flag = isFlag(set, flag, obj, matcher);
                if (flag) continue;
            }
            if (!roleKey.isEmpty()) {
                Matcher matcher = Pattern.compile(roleKey).matcher(obj.getRole());
                flag = isFlag(set, flag, obj, matcher);
                if (flag) continue;
            }
            if (!emailKey.isEmpty()) {
                Matcher matcher = Pattern.compile(emailKey).matcher(String.valueOf(obj.getEmail()));
                while (matcher.find()) set.add(obj);
            }
        }
        return new ArrayList<>(set);
    }

    private boolean isFlag(Set<UserHere> set, boolean flag, UserHere obj, Matcher matcher) {
        while (matcher.find()) {
            set.add(obj);
            flag = true;
        }
        return flag;
    }

    public List<UserHere> getSearchList(String keyword, String regex) {
        if (regex.isEmpty()) return service.search(keyword);

        Set<UserHere> set = new HashSet<>();
        List<UserHere> listAll = service.getListAll();

        for (UserHere obj : listAll) {
            for (String field : obj.getAllFields()) {
                Pattern pattern = Pattern.compile(keyword);
                Matcher matcher = pattern.matcher(field);

                while (matcher.find()) set.add(obj);
            }
        }

        return new ArrayList<>(set);
    }

    public List<UserHere> getDataFromJson(String fileName) {
        List<UserHere> list = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            list = objectMapper.readValue(
                    getClass().getClassLoader().getResourceAsStream(fileName),
                    new TypeReference<List<UserHere>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}