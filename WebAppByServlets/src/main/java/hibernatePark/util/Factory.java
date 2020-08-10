package hibernatePark.util;

import hibernatePark.dao.*;

public class Factory {
    private static BusDao busDao = null;
    private static DriverDao driverDao = null;
    private static RouteDao routeDao = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance() {
        if (instance == null) instance = new Factory();
        return instance;
    }

    public BusDao getBusDao() {
        if (busDao == null) busDao = new BusDaoImpl();
        return busDao;
    }

    public DriverDao getDriverDao() {
        if (driverDao == null) driverDao = new DriverDaoImpl();
        return driverDao;
    }

    public RouteDao getRouteDao() {
        if (routeDao == null) routeDao = new RouteDaoImpl();
        return routeDao;
    }
}