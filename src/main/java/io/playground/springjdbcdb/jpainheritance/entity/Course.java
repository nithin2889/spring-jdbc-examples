package io.playground.springjdbcdb.jpainheritance.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table
@Data
@NamedQueries(
    value = {
      @NamedQuery(name = "query_get_all_courses", query = "Select c from Course c"),
      @NamedQuery(
          name = "query_get_100_steps_courses",
          query = "Select c from Course c where name like '%100 Steps'")
    })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

  @Id @GeneratedValue private Long id;

  @Column(nullable = false)
  private String name;

  @CreationTimestamp private LocalDateTime createdDate;

  @UpdateTimestamp private LocalDateTime lastUpdatedDate;

  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  private List<Review> reviews = new ArrayList<>();

  @ManyToMany(mappedBy = "courses")
  private List<Student> students = new ArrayList<>();

  public Course(String name) {
    this.name = name;
  }

  public void addReview(Review review) {
    this.reviews.add(review);
  }

  public void removeReview(Review review) {
    this.reviews.remove(review);
  }

  public void addStudent(Student student) {
    this.students.add(student);
  }

  public void removeStudent(Student student) {
    this.students.remove(student);
  }

  @Override
  public String toString() {
    return String.format("Course[%s]", name);
  }
}
