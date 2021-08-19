package io.playground.springjdbcdb;

import io.playground.springjdbcdb.database.entity.Person;
import io.playground.springjdbcdb.database.jdbc.PersonJDBCDAO;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
@Slf4j
public class SpringJDBCDBApplication implements CommandLineRunner {

  @Autowired PersonJDBCDAO personJDBCDAO;

  public static void main(String[] args) {
    SpringApplication.run(SpringJDBCDBApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("All users-> {} ", personJDBCDAO.findAll() + "\n");
    log.info("User 10001-> {} ", personJDBCDAO.findById(10001));
    log.info("Delete 10001-> Number of rows deleted: {} ", personJDBCDAO.deleteById(10001));
    log.info(
        "Insert 10005-> {} ",
        personJDBCDAO.insert(new Person(10005, "Manjula", "Bangalore", new Date())));

    log.info(
        "Update 10004-> {} ",
        personJDBCDAO.update(new Person(10004, "Prasad", "Utrecht", new Date())));
  }
}
