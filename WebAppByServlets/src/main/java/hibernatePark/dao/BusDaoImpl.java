package hibernatePark.dao;

import hibernatePark.model.Bus;
import hibernatePark.model.Driver;
import hibernatePark.model.Route;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


public class BusDaoImpl implements BusDao {

    @Override
    public void addBus(Bus bus) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(bus);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public void updateBus(Long bus_id, Bus bus) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(bus);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public Bus getBusById(Long bus_id) throws SQLException {
        Session session = null;
        Bus bus = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            bus = (Bus) session.load(Bus.class, bus_id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return bus;
    }

    @Override
    public Collection<Bus> getAllBuses() throws SQLException {
        Session session = null;
        List<Bus> buses = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            buses = session.createCriteria(Bus.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
        return buses;
    }

    @Override
    public void deleteBus(Bus bus) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(bus);
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
    public Collection<Bus> getBusesByDriver(Driver driver) throws SQLException {
        Session session = null;
        List<Bus> buses;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long driver_id = driver.getId();
            Query query = session.createQuery(" select b "
//                    + " from Bus b INNER JOIN b.drivers driver"
                    + " from Bus b INNER JOIN Driver driver"
                    + " where driver.id = :driverId ")
                    .setLong("driverId", driver_id);
            buses = (List<Bus>) query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
        return buses;
    }

    @Override
    public Collection<Bus> getBusesByRoute(Route route) throws SQLException {
        Session session = null;
        List<Bus> buses;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long route_id = route.getId();
//            Query query = session.createQuery("from Bus where route_id = :routeId ")
            Query query = session.createQuery("from Bus where Route = :routeId ")
                    .setLong("routeId", route_id);
            buses = (List<Bus>) query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
        return buses;
    }
}