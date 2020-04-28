package com.hemoptysisheart.spring.examples.jpa.daily.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import static java.lang.String.format;

/**
 * 복합PK를 가진, {@link Person2}의 일부인 일기.
 *
 * @since 2020/04/28
 */
@Entity(name = "Diary2")
@Table(name = "diary2")
public class Diary2 {
  @SuppressWarnings("JpaAttributeMemberSignatureInspection")
  @Embeddable
  public static class Diary2Id implements Serializable {
    private long person;
    private LocalDate date;

    public Diary2Id() {
    }

    public Diary2Id(long person, LocalDate date) {
      if (0L >= person) throw new IllegalArgumentException("person is not positive : " + person);
      if (null == date) throw new IllegalArgumentException("date is null.");

      this.person = person;
      this.date = date;
    }

    public Diary2Id(Person2 person, LocalDate date) {
      this(person.getId(), date);
    }

    public long getPerson() {
      return this.person;
    }

    public LocalDate getDate() {
      return this.date;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Diary2Id that = (Diary2Id) o;
      return this.person == that.person &&
                 this.date.equals(that.date);
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
  @ManyToOne
  @JoinColumn(name = "person", nullable = false, insertable = false, updatable = false,
      foreignKey = @ForeignKey(name = "fk_diary2_pk_person2"), referencedColumnName = "id")
  private Person2 person;
  @Column(name = "date", nullable = false, insertable = false, updatable = false)
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

    if (0L < person.getId()) {
      this.id = new Diary2Id(person, date);
    }
    this.person = person;
    this.date = date;
    this.content = content;
  }

  @PrePersist
  private void prePersist() {
    if (null == this.id) {
      this.id = new Diary2Id(this.person, this.date);
    }
  }

  public Diary2Id getId() {
    if (null == this.id) {
      throw new IllegalStateException("id is null.");
    }
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
    return this.id == ((Diary2) o).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public String toString() {
    return format("(id=%s, person=(%d, %s), date=%s, content=%s)",
        this.id, this.person.getId(), this.person.getName(), this.content);
  }
}
