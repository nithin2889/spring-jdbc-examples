package io.playground.springjdbcdb.jpainheritance.repository;

import io.playground.springjdbcdb.jpainheritance.entity.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "courses")
public interface CourseSpringDataJPARepository extends JpaRepository<Course, Long> {

  List<Course> findByName(String name);

  List<Course> findByIdAndName(Long id, String name);

  List<Course> countByName(String name);

  List<Course> findByNameOrderByIdDesc(String name);

  List<Course> deleteByName(String name);

  @Query(value = "select c.name from course c where c.name like '%100 Steps%'", nativeQuery = true)
  String courseWith100StepsInName(String name);

  @Query(name = "query_get_all_courses")
  List<String> getAllCoursesUsingNamedQueries();
}
