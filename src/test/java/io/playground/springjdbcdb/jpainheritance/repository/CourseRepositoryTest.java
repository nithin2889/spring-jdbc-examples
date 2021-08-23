package io.playground.springjdbcdb.jpainheritance.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.playground.springjdbcdb.SpringDemoApplication;
import io.playground.springjdbcdb.jpainheritance.entity.Course;
import io.playground.springjdbcdb.jpainheritance.entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(classes = SpringDemoApplication.class)
@Slf4j
class CourseRepositoryTest {

  @Autowired CourseRepository courseRepository;

  @Autowired EntityManager em;

  @Test
  void findById_Basic() {
    Course course = courseRepository.findById(10001L);
    assertEquals("JPA in 50 Steps", course.getName());
  }

  @Test
  @Transactional
  void findById_FirstLevelCache() {
    Course course = courseRepository.findById(10001L);
    log.info("First course retrieved {}", course);

    Course course2 = courseRepository.findById(10001L);
    log.info("First course retrieved again {}", course2);

    assertEquals("JPA in 50 Steps", course.getName());
    assertEquals("JPA in 50 Steps", course2.getName());
  }

  @Test
  void findById_JPQL_RawType() {
    Query query = em.createQuery("Select c from Course c");
    List courses = query.getResultList();
    log.info("Select c from Course c -> {}", courses);
  }

  @Test
  void findById_JPQL_Typed() {
    TypedQuery<Course> queryType = em.createQuery("Select c from Course c", Course.class);
    List<Course> courses = queryType.getResultList();
    log.info("Select c from Course c -> {}", courses);
  }

  @Test
  void findById_JPQL_WhereClause() {
    TypedQuery<Course> queryType =
        em.createQuery("Select c from Course c where name like '%100 Steps'", Course.class);
    List<Course> courses = queryType.getResultList();
    log.info("Select c from Course c where name like '%100 Steps' -> {}", courses);
  }

  @Test
  void findById_NativeQuery_Basic() {
    Query query = em.createNativeQuery("select * from course where is_deleted = 0", Course.class);
    List courses = query.getResultList();
    log.info("select * from course -> {}", courses);
  }

  @Test
  void findById_NativeQuery_PositionalParameter() {
    Query query = em.createNativeQuery("select * from course where id = ? and is_deleted = 0", Course.class);
    query.setParameter(1, 10001L);
    List courses = query.getResultList();
    log.info("select * from course where id = ? -> {}", courses);
  }

  @Test
  void findById_NativeQuery_NamedParameter() {
    Query query = em.createNativeQuery("select * from course where id = :id and is_deleted = 0", Course.class);
    query.setParameter("id", 10001L);
    List courses = query.getResultList();
    log.info("select * from course where id = ? -> {}", courses);
  }

  @Test
  @Transactional
  void findById_NativeQuery_BulkUpdate() {
    Query query =
        em.createNativeQuery("update course set last_updated_date = sysdate()", Course.class);
    int noOfRows = query.executeUpdate();
    log.info("No. of rows updated -> {}", noOfRows);
  }

  @Test
  @DirtiesContext
  void deleteById_Basic() {
    courseRepository.deleteById_Basic(10001L);
    assertNull(courseRepository.findById(10001L));
  }

  @Test
  @DirtiesContext
  void save_Basic() {
    // get the course
    Course course = courseRepository.findById(10001L);
    assertEquals("JPA in 50 Steps", course.getName());

    // update the course
    course.setName("JPA in 50 Steps updated");
    courseRepository.save(course);

    // check the value
    Course updatedCourse = courseRepository.findById(10001L);
    assertEquals("JPA in 50 Steps updated", updatedCourse.getName());
  }

  @Test
  @DirtiesContext
  void playWithEntityManagerTest() {
    courseRepository.playWithEntityManager_Flush_Detach_Clear();
  }

  @Test
  void findById_JPQL_RawNamedQuery() {
    Query query = em.createNamedQuery("query_get_all_courses");
    List courses = query.getResultList();
    log.info("query_get_all_courses: Select c from Course c -> {}", courses);
  }

  @Test
  void findById_JPQL_TypedNamedQuery() {
    TypedQuery<Course> queryType = em.createNamedQuery("query_get_all_courses", Course.class);
    List<Course> courses = queryType.getResultList();
    log.info("query_get_all_courses: Select c from Course c -> {}", courses);
  }

  @Test
  void findById_JPQL_WhereClauseNamedQuery() {
    TypedQuery<Course> queryType = em.createNamedQuery("query_get_100_steps_courses", Course.class);
    List<Course> courses = queryType.getResultList();
    log.info(
        "query_get_100_steps_courses: Select c from Course c where name like '%100 Steps' -> {}",
        courses);
  }

  @Test
  void findById_JPQL_CoursesWithoutStudents() {
    TypedQuery<Course> queryType =
        em.createQuery("Select c from Course c where c.students is empty", Course.class);
    List<Course> courses = queryType.getResultList();
    log.info("Select c from Course c where c.students is empty -> {}", courses);
  }

  @Test
  void findById_JPQL_CoursesWithAtleast2Students() {
    TypedQuery<Course> queryType =
        em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
    List<Course> courses = queryType.getResultList();
    log.info("Select c from Course c where size(c.students) >= 2 -> {}", courses);
  }

  @Test
  void findById_JPQL_CoursesOrderByStudents() {
    TypedQuery<Course> queryType =
        em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
    List<Course> courses = queryType.getResultList();
    log.info("Select c from Course c order by size(c.students) desc -> {}", courses);
  }

  @Test
  void findById_JPQL_StudentsWithCertainPassportPattern() {
    TypedQuery<Student> queryType =
        em.createQuery(
            "Select s from Student s where s.passport.number like '%1234%'", Student.class);
    List<Student> students = queryType.getResultList();
    log.info("Select s from Student s where s.passport.number like '%1234% -> {}", students);
  }

  @Test
  public void join() {
    Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
    List<Object[]> resultList = query.getResultList();
    log.info("Result size -> {}", resultList.size());
    for (Object[] result : resultList) {
      log.info("result[0] -> {}", result[0]);
      log.info("result[1] -> {}", result[1]);
    }
  }

  @Test
  public void leftJoin() {
    Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
    List<Object[]> resultList = query.getResultList();
    log.info("Result size -> {}", resultList.size());
    for (Object[] result : resultList) {
      log.info("result[0] -> {}", result[0]);
      log.info("result[1] -> {}", result[1]);
    }
  }

  @Test
  public void crossJoin() {
    Query query = em.createQuery("Select c, s from Course c, Student s");
    List<Object[]> resultList = query.getResultList();
    log.info("Result size -> {}", resultList.size());
    for (Object[] result : resultList) {
      log.info("result[0] -> {}", result[0]);
      log.info("result[1] -> {}", result[1]);
    }
  }
}
