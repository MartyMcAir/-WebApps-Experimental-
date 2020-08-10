package hibernatePark.dao;

import hibernatePark.model.Bus;
import hibernatePark.model.Driver;
import hibernatePark.model.Route;
import hibernatePark.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


public class DriverDaoImpl implements DriverDao {

    @Override
    public void addDriver(Driver Driver) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(Driver);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public void updateDriver(Long Driver_id, Driver Driver) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(Driver);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public Driver getDriverById(Long Driver_id) throws SQLException {
        Session session = null;
        Driver Driver = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Driver = (Driver) session.load(Driver.class, Driver_id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return Driver;
    }

    @Override
    public Collection<Driver> getAllDrivers() throws SQLException {
        Session session = null;
        List<Driver> Driveres = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Driveres = session.createCriteria(Driver.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
        return Driveres;
    }

    @Override
    public void deleteDriver(Driver Driver) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(Driver);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Collection<Driver> getDriversByBus(Bus bus) throws SQLException {
        Session session = null;
        List<Driver> Drivers;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long driver_id = bus.getId();
            Query query = session.createQuery(" select b "
//                    + " from Driver b INNER JOIN b.drivers driver"
                    + " from Driver b INNER JOIN Driver driver"
                    + " where driver.id = :driverId ")
                    .setLong("driverId", driver_id);
            Drivers = (List<Driver>) query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
        return Drivers;
    }

    @Override
    public Collection<Driver> getDriversByRoute(Route route) throws SQLException {
        Session session = null;
        List<Driver> Drivers;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long route_id = route.getId();
//            Query query = session.createQuery("from Driver where route_id = :routeId ")
            Query query = session.createQuery("from Driver where Route = :routeId ")
                    .setLong("routeId", route_id);
            Drivers = (List<Driver>) query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
        return Drivers;
    }
}