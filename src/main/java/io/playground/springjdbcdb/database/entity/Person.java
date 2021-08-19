package io.playground.springjdbcdb.database.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Person {

  private int id;
  private String name;
  private String location;
  private Date birthDate;
}
