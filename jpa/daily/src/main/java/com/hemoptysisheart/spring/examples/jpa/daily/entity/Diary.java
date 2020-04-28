package com.hemoptysisheart.spring.examples.jpa.daily.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

import static java.lang.String.format;

/**
 * {@code AUTO_INCREMENT} PK를 대체키로 가진 일기.
 *
 * @since 2020/04/28
 */
@Entity(name = "Diary")
@Table(name = "diary",
    uniqueConstraints = {@UniqueConstraint(name = "uq_diary", columnNames = {"person", "date"})},
    indexes = {@Index(name = "idx_diary_date", columnList = "date ASC")})
public class Diary {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
  private long id;
  @ManyToOne(targetEntity = Person.class, optional = false)
  @JoinColumn(name = "person", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "fk_diary_pk_person"), referencedColumnName = "id")
  private Person person;
  @Column(name = "date", nullable = false, updatable = false)
  private LocalDate date;
  @Column(name = "content", nullable = false)
  private String content;

  public Diary() {
  }

  public Diary(Person person, LocalDate date) {
    this(person, date, "");
  }

  public Diary(Person person, LocalDate date, String content) {
    if (null == person) throw new IllegalArgumentException("person is null.");
    if (null == date) throw new IllegalArgumentException("date is null.");
    if (null == content) throw new IllegalArgumentException("content is null.");

    this.person = person;
    this.date = date;
    this.content = content;
  }

  public long getId() {
    return this.id;
  }

  public Person getPerson() {
    return this.person;
  }

  public LocalDate getDate() {
    return this.date;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    if (null == content) {
      content = "";
    }
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Diary diary = (Diary) o;
    return this.id == ((Diary) o).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public String toString() {
    return format("(id=%d, person=(%d, %s), date=%s, content=%s)",
        this.id, this.person.getId(), this.person.getName(), this.date, this.content);
  }
}
