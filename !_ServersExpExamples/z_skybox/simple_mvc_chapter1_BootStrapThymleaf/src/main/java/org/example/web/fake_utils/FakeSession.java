package org.example.web.fake_utils;

import org.example.web.model.User;
import org.springframework.ui.Model;

// Single Tone
public final class FakeSession {
    private static volatile FakeSession fakeSession = null;
    private boolean loggedIn = false;
    private User currentUser = null;

    private FakeSession() {
    }

    public static FakeSession getInstance() {
        if (fakeSession == null) {
            synchronized (FakeSession.class) {
                if (fakeSession == null) {
                    fakeSession = new FakeSession();
                }
            }
        }
        return fakeSession;
    }

    public static void checkSessionAndAddAttribute(Model model) {
        FakeSession instance = FakeSession.getInstance();
        if (instance.getCurrentUser() != null) {
            User currentUser = instance.getCurrentUser();
            model.addAttribute("user", currentUser);
            model.addAttribute("isAdmin", currentUser.isAdmin());
            model.addAttribute("loggedIn", instance.isLoggedIn());
        } else { // если пользователь на сайте впервые
            model.addAttribute("user", User.getGuest());
            model.addAttribute("isAdmin", false);
            model.addAttribute("loggedIn", false);
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}