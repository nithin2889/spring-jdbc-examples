package io.playground.springjdbcdb.jpainheritance.repository;

import io.playground.springjdbcdb.jpainheritance.entity.Course;
import io.playground.springjdbcdb.jpainheritance.entity.Review;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
@Slf4j
public class CourseRepository {

  @Autowired EntityManager entityManager;

  public Course findById(Long id) {
    return entityManager.find(Course.class, id);
  }

  public Course save(Course course) {
    if (course.getId() == null) {
      // if id is null, insert
      entityManager.persist(course);
    } else {
      // else update
      entityManager.merge(course);
    }
    return course;
  }

  public void deleteById_Basic(Long id) {
    Course courseById = findById(id);
    entityManager.remove(courseById);
  }

  public void playWithEntityManager() {
    Course newCourse1 = new Course("Maven in 100 Steps");
    entityManager.persist(newCourse1);

    Course course2 = findById(10004L);
    course2.setName("Microservices in 50 Steps updated");
  }

  public void playWithEntityManager_Flush_Detach_Clear() {
    Course newCourse1 = new Course("Web Services in 100 Steps");
    entityManager.persist(newCourse1);
    entityManager.flush();

    newCourse1.setName("Web Services in 100 Steps Updated");
    entityManager.flush();

    Course newCourse2 = new Course("AngularJS in 100 Steps");
    entityManager.persist(newCourse2);
    entityManager.flush();

    entityManager.detach(newCourse2);
    entityManager.clear();

    newCourse2.setName("AngularJS in 100 Steps Updated");
    entityManager.flush();
  }

  public void playWithEntityManager_Refresh() {
    Course newCourse1 = new Course("React Native in 100 Steps");
    entityManager.persist(newCourse1);

    Course newCourse2 = new Course("ReactJS in 100 Steps");
    entityManager.persist(newCourse2);

    entityManager.flush();

    newCourse1.setName("React Native in 100 Steps Updated");
    newCourse2.setName("ReactJS in 100 Steps Updated");

    entityManager.refresh(newCourse1);

    entityManager.flush();
  }

  public void addHardcodedReviewsForCourse() {
    // get course id 10003
    Course course = entityManager.find(Course.class, 10003L);
    log.info("course getReviews() -> {}", course.getReviews());

    // add 2 reviews
    Review review1 = new Review("2.5", "Okay-ish course");
    Review review2 = new Review("3.5", "Above average course");

    /*since review is the owning side of the relationship we need to tell JPA which
    course the review is associated with.*/
    // Setting the relationship
    course.addReview(review1);
    review1.setCourse(course);

    course.addReview(review2);
    review2.setCourse(course);

    // save it to the database
    entityManager.persist(review1);
    entityManager.persist(review2);
  }

  // general form
  public void addReviewsForCourse(Long courseId, List<Review> reviews) {
    // get course id 10003
    Course course = findById(courseId);
    log.info("course getReviews() -> {}", course.getReviews());

    // add all reviews
    for (Review review : reviews) {
      // Setting the relationship
      course.addReview(review);
      review.setCourse(course);

      // save it to the database
      entityManager.persist(review);
    }
  }

  /**
   * Fetching default values: 1. *ToOne - Eager fetching by default (OneToOne and ManyToOne) 2.
   * *ToMany - Lazy fetching by default (OneToMany and ManyToMany)
   */
}
