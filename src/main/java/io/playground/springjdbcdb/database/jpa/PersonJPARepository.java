package io.playground.springjdbcdb.database.jpa;

import io.playground.springjdbcdb.database.entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PersonJPARepository {

  @PersistenceContext EntityManager entityManager;

  public List<Person> findAll() {
    TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_person", Person.class);
    return namedQuery.getResultList();
  }

  public Person findById(int id) {
    return entityManager.find(Person.class, id);
  }

  public Person upsert(Person person) {
    return entityManager.merge(person);
  }

  public void deleteById(int id) throws Exception {
    Person person = findById(id);
    if (person != null) {
      entityManager.remove(person);
    } else {
      throw new Exception("Person not found!");
    }
  }
}
