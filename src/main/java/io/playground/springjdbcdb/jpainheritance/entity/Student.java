package io.playground.springjdbcdb.jpainheritance.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

  @Id @GeneratedValue private Long id;

  @Column(nullable = false)
  private String name;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "passport_id")
  private Passport passport;

  @ManyToMany
  @JoinTable(
      name = "student_course",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id"))
  private List<Course> courses = new ArrayList<>();

  public Student(String name) {
    this.name = name;
  }

  public void addCourse(Course course) {
    this.courses.add(course);
  }

  public void removeCourse(Course course) {
    this.courses.remove(course);
  }

  @Override
  public String toString() {
    return String.format("Student[%s]", name);
  }
}
