package io.playground.springjdbcdb.jpainheritance.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.playground.springjdbcdb.SpringDemoApplication;
import io.playground.springjdbcdb.jpainheritance.entity.Course;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootTest(classes = SpringDemoApplication.class)
@Slf4j
class CourseSpringDataJPARepositoryTest {

  @Autowired CourseSpringDataJPARepository courseRepository;

  @Test
  public void findById_CourseIsPresent() {
    Optional<Course> course = courseRepository.findById(10001L);
    assertTrue(course.isPresent());
  }

  @Test
  public void findById_CourseIsNotPresent() {
    Optional<Course> course = courseRepository.findById(20001L);
    assertFalse(course.isPresent());
  }

  @Test
  public void findById_PlayingAroundWithSpringDataJPA() {
    Course learnToPlayGuitar = courseRepository.save(new Course("Learn to play Guitar"));
    learnToPlayGuitar.setName("Learn to play Guitar updated");
    courseRepository.save(learnToPlayGuitar);

    log.info("Finding all courses: {}", courseRepository.findAll());
    log.info("Count of courses: {}", courseRepository.count());
  }

  @Test
  public void sortingWithSpringDataJPA() {
    Sort sort = Sort.by(Sort.Direction.DESC, "name").and(Sort.unsorted());
    log.info("Finding all courses: {}", courseRepository.findAll(sort));
    log.info("Count of courses: {}", courseRepository.count());
  }

  @Test
  public void paginationWithSpringDataJPA() {
    PageRequest pageRequest = PageRequest.of(0, 3);
    Page<Course> pages = courseRepository.findAll(pageRequest);

    while (!pages.isEmpty()) {
      log.info("page " + (1 + pages.getNumber()) + " -> {}", pages.getContent());
      pageRequest = pageRequest.next();

      pages = courseRepository.findAll(pageRequest);
    }
  }

  @Test
  public void findUsingName() {
    log.info("findByName -> {}", courseRepository.findByName("JPA in 50 Steps"));
    log.info(
        "course like 10 steps -> {}", courseRepository.courseWith100StepsInName("JPA in 50 Steps"));
    log.info("getAllCoursesUsingNamedQueries -> {}", courseRepository.getAllCoursesUsingNamedQueries());
  }
}
