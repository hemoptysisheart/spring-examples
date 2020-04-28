package com.hemoptysisheart.spring.examples.jpa.daily.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * @since 2020/04/29
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {
  @Override
  public Date convertToDatabaseColumn(LocalDate attribute) {
    return null == attribute
               ? null
               : Date.valueOf(attribute);
  }

  @Override
  public LocalDate convertToEntityAttribute(Date dbData) {
    return null == dbData
               ? null
               : dbData.toLocalDate();
  }
}
