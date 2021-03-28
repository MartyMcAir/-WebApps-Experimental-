package com.db;


import com.utils.PropertiesHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Database {
//    private static Log log = LogFactory.getLog(Database.class);


    public static Connection getConnection() {
        try {
            Properties props = PropertiesHelper.getDBConnectionProps();

//            log.info("Using database with URL " + props.getProperty("db.url"));
            Connection dbCon = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
            ;
            return dbCon;
        } catch (Exception e) {
//            log.error("Error setting up DB connection: ", e);
        } catch (Throwable e) {
//            log.error("Error setting up DB connection: ", e);
        }

        return null;
    }
}