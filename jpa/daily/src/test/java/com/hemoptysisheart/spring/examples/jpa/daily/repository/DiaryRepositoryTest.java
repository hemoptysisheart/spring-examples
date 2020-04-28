package com.hemoptysisheart.spring.examples.jpa.daily.repository;

import com.hemoptysisheart.spring.examples.jpa.daily.JpaDailyTestConfiguration;
import com.hemoptysisheart.spring.examples.jpa.daily.entity.Diary;
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
 * @author justburrow
 * @since 2020/04/28
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JpaDailyTestConfiguration.class)
class DiaryRepositoryTest {
  protected static final Logger log = getLogger(DiaryRepositoryTest.class);

  @Autowired
  private DiaryRepository repository;

  @Test
  void test_findAll() {
    List<Diary> list = this.repository.findAll();
    log.info("WHEN - list={}", list);

    assertThat(list)
        .isNotNull();
  }
}
