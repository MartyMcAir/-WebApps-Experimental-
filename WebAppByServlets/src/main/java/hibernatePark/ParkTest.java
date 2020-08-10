package hibernatePark;

import hibernatePark.dao.DriverDao;
import hibernatePark.model.Bus;
import hibernatePark.model.Driver;
import hibernatePark.model.Route;
import hibernatePark.util.Factory;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

// from https://habr.com/ru/post/29694/
// Dont Work _ wrong sql query
public class ParkTest {
//    String query = "SELECT driver_id, name, surname, age FROM drivers";
//    List drivers = new ArrayList();
//    drivers =(List)session.createSQLQuery(query).list();

    public static void main(String[] args) throws SQLException {
//        addDrivers();
        DriverDao driverDao = Factory.getInstance().getDriverDao();
        Collection<Driver> allDrivers = driverDao.getAllDrivers();
        for (Driver driver : allDrivers) {
            System.out.println(driver.getName());
        }

//        origin();
    }

    private static void addDrivers() throws SQLException {
        DriverDao driverDao = Factory.getInstance().getDriverDao();
        Driver driverToby = new Driver();
        Driver driverJohnathan = new Driver();
        driverToby.setName("Toby");
        driverJohnathan.setName("Johnathan");

        driverDao.addDriver(driverToby);
        driverDao.addDriver(driverJohnathan);
    }

    private static void origin() throws SQLException {
        Collection routes = Factory.getInstance().getRouteDao().getAllRoutes();
        Iterator iterator = routes.iterator();
        System.out.println("========Все маршруты=========");
        while (iterator.hasNext()) {
            Route route = (Route) iterator.next();
            System.out.println("Маршрут : " + route.getName() + "  Номер маршрута : " + route.getNumber());
            Collection buses = Factory.getInstance().getBusDao().getBusesByRoute(route);
            Iterator iterator2 = buses.iterator();
            while (iterator2.hasNext()) {
                Bus bus = (Bus) iterator2.next();
                System.out.println("Автобус № " + bus.getNumber());
            }
        }

        Collection busses = Factory.getInstance().getBusDao().getAllBuses();
        iterator = busses.iterator();
        System.out.println("========Все автобусы=========");
        while (iterator.hasNext()) {
            Bus bus = (Bus) iterator.next();
            Collection drivers = Factory.getInstance().getDriverDao().getDriversByBus(bus);
            Iterator iterator2 = drivers.iterator();
            System.out.println("Автобус № " + bus.getNumber());
            while (iterator2.hasNext()) {
                Driver driver = (Driver) iterator2.next();
                System.out.println("Имя : " + driver.getName() + "   Фамилия: " + driver.getSurname());
            }
        }
    }
}