package io.playground.springjdbcdb.jpainheritance.entity;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {

  private String line1;

  private String line2;

  private String city;
}
