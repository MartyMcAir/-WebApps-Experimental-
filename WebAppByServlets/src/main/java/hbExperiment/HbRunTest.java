package hbExperiment;

import hbExperiment.dao.HbDevelopersDaoImpl;
import hbExperiment.dao.HbMyDevelopersDao;
import hbExperiment.model.HibernateDevelopersEntity;
import hbExperiment.util.HibernateSessionFactoryUtil;
import org.hibernate.SessionFactory;


public class HbRunTest {
    public static void main(String[] args) {
//        originTest();
        myModification();

    }

    private static void myModification() {
        // https://proselyte.net/tutorials/hibernate-tutorial/annotations/
        HbMyDevelopersDao developersDao = new HbMyDevelopersDao();

        System.out.println("Adding Developer's records to the database");
        HibernateDevelopersEntity developer1 = new HibernateDevelopersEntity("Proselyte", "Developer1", "Java Developer", 2);
        HibernateDevelopersEntity developer2 = new HibernateDevelopersEntity("Proselyte", "Developer2", "Java Developer", 2);
        HibernateDevelopersEntity developer3 = new HibernateDevelopersEntity("Proselyte", "Developer3", "Java Developer", 2);
        Integer id1 = developersDao.addDeveloper(developer1);
        Integer id2 = developersDao.addDeveloper(developer2);
        Integer id3 = developersDao.addDeveloper(developer3);

        System.out.println("\nList of Developers:");
        developersDao.listDevelopers();

        developersDao.removeDeveloper(id2);
        developersDao.updateDeveloperExpirience(id1, 8);

        System.out.println("\nList 2 of Developers:");
        developersDao.listDevelopers();

        developersDao.closeSession();
    }

    private static void originTest() {
        // https://proselyte.net/tutorials/hibernate-tutorial/annotations/
        HbDevelopersDaoImpl developerRunner = new HbDevelopersDaoImpl();
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        developerRunner.setSessionFactory(sessionFactory);

        System.out.println("Adding Developer's records to the database");
        Integer developerId1 = developerRunner.addDeveloper("Proselyte", "Developer",
                "Java Developer", 2);
        Integer developerId2 = developerRunner.addDeveloper("Some", "Developer",
                "C++ Developer", 2);
        Integer developerId3 = developerRunner.addDeveloper("Peter", "Team Lead",
                "Java Team Lead", 6);

        System.out.println("List of Developers:");
        developerRunner.listDevelopers();

        System.out.println("Removing \'Some Developer\' and updating \'Proselyte Developer\''s experience:");
        developerRunner.removeDeveloper(developerId2);
        developerRunner.updateDeveloper(developerId1, 3);

        System.out.println("Final list of Developers:");
        developerRunner.listDevelopers();
        developerRunner.getSessionFactory().close();
    }
}