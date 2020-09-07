package ru.otus;

import java.util.Optional;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.jdbc.dao.UserDaoJdbc;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.h2.DataSourceH2;
import ru.otus.core.model.User;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;



/**
 * @author sergey
 * created on 03.02.19.
 */
// этот класс не должен быть в домашней работе
public class DbServiceDemo {
    private static final Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) {
        var dataSource = new DataSourceH2();
        flywayMigrations(dataSource);

        var sessionManager = new SessionManagerJdbc(dataSource);
        DbExecutorImpl<User> dbExecutor = new DbExecutorImpl<>();
        var userDao = new UserDaoJdbc(sessionManager, dbExecutor);

        var dbServiceUser = new DbServiceUserImpl(userDao);
        var id = dbServiceUser.saveUser(new User(0, "dbServiceUser"));
        Optional<User> user = dbServiceUser.getUser(id);

        user.ifPresentOrElse(
                crUser -> logger.info("created user, name:{}", crUser.getName()),
                () -> logger.info("user was not created")
        );
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
