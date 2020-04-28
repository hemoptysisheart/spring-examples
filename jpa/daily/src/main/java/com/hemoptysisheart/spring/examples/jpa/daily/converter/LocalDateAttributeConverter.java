package com.hemoptysisheart.spring.examples.jpa.daily.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * @since 2020/04/28
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {
  @Override
  public Date convertToDatabaseColumn(LocalDate localDate) {
    return null == localDate
               ? null
               : Date.valueOf(localDate);
  }

  @Override
  public LocalDate convertToEntityAttribute(Date date) {
    return null == date
               ? null
               : date.toLocalDate();
  }
}