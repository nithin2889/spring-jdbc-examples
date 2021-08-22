package io.playground.springjdbcdb;

import io.playground.springjdbcdb.jpainheritance.entity.FullTimeEmployee;
import io.playground.springjdbcdb.jpainheritance.entity.PartTimeEmployee;
import io.playground.springjdbcdb.jpainheritance.repository.EmployeeRepository;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringJPAInheritanceDemoApplication implements CommandLineRunner {

  @Autowired EmployeeRepository employeeRepository;

  public static void main(String[] args) {
    SpringApplication.run(SpringJPAInheritanceDemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Insert a full-time employee");
    employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));

    log.info("Insert a part-time employee");
    employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));

    log.info("All employees -> {}\n", employeeRepository.retrieveAllEmployees());
  }
}
