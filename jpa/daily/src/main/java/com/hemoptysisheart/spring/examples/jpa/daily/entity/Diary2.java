package com.hemoptysisheart.spring.examples.jpa.daily.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import static java.lang.String.format;

/**
 * @since 2020/04/28
 */
@Entity(name = "Diary2")
@Table(name = "diary2")
public class Diary2 {
  @Embeddable
  public static class Diary2Id implements Serializable {
    private long person;
    private LocalDate date;

    public Diary2Id() {
    }

    public Diary2Id(long person, LocalDate date) {
      this.person = person;
      this.date = date;
    }

    public Diary2Id(Person2 person, LocalDate date) {
      this(person.getId(), date);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Diary2Id diary2Id = (Diary2Id) o;
      return this.person == diary2Id.person &&
                 this.date.equals(diary2Id.date);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.person, this.date);
    }

    @Override
    public String toString() {
      return format("(%d, %s)", this.person, this.date);
    }
  }

  @EmbeddedId
  @AttributeOverrides({
      @AttributeOverride(name = "person", column = @Column(name = "person", nullable = false, updatable = false)),
      @AttributeOverride(name = "date", column = @Column(name = "date", nullable = false, updatable = false))
  })
  private Diary2Id id;
  @ManyToOne(optional = false)
  @JoinColumn(name = "person", nullable = false, insertable = false, updatable = false,
      foreignKey = @ForeignKey(name = "fk_diary2_pk_person2"), referencedColumnName = "id")
  @MapsId("person")
  private Person2 person;
  @Column(name = "date", nullable = false, insertable = false, updatable = false)
  @MapsId("date")
  private LocalDate date;
  @Column(name = "content", nullable = false)
  private String content;

  public Diary2() {
  }

  public Diary2(Person2 person, LocalDate date) {
    this(person, date, "");
  }

  public Diary2(Person2 person, LocalDate date, String content) {
    if (null == person) throw new IllegalArgumentException("person is null.");
    if (null == date) throw new IllegalArgumentException("date is null.");
    if (null == content) throw new IllegalArgumentException("content is null.");

    this.id = new Diary2Id(person, date);
    this.person = person;
    this.date = date;
    this.content = content;
  }

  public Diary2Id getId() {
    return this.id;
  }

  public Person2 getPerson() {
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
    Diary2 diary = (Diary2) o;
    return this.id == ((Diary2) o).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public String toString() {
    return format("(id=%s, person=(%d, %s), date=%s, content=%s)",
        this.id, this.person.getId(), this.person.getName(), this.date, this.content);
  }
}
