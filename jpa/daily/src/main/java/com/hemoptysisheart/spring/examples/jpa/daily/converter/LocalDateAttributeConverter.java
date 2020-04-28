package com.hemoptysisheart.spring.examples.jpa.daily.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @since 2020/04/29
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, String> {
  @Override
  public String convertToDatabaseColumn(LocalDate attribute) {
    return null == attribute
               ? null
               : attribute.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  @Override
  public LocalDate convertToEntityAttribute(String dbData) {
    return null == dbData
               ? null
               : LocalDate.parse(dbData, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }
}
