package io.playground.springjdbcdb;

import io.playground.springjdbcdb.jpainheritance.repository.CourseRepository;
import io.playground.springjdbcdb.jpainheritance.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringDemoApplication implements CommandLineRunner {

  @Autowired CourseRepository courseRepository;
  @Autowired StudentRepository studentRepository;

  public static void main(String[] args) {
    SpringApplication.run(SpringDemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    /*Course myCourse = courseRepository.findById(10001L);
    log.info("Course 10003 -> {}", myCourse);

    log.info("Removing course 10004...");
    courseRepository.deleteById_Basic(10004L);

    log.info("Creating a new course...");
    courseRepository.save(new Course("React Native"));
     courseRepository.playWithEntityManager_Flush_Detach_Clear();
     courseRepository.playWithEntityManager_Refresh();
     courseRepository.playWithEntityManager();
     studentRepository.saveStudentWithPassport();
     courseRepository.addHardcodedReviewsForCourse();

    List<Review> reviews = new ArrayList<>();
    reviews.add(new Review("2.5", "Okay-ish course"));
    reviews.add(new Review("3.5", "Above average course"));
    courseRepository.addReviewsForCourse(10003L, reviews);*/
  }
}
