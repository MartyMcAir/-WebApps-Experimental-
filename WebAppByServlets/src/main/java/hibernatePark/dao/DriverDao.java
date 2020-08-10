package hibernatePark.dao;

import hibernatePark.model.Bus;
import hibernatePark.model.Driver;
import hibernatePark.model.Route;

import java.sql.SQLException;
import java.util.Collection;

public interface DriverDao {
    public void addDriver(Driver Driver) throws SQLException;

    public void updateDriver(Long Driver_id, Driver Driver) throws SQLException;

    public Driver getDriverById(Long Driver_id) throws SQLException;

    public Collection<Driver> getAllDrivers() throws SQLException;

    public void deleteDriver(Driver Driver) throws SQLException;

    public Collection<Driver> getDriversByBus(Bus bus) throws SQLException;

    public Collection<Driver> getDriversByRoute(Route route) throws SQLException;
}