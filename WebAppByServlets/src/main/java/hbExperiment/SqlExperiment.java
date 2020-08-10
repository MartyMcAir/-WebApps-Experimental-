package hbExperiment;

import MyDB_CRUD.DBConnectionPoolVector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class SqlExperiment {
    public static void main(String[] args) throws SQLException {
        String query = "CREATE TABLE HIBERNATE_DEVELOPERS(\n" +
                "   ID serial not null,\n" +
                "   FIRST_NAME VARCHAR(50) DEFAULT NULL,\n" +
                "   LAST_NAME VARCHAR(50) DEFAULT NULL,\n" +
                "   SPECIALTY VARCHAR(50) DEFAULT NULL,\n" +
                "   EXPERIENCE INT DEFAULT NULL,\n" +
                "   PRIMARY KEY(ID)\n" +
                ");";

        executeSQLbyJPA(query);
//        userAuto();
    }

    private static void userAuto() throws SQLException {
        // @OneToMane и @ManyToOne
        // from http://java-online.ru/hibernate-entities.xhtml
        // сначала создаем главную таблицу
        String sql_tbl_user = "create table users (" +
                "id        Integer not null, " + //default nextval('users_id_seq'::regclass), " + // exception
                "login     varchar (16) null, " +
                "name      varchar (64) null, " +
//                "SYSDATE   int not null, " +
//                "data      timestamp default SYSDATE, " + // DEFAULT (по умолчанию) нельзя ссылаться на столбцы
                "data      timestamp, " +
                "CONSTRAINT PK_USERID PRIMARY KEY (id) );";
        // далее таблицы которые зависят от главной
        String sql_tbl_auto = "create TABLE autos (" +
                "aid         Integer not null, " +
                "user_id     Integer, " +
                "name        varchar (32), " +
                "CONSTRAINT pk_AUTOSK_ID PRIMARY KEY (aid), " +
                "CONSTRAINT fk_USERID FOREIGN KEY (user_id) " +
                "REFERENCES users (id) );"; // IDEA выдает предупреждение, но работать будет

        // From Data Grip _ by created from http://java-online.ru/hibernate-entities.xhtml
        String sql_user_originFromDataGrip = "create table users (" +
                "id    serial not null " +
                "constraint pk_userid primary key, " +
                "login varchar(16), " +
                "name  varchar(64), " +
                "data  timestamp );";
        String sql_auto_originFromDataGrip = "create table autos (" +
                "aid     integer not null " +
                "constraint pk_autosk_id primary key, " +
                "user_id integer " +
                "constraint fk_userid references users, " +
                "name    varchar(32) );";

        // From Data Grip _ by Me with help from DataGrip
        String sql_dataGrip_crtTblUser = "CREATE TABLE userT(" +
                "id serial not null " + // serial говорит об auto increment
                "constraint userT_pk primary key, " + // pk означает primary key, потому и не указывается
                "login varchar(16), " +
                "name varchar(64), " +
                "age int, " +
                "date timestamp );";

        String sql_dataGrip_crtTblAuto = "CREATE TABLE autoT(" +
                "aid serial not null " +
                "constraint autoT_pk primary key, " +
                "user_id int " + // fk означает FOREIGN KEY, потому и не указывается
                "constraint autoT_fk references userT, " + // по умолчанию user_t(id) по этому не указывается
                "auto_name varchar(32) );";

        String sql_add_user = "INSERT INTO users (name, data) VALUES (Thanos, " + new Date() + ")";


        // CASCADE _ ON DELETE _ from https://metanit.com/sql/sqlserver/3.5.php
        // при удалении покупателя из таблицы customers - все его ордера из таблицы orders так же будут удалены
        String sql_tblCustomers = "CREATE TABLE Customers( " +
                "    Id serial PRIMARY KEY, " + // IDENTITY = exception
                "    Age INT DEFAULT 18, " +
                "    FirstName VARCHAR(20) NOT NULL, " +
                "    LastName VARCHAR(20) NOT NULL, " +
                "    Email VARCHAR(30) UNIQUE, " +
                "    Phone VARCHAR(20) UNIQUE );";
        String sql_tblC_modBYDataGrip = "create table customers ( " +
                "id serial not null constraint customers_pkey primary key, " +
                "    age       integer default 18, " +
                "    firstname varchar(20) not null, " +
                "    lastname  varchar(20) not null, " +
                "email varchar(30) constraint customers_email_key unique, " +
                "phone varchar(20) constraint customers_phone_key unique );";

        String sql_tblOrders = "CREATE TABLE Orders ( " +
                "    Id serial PRIMARY KEY, " +   // IDENTITY = exception
//                "    Id int not null identity (1,1), " + // в просто sql работает но не для postGreSQL
                "    CustomerId INT, " +
                "    CreatedAt Date, " +
                " market_id int, " +
                "    FOREIGN KEY (CustomerId) REFERENCES Customers (Id) ON DELETE CASCADE )";
        String sql_tblO_modByDataGrip = "create table orders ( " +
                "    id         serial not null constraint orders_pkey primary key, " +
                "customerid integer constraint orders_customerid_fkey " +
                "references customers on delete cascade, " +
                "    createdat  date, " +
                "market_id  integer );";

        executeSQLbyJPA(sql_tblOrders);
    }

    private static void executeSQLbyJPA(String sql_is) throws SQLException {
        DBConnectionPoolVector conPool = new DBConnectionPoolVector(3);
        Connection connection = conPool.retrieve(); // using DriverManager.getConnection(..
        Statement statement = connection.createStatement();

        statement.execute(sql_is);
        statement.close();
    }
}
