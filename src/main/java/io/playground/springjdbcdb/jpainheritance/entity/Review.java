package io.playground.springjdbcdb.jpainheritance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

  @Id @GeneratedValue private Long id;

  @Column private String rating;

  @Column private String description;

  @ManyToOne
  private Course course;

  public Review(String rating, String description) {
    this.rating = rating;
    this.description = description;
  }
}
