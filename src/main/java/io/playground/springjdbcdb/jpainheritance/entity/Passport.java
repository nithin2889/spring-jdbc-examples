package io.playground.springjdbcdb.jpainheritance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Passport {

  @Id @GeneratedValue private Long id;

  @Column(nullable = false)
  private String number;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
  private Student student;

  public Passport(String number) {
    this.number = number;
  }
}
