package com.hemoptysisheart.spring.examples.jpa.daily.repository;

import com.hemoptysisheart.spring.examples.jpa.daily.JpaDailyTestConfiguration;
import com.hemoptysisheart.spring.examples.jpa.daily.entity.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @since 2020/04/28
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JpaDailyTestConfiguration.class)
public class PersonRepositoryTest {
  protected static final Logger log = getLogger(PersonRepositoryTest.class);

  @Autowired
  private PersonRepository repository;

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
}
