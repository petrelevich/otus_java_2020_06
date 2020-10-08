package ru.otus.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

// Здесь только инициализация БД.
// Примеры сделаны в виде тестов.

public class BatisStarter {
    private static final String URL = "jdbc:h2:mem:testDB";
    private final Connection connection;

    public BatisStarter() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        this.connection.setAutoCommit(false);
    }

    public void createTables() throws SQLException {
        execSqlUpdate("create table test(id int, name varchar(50), address_id int)");
        execSqlUpdate("create table person(id int, firstName varchar(50), lastName varchar(50))");
        execSqlUpdate("create table address(id int, personId int, city varchar(50))");
    }

    public void dropTables() throws SQLException {
        execSqlUpdate("drop table test");
        execSqlUpdate("drop table person");
        execSqlUpdate("drop table address");
    }

    public void insertRecords() throws SQLException {
        try (var pst = connection.prepareStatement(
                "insert into test(id, name) values (?, ?)")) {
            Savepoint savePoint = this.connection.setSavepoint("savePointName");
            try {
                int rowCount = 0;
                for (int idx = 0; idx < 100; idx++) {
                    pst.setInt(1, idx);
                    pst.setString(2, "NameValue_" + idx);
                    rowCount += pst.executeUpdate();
                }
                this.connection.commit();
                System.out.println("inserted rowCount:" + rowCount);
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                System.out.println(ex.getMessage());
            }
        }
    }

    private void execSqlUpdate(String sql) throws SQLException {
        try (var pst = connection.prepareStatement(sql)) {
            pst.executeUpdate();
        }
    }
}
