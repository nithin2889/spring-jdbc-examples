package io.playground.springjdbcdb.jpainheritance.repository;

import io.playground.springjdbcdb.SpringDemoApplication;
import io.playground.springjdbcdb.jpainheritance.entity.Address;
import io.playground.springjdbcdb.jpainheritance.entity.Course;
import io.playground.springjdbcdb.jpainheritance.entity.Passport;
import io.playground.springjdbcdb.jpainheritance.entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringDemoApplication.class)
@Slf4j
class StudentRepositoryTest {

  @Autowired StudentRepository studentRepository;

  @Autowired EntityManager em;

  @Test
  @Transactional
  public void someTest() {
    // database operation 1 - retrieve student
    Student student = em.find(Student.class, 20001L);

    // database operation 2 - retrieve passport
    Passport passport = student.getPassport();

    // database operation 3 - update passport
    passport.setNumber("E123457");

    // database operation 4 - update student
    student.setName("Nithin Prasad-Updated");
  }

  @Test
  @Transactional
  void retrieveStudentAndPassport() {
    Student student = em.find(Student.class, 20001L);
    log.info("Student details -> {}", student);
    log.info("Passport details -> {}", student.getPassport());
  }

  @Test
  @Transactional
  void setAddressDetailsForAStudent() {
    Student student = em.find(Student.class, 20001L);
    student.setAddress(new Address("No. 1706", "BSK 1st Stage", "Bangalore"));
    em.flush();
    log.info("Student details -> {}", student);
    log.info("Passport details -> {}", student.getPassport());
  }

  @Test
  @Transactional
  void retrievePassportAndAssociatedStudent() {
    Passport passport = em.find(Passport.class, 40001L);
    log.info("Passport details -> {}", passport);
    log.info("Student details -> {}", passport.getStudent());
  }

  @Test
  @Transactional
  void retrieveStudentAndCourses() {
    Student student = em.find(Student.class, 20001L);
    log.info("Student details -> {}", student);

    List<Course> courses = student.getCourses();
    log.info("Courses details -> {}", courses);
  }
}
