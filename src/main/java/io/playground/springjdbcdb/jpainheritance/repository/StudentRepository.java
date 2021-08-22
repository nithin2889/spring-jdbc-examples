package io.playground.springjdbcdb.jpainheritance.repository;

import io.playground.springjdbcdb.jpainheritance.entity.Passport;
import io.playground.springjdbcdb.jpainheritance.entity.Student;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Slf4j
public class StudentRepository {

  @Autowired EntityManager entityManager;

  public Student findById(Long id) {
    return entityManager.find(Student.class, id);
  }

  public Student save(Student student) {
    if (student.getId() == null) {
      // if id is null, insert
      entityManager.persist(student);
    } else {
      // else update
      entityManager.merge(student);
    }
    return student;
  }

  public void saveStudentWithPassport() {
    Passport passport = new Passport("L8521089");
    entityManager.persist(passport);

    Student student = new Student("Mike");
    student.setPassport(passport);
    entityManager.persist(student);
  }

  public void deleteById_Basic(Long id) {
    Student studentById = findById(id);
    entityManager.remove(studentById);
  }

  public void playWithEntityManager() {
    Student newStudent1 = new Student("Maven in 100 Steps");
    entityManager.persist(newStudent1);

    Student student2 = findById(10004L);
    student2.setName("Microservices in 50 Steps updated");
  }

  public void playWithEntityManager_Flush_Detach_Clear() {
    Student newStudent1 = new Student("Web Services in 100 Steps");
    entityManager.persist(newStudent1);
    entityManager.flush();

    newStudent1.setName("Web Services in 100 Steps Updated");
    entityManager.flush();

    Student newStudent2 = new Student("AngularJS in 100 Steps");
    entityManager.persist(newStudent2);
    entityManager.flush();

    entityManager.detach(newStudent2);
    entityManager.clear();

    newStudent2.setName("AngularJS in 100 Steps Updated");
    entityManager.flush();
  }

  public void playWithEntityManager_Refresh() {
    Student newStudent1 = new Student("React Native in 100 Steps");
    entityManager.persist(newStudent1);

    Student newStudent2 = new Student("ReactJS in 100 Steps");
    entityManager.persist(newStudent2);

    entityManager.flush();

    newStudent1.setName("React Native in 100 Steps Updated");
    newStudent2.setName("ReactJS in 100 Steps Updated");

    entityManager.refresh(newStudent1);

    entityManager.flush();
  }
}
