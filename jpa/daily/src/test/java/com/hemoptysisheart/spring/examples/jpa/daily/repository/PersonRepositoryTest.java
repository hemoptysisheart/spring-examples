package com.hemoptysisheart.spring.examples.jpa.daily.repository;

import com.hemoptysisheart.spring.examples.jpa.daily.JpaDailyTestConfiguration;
import com.hemoptysisheart.spring.examples.jpa.daily.entity.Diary;
import com.hemoptysisheart.spring.examples.jpa.daily.entity.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.Map.entry;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @since 2020/04/28
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JpaDailyTestConfiguration.class)
@Transactional
public class PersonRepositoryTest {
  protected static final Logger log = getLogger(PersonRepositoryTest.class);

  @Autowired
  private PersonRepository repository;
  @Autowired
  private DiaryRepository diaryRepository;

  @Autowired
  private EntityManager entityManager;

  @Test
  public void test_findAll() throws Exception {
    // WHEN
    List<Person> all = this.repository.findAll();
    log.info("WHEN - all={}", all);

    // THEN
    assertThat(all)
        .isNotNull();
  }

  @Test
  public void test_save() throws Exception {
    // GIVEN
    String name = "test person";
    Person person = new Person(name);
    log.info("GIVEN - person={}", person);

    // WHEN
    Person saved = this.repository.save(person);
    log.info("WHEN - saved={}", saved);

    // THEN
    assertThat(saved)
        .isNotNull()
        .extracting(Person::getName)
        .isEqualTo(name);
  }

  @Test
  void test_load() {
    // GIVEN
    Person person = this.repository.save(new Person("person"));
    log.info("GIVEN - person={}", person);

    Diary diary = this.diaryRepository.save(new Diary(person, LocalDate.now(), "content"));
    log.info("GIVEN - diary={}", diary);

    this.entityManager.flush();
    this.entityManager.clear();

    // WHEN
    Person actual = this.repository.findById(person.getId()).get();
    log.info("WHEN - actual={}", actual);

    // THEN
    assertThat(actual)
        .isNotNull()
        .isEqualTo(person)
        .isNotSameAs(person);
    assertThat(actual.getDiary(LocalDate.now()))
        .isNotNull()
        .extracting(Diary::getId, Diary::getDate, Diary::getContent)
        .containsSequence(diary.getId(), LocalDate.now(), "content");
  }

  @Test
  void test_load_multiple_diary() {
    // GIVE
    Person person = this.repository.save(new Person("person"));
    log.info("GIVEN - person={}", person);

    LocalDate today = LocalDate.now();
    List<Diary> temp = new ArrayList<>(List.of(new Diary(person, today.minusDays(3L), "content1"),
        new Diary(person, today.minusDays(2L), "content2"),
        new Diary(person, today.minusDays(1L), "content3"),
        new Diary(person, today, "content4")));
    Collections.shuffle(temp);
    log.info("GIVEN - temp={}", temp);
    List<Diary> diaries = this.diaryRepository.saveAll(temp)
                              .stream()
                              .sorted(comparing(Diary::getDate))
                              .collect(toList());
    log.info("GIVEN - diaries={}", diaries);

    this.entityManager.flush();
    this.entityManager.clear();

    // WHEN
    Person actual = this.repository.findById(person.getId()).get();
    log.info("WHEN - actual={}", actual);

    // THEN
    assertThat(actual.getDiary())
        .isNotNull()
        .containsExactly(entry(today.minusDays(3L), diaries.get(0)),
            entry(today.minusDays(2L), diaries.get(1)),
            entry(today.minusDays(1L), diaries.get(2)),
            entry(today, diaries.get(3)));

    assertThat(actual.getDiary(today.minusDays(2L), today.minusDays(1L)))
        .isNotNull()
        .containsExactly(entry(today.minusDays(2L), diaries.get(1)),
            entry(today.minusDays(1L), diaries.get(2)));
  }

  @Test
  void test_add() {
    // GIVEN
    Person person = this.repository.save(new Person("person"));
    log.info("GIVEN - person={}", person);

    Diary diary = new Diary(person, LocalDate.now(), "content");
    log.info("GIVEN - diary={}", diary);

    // WHEN
    person.add(diary);
    this.entityManager.flush();
    log.info("WHEN - diary={}", diary);

    // THEN
    assertThat(diary.getId())
        .isPositive();
  }
}
