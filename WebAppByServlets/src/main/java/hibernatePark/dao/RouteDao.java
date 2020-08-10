package hibernatePark.dao;

import hibernatePark.model.Driver;
import hibernatePark.model.Route;

import java.sql.SQLException;
import java.util.Collection;

public interface RouteDao {
    public void addRoute(Route Route) throws SQLException;

    public void updateRoute(Long Route_id, Route Route) throws SQLException;

    public Route getRouteById(Long Route_id) throws SQLException;

    public Collection<Route> getAllRoutes() throws SQLException;

    public void deleteRoute(Route Route) throws SQLException;

    public Collection<Route> getRoutesByDriver(Driver driver) throws SQLException;

    public Collection<Route> getRoutesByRoute(Route route) throws SQLException;
}