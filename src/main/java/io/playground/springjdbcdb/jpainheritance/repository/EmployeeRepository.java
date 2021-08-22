package io.playground.springjdbcdb.jpainheritance.repository;

import io.playground.springjdbcdb.jpainheritance.entity.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@Slf4j
public class EmployeeRepository {

  @PersistenceContext EntityManager em;

  public void insert(Employee employee) {
    em.merge(employee);
  }

  public List<Employee> retrieveAllEmployees() {
    return em.createQuery("select e from Employee e", Employee.class).getResultList();
  }

  public Employee findById(int id) {
    return em.find(Employee.class, id);
  }
}
