package com.hemoptysisheart.spring.examples.jpa.daily.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import static java.lang.String.format;

/**
 * @since 2020/04/28
 */
@Entity(name = "Diary")
@Table(name = "diary")
public class Diary {
  @SuppressWarnings("JpaAttributeMemberSignatureInspection")
  @Embeddable
  public static class DiaryId implements Serializable {
    private long person;
    private LocalDate date;

    public DiaryId() { // JPA only
    }

    public DiaryId(long person, LocalDate date) {
      if (0L >= person) throw new IllegalArgumentException("person is null.");
      if (null == date) throw new IllegalArgumentException("date is null.");

      this.person = person;
      this.date = date;
    }

    public DiaryId(Person person, LocalDate date) {
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
      DiaryId diaryId = (DiaryId) o;
      return this.person == diaryId.person &&
                 this.date.equals(diaryId.date);
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
  @AttributeOverrides(
      {@AttributeOverride(name = "person", column = @Column(name = "person", nullable = false, updatable = false)),
          @AttributeOverride(name = "date", column = @Column(name = "date", nullable = false, updatable = false))})
  private DiaryId id;
  @ManyToOne(targetEntity = Person.class)
  @JoinColumn(name = "person", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "fk_diary_pk_person"), referencedColumnName = "id")
  @MapsId("person")
  private Person person;
  @Column(name = "date", nullable = false, updatable = false)
  @MapsId("date")
  private LocalDate date;
  @Column(name = "content", nullable = false)
  private String content;

  public Diary() {
  }

  public Diary(Person person, LocalDate date) {
    this(person, date, "");
  }

  public Diary(Person person, LocalDate date, String content) {
    if (null == content) throw new IllegalArgumentException("content is null.");

    this.id = new DiaryId(person, date);
    this.person = person;
    this.date = date;
    this.content = content;
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
    return this.id.equals(diary.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }
}
