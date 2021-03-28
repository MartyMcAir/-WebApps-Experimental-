package pkg.utils;


import org.springframework.ui.Model;
import pkg.model.User;

// Single Tone
public final class FakeSession_early {
    private static volatile FakeSession_early fakeSession = null;
    private boolean loggedIn = false;
    private User currentUser = null;

    private FakeSession_early() {
    }

    public static FakeSession_early getInstance() {
        if (fakeSession == null) {
            synchronized (FakeSession_early.class) {
                if (fakeSession == null) {
                    fakeSession = new FakeSession_early();
                }
            }
        }
        return fakeSession;
    }

    public static void checkSessionAndAddAttribute(Model model) {
        FakeSession_early instance = FakeSession_early.getInstance();
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