package ru.otus.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.model.User;
import ru.otus.h2.DataSourceH2;

import javax.sql.DataSource;

/**
 * @author sergey
 * created on 03.02.19.
 */
// этот класс не должен быть в домашней работе
public class ExecutorDemo {
    private static final Logger logger = LoggerFactory.getLogger(ExecutorDemo.class);

    public static void main(String[] args) throws SQLException {
        var dataSource = new DataSourceH2();
        flywayMigrations(dataSource);

        try (Connection connection = getConnection(dataSource)) {
            DbExecutorImpl<User> executor = new DbExecutorImpl<>();
            long userId = executor.executeInsert(connection, "insert into user(name) values (?)",
                    Collections.singletonList("testUserName"));
            logger.info("created user:{}", userId);
            connection.commit();

            Optional<User> user = executor.executeSelect(connection, "select id, name from user where id  = ?",
                    userId, rs -> {
                        try {
                            if (rs.next()) {
                                return new User(rs.getLong("id"), rs.getString("name"));
                            }
                        } catch (SQLException e) {
                            logger.error(e.getMessage(), e);
                        }
                        return null;
                    });
            logger.info("user:{}", user);
        }
    }

    private static Connection getConnection(DataSource dataSource) throws SQLException {
        var connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    private static void flywayMigrations(DataSource dataSource) {
        logger.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        logger.info("db migration finished.");
        logger.info("***");
    }
}
