package com.hemoptysisheart.spring.examples.jpa.daily.repository;

import com.hemoptysisheart.spring.examples.jpa.daily.JpaDailyTestConfiguration;
import com.hemoptysisheart.spring.examples.jpa.daily.entity.Diary2;
import com.hemoptysisheart.spring.examples.jpa.daily.entity.Person2;
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
 * @since 2020/04/29
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JpaDailyTestConfiguration.class)
@Transactional
class Person2RepositoryTest {
  protected static final Logger log = getLogger(Person2RepositoryTest.class);

  @Autowired
  private Person2Repository repository;
  @Autowired
  private EntityManager entityManager;

  @Test
  void test_findAll() {
    List<Person2> list = this.repository.findAll();

    assertThat(list)
        .isNotNull();
  }

  @Test
  void test_save() {
    // GIVEN
    Person2 person = new Person2("person2");
    log.info("GIVEN - person={}", person);

    // WHEN
    Person2 saved = this.repository.save(person);
    log.info("WHEN - saved={}", saved);

    // THEN
    assertThat(saved)
        .isNotNull();
    assertThat(saved.getId())
        .isPositive();
  }

  @Test
  void test_save_with_diary() {
    // GIVEN
    Person2 person = new Person2("person2");
    log.info("GIVEN - person={}", person);

    Diary2 diary = new Diary2(person, LocalDate.now(), "diary2 content.");
    person.add(diary);
    log.info("GIVEN - diary={}", diary);

    // WHEN
    Person2 saved = this.repository.saveAndFlush(person);
    log.info("WHEN - saved={}", saved);
    this.entityManager.clear();

    // THEN
    Person2 target = this.repository.findById(saved.getId()).get();
    log.info("THEN - target={}", target);
    assertThat(target)
        .isNotNull()
        .isEqualTo(person)
        .isNotSameAs(person);
    assertThat(target.getDiary())
        .hasSize(1);
    assertThat(target.getDiary().get(0))
        .isNotNull()
        .extracting(Diary2::getId, Diary2::getContent)
        .containsSequence(new Diary2.Diary2Id(person, LocalDate.now()), "diary2 content.");
  }
}
