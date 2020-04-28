package com.hemoptysisheart.spring.examples.jpa.daily;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author justburrow
 * @since 2020/04/28
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class JpaDailyConfiguration {
}
