package hibernatePark.dao;

import hibernatePark.model.*;

import java.sql.SQLException;
import java.util.Collection;

public interface BusDao {
    public void addBus(Bus bus) throws SQLException;

    public void updateBus(Long bus_id, Bus bus) throws SQLException;

    public Bus getBusById(Long bus_id) throws SQLException;

    public Collection<Bus> getAllBuses() throws SQLException;

    public void deleteBus(Bus bus) throws SQLException;

    public Collection<Bus> getBusesByDriver(Driver driver) throws SQLException;

    public Collection<Bus> getBusesByRoute(Route route) throws SQLException;
}