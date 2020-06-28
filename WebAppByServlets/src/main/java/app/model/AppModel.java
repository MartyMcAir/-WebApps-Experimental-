package app.model;

import app.entities.AppUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppModel {
    private static AppModel instance = new AppModel();

    private List<AppUser> model;

    public static AppModel getInstance() {
        return instance;
    }

    private AppModel() {
        model = new ArrayList<>();
    }

    public void add(AppUser user) {
        model.add(user);
    }

    public List<String> list() {
        return model.stream()
                .map(AppUser::getName)
                .collect(Collectors.toList());
    }
}