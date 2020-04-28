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
  @MapKey(name = "date")
  @OrderBy("date ASC")
  private Map<LocalDate, Diary2> diary = new HashMap<>();

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

  public Map<LocalDate, Diary2> getDiary() {
    return this.diary;
  }

  public Diary2 getDiary(LocalDate date) {
    if (null == date)
      throw new IllegalArgumentException("date is null.");
    return this.diary.get(date);
  }

  public Map<LocalDate, Diary2> getDiary(LocalDate from, LocalDate to) {
    if (null == from)
      throw new IllegalArgumentException("from is null.");
    if (null == to)
      throw new IllegalArgumentException("to is null.");
    if (from.isAfter(to))
      throw new IllegalArgumentException(format("illegal range : from=%s, to=%s", from, to));

    Map<LocalDate, Diary2> temp = new LinkedHashMap<>();
    LocalDate date = from;
    while (date.isBefore(to) || date.equals(to)) {
      Diary2 diary = this.diary.get(date);
      if (null != diary) {
        temp.put(date, diary);
      }
      date = date.plusDays(1L);
    }
    return temp;
  }

  public void add(Diary2 diary) {
    if (null == diary)
      throw new IllegalArgumentException("diary is null.");

    this.diary.put(diary.getDate(), diary);
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
    return format("(id=%d, name='%s', diary=%s)", this.id, this.name, this.diary);
  }
}
