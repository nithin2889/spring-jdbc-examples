package io.playground.springjdbcdb.jpainheritance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

  @Enumerated(EnumType.STRING)
  private ReviewRating rating;

  @Column private String description;

  @ManyToOne private Course course;

  public Review(ReviewRating rating, String description) {
    this.rating = rating;
    this.description = description;
  }
}
