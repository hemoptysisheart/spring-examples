package com.hemoptysisheart.spring.examples.jpa.daily.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.String.format;

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
  @OneToMany(mappedBy = "person")
  @MapKey(name = "date") // Map<Person.date, Person>
  @OrderBy("date ASC")  // 이 조건이 없으면 Diary.id 순으로 정렬됨.
  private Map<LocalDate, Diary> diary = new HashMap<>();

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

  public Map<LocalDate, Diary> getDiary() {
    return this.diary;
  }

  public Diary getDiary(LocalDate date) {
    if (null == date)
      throw new IllegalArgumentException("date is null.");
    return this.diary.get(date);
  }

  public Map<LocalDate, Diary> getDiary(LocalDate from, LocalDate to) {
    if (null == from)
      throw new IllegalArgumentException("from is null.");
    if (null == to)
      throw new IllegalArgumentException("to is null.");
    if (from.isAfter(to))
      throw new IllegalArgumentException(format("illegal range : from=%s, to=%s", from, to));

    Map<LocalDate, Diary> temp = new LinkedHashMap<>();
    LocalDate date = from;
    while (date.isBefore(to) || date.equals(to)) {
      Diary diary = this.diary.get(date);
      if (null != diary) {
        temp.put(date, diary);
      }
      date = date.plusDays(1L);
    }
    return temp;
  }

  public void add(Diary diary) {
    if (null == diary)
      throw new IllegalArgumentException("diary is null.");

    this.diary.put(diary.getDate(), diary);
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

  @Override
  public String toString() {
    return format("(id=%d, name='%s', diary=%s)", this.id, this.name, this.diary);
  }
}
