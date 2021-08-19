package io.playground.springjdbcdb;

import io.playground.springjdbcdb.database.entity.Person;
import io.playground.springjdbcdb.database.jpa.PersonJPARepository;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringJPADemoApplication implements CommandLineRunner {

  @Autowired PersonJPARepository personJPARepository;

  public static void main(String[] args) {
    SpringApplication.run(SpringJPADemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("All users -> {} ", personJPARepository.findAll() + "\n");

    log.info("User 10001 -> {} ", personJPARepository.findById(10001));

    log.info(
        "Insert 10005 -> {} ",
        personJPARepository.upsert(new Person(10005, "Manjula", "Bangalore", new Date())));

    log.info(
        "Update 10001 -> {} ",
        personJPARepository.upsert(new Person(10001, "Nithin P", "Utrecht", new Date())));

    log.info("Delete 10005 ->");
    personJPARepository.deleteById(10005);
  }
}
