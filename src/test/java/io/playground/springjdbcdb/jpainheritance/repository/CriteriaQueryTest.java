package io.playground.springjdbcdb.jpainheritance.repository;

import io.playground.springjdbcdb.SpringDemoApplication;
import io.playground.springjdbcdb.jpainheritance.entity.Course;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringDemoApplication.class)
@Slf4j
class CriteriaQueryTest {

  @Autowired CourseRepository courseRepository;

  @Autowired EntityManager em;

  @Test
  void findAllCourses_CriteraQuery() {
    // "Select c from Course c"
    // 1. Use criteria builder to create a criteria query returning the expected result object.
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    // 2. Define roots for tables which are involved in the query
    Root<Course> fromCourse = cq.from(Course.class);

    // 3. Define predicates etc using criteria builder

    // 4. Add predicates to the criteria query.

    // 5. Build the TypedQuery using entity manager and criteria query
    TypedQuery<Course> typedQuery = em.createQuery(cq.select(fromCourse));
    List<Course> courses = typedQuery.getResultList();
    log.info("Typed Query Criteria Query -> {}", courses);
  }

  @Test
  void findCoursesLike100Steps_CriteraQuery() {
    // "Select c from Course c"
    // 1. Use criteria builder to create a criteria query returning the expected result object.
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    // 2. Define roots for tables which are involved in the query
    Root<Course> fromCourseRoot = cq.from(Course.class);

    // 3. Define predicates etc using criteria builder
    Predicate like100Steps = cb.like(fromCourseRoot.get("name"), "%100 Steps");

    // 4. Add predicates to the criteria query.
    cq.where(like100Steps);

    // 5. Build the TypedQuery using entity manager and criteria query
    TypedQuery<Course> typedQuery = em.createQuery(cq.select(fromCourseRoot));
    List<Course> courses = typedQuery.getResultList();
    log.info("Typed Query Criteria Query -> {}", courses);
  }
}
