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
class DiaryRepositoryTest {
  protected static final Logger log = getLogger(DiaryRepositoryTest.class);

  @Autowired
  private DiaryRepository repository;
  @Autowired
  private PersonRepository personRepository;

  @Test
  void test_findAll() {
    List<Diary> list = this.repository.findAll();
    log.info("WHEN - list={}", list);

    assertThat(list)
        .isNotNull();
  }

  @Test
  void test_save() {
    // GIVEN
    Person person = this.personRepository.save(new Person("test person"));
    log.info("GIVEN - person={}", person);

    LocalDate date = LocalDate.now();
    String content = "test diary";
    Diary diary = new Diary(person, date, content);
    log.info("GIVEN - diary={}", diary);

    // WHEN
    Diary saved = this.repository.save(diary);
    log.info("WHEN - saved={}", saved);

    // THEN
    assertThat(saved)
        .isNotNull()
        .extracting(Diary::getPerson, Diary::getDate, Diary::getContent)
        .containsSequence(person, date, content);
  }
}
