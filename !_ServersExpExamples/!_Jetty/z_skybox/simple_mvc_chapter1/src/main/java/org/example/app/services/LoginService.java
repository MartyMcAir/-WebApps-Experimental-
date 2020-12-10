package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.example.web.fake_utils.FakeSession;
import org.example.web.fake_utils.FakeUsersBase;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class LoginService {

    private Logger logger = Logger.getLogger(LoginService.class);

    public boolean authenticate(LoginForm loginFrom) {
        logger.info("try auth with user-form: " + loginFrom);
        boolean flag = false;
//        flag = original(loginFrom);

        FakeUsersBase usersBase = FakeUsersBase.getInstance();
        flag = usersBase.authenticateUser(loginFrom.getUsername(), loginFrom.getPassword());
        if (flag) FakeSession.getInstance().setCurrentUser(usersBase.getUserByName(loginFrom.getUsername()));

        return flag;
    }

    private boolean original(LoginForm loginFrom) {
        boolean flag;
        flag = loginFrom.getUsername().equals("root") && loginFrom.getPassword().equals("123");
        if (!flag) {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            String sessionId = attr.getSessionId();
        }
        return flag;
    }
}