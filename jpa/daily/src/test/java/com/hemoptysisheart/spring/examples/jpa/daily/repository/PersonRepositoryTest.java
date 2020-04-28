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
import java.util.List;

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
}
