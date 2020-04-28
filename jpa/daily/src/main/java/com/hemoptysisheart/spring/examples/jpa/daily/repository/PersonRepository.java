package com.hemoptysisheart.spring.examples.jpa.daily.repository;

import com.hemoptysisheart.spring.examples.jpa.daily.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @since 2020/04/28
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
