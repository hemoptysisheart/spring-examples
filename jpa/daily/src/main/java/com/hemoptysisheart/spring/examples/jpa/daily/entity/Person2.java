package com.hemoptysisheart.spring.examples.jpa.daily.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;


/**
 * @since 2020/04/28
 */
@Entity(name = "Person2")
@Table(name = "person2",
    uniqueConstraints = @UniqueConstraint(name = "uq_person2_name", columnNames = "name"))
public class Person2 {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = false, insertable = false, updatable = false)
  private long id;
  @Column(name = "name", nullable = false, unique = true)
  private String name;
  @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
  @OrderBy("date ASC")
  private List<Diary2> diary = new ArrayList<>();

  public Person2() {
  }

  public Person2(String name) {
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

  public void add(Diary2 diary) {
    this.diary.add(diary);
  }

  public List<Diary2> getDiary() {
    return this.diary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person2 person = (Person2) o;
    return this.id == person.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public String toString() {
    return format("(id=%d, name='%s')", this.id, this.name);
  }
}
