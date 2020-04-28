package com.hemoptysisheart.spring.examples.jpa.daily.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @since 2020/04/28
 */
@Entity(name = "Person")
@Table(name = "person",
    uniqueConstraints = @UniqueConstraint(name = "uq_person_name", columnNames = {"name"}))
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = false, insertable = false, updatable = false)
  private long id;
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  public Person() {
  }

  public Person(String name) {
    setName(name);
  }

  public long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    if (null == name) throw new IllegalArgumentException("name is null.");
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return this.id == person.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }
}
