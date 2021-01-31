package dbUtilsExperimental;

import util.HibernateConnect;
import dbUtilsExperimental.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.math.BigInteger;

// https://www.baeldung.com/hibernate-aggregate-functions
public class HibernateSqlUtils {
    private Session session;
    private Transaction transaction;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    //    @BeforeClass
    public void setup() {
        session = HibernateConnect.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        session.save(new Student("Jonas", 22, 12));
        session.save(new Student("Sally", 20, 34));
        session.save(new Student("Simon", 25, 45));
        session.save(new Student("Raven", 21, 43));
        session.save(new Student("Sam", 23, 33));

        transaction.commit();
        session.close();  // added by me
    }

    // для aggregate запросов транзакции не нужны _ это ведь не проведение транзакции а лишь запрос на получение..
    public void openSession() {
        session = HibernateConnect.getSessionFactory().openSession();
    }

    public void closeSession() {
        session.close();
    }

    public void openSessionAndTransaction() {
        session = HibernateConnect.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    public void closeSessionAndTransaction() {
        transaction.commit();
        session.close();
    }

    // Aggregate Functions
    public int getMin(String fieldName, String tableName) {
        return (int) session.createSQLQuery("SELECT min(" + fieldName + ") from " + tableName).getSingleResult();
    }

    public int getMax(String fieldName, String tableName) {
        return (int) session.createSQLQuery("SELECT max(" + fieldName + ") from " + tableName).getSingleResult();
    }

    public long getSum(String fieldName, String tableName) {
        BigInteger singleResult = (BigInteger) session.createSQLQuery("SELECT sum(" + fieldName + ") from " + tableName).getSingleResult();
        return Long.parseLong(singleResult.toString());
    }

    public double getAvg(String fieldName, String tableName) {
        BigDecimal singleResult = (BigDecimal) session.createSQLQuery("SELECT avg(" + fieldName + ") from " + tableName).getSingleResult();
        return Double.parseDouble(singleResult.toString());
    }

    //    public long getCount(String fieldName, String tableName) {
//        return (int) session.createQuery("SELECT count(" + fieldName + ") from " + tableName).getSingleResult();
    public long getCount(String fieldName, String tableName) {
        BigInteger singleResult = (BigInteger) session.createSQLQuery("SELECT count(*) from " + tableName).getSingleResult();
        return Long.parseLong(singleResult.toString());
    }
}