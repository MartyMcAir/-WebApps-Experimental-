package hibernatePark.dao;

import hibernatePark.model.Driver;
import hibernatePark.model.Route;
import hibernatePark.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


public class RouteDaoImpl implements RouteDao {

    @Override
    public void addRoute(Route Route) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(Route);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public void updateRoute(Long Route_id, Route Route) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(Route);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public Route getRouteById(Long Route_id) throws SQLException {
        Session session = null;
        Route Route = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Route = (Route) session.load(Route.class, Route_id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return Route;
    }

    @Override
    public Collection<Route> getAllRoutes() throws SQLException {
        Session session = null;
        List<Route> Routees = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Routees = session.createCriteria(Route.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
        return Routees;
    }

    @Override
    public void deleteRoute(Route Route) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(Route);
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
    public Collection<Route> getRoutesByDriver(Driver driver) throws SQLException {
        Session session = null;
        List<Route> Routes;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long driver_id = driver.getId();
            Query query = session.createQuery(" select b "
//                    + " from Route b INNER JOIN b.drivers driver"
                    + " from Route b INNER JOIN Driver driver"
                    + " where driver.id = :driverId ")
                    .setLong("driverId", driver_id);
            Routes = (List<Route>) query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
        return Routes;
    }

    @Override
    public Collection<Route> getRoutesByRoute(Route route) throws SQLException {
        Session session = null;
        List<Route> Routes;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long route_id = route.getId();
//            Query query = session.createQuery("from Route where route_id = :routeId ")
            Query query = session.createQuery("from Route where Route = :routeId ")
                    .setLong("routeId", route_id);
            Routes = (List<Route>) query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
        return Routes;
    }
}