package hibernate.servlets;

import hibernate.dao.UserDaoOneSession;

public class TmpHbTestRun {
    public static void main(String[] args) {
        UserDaoOneSession userDaoOneSession = new UserDaoOneSession();
        userDaoOneSession.startAndGetTransaction();

        System.out.println("__________ RESULT IS: __________ " + userDaoOneSession.getMaxId());

        userDaoOneSession.closeSession();
    }
}