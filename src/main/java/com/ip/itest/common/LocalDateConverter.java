/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.common;

import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, java.sql.Date> {

  @Override
  public java.sql.Date convertToDatabaseColumn(LocalDate entityValue) {
    if (entityValue != null) {
      return java.sql.Date.valueOf(entityValue);
    }
    return null;
  }

  @Override
  public LocalDate convertToEntityAttribute(java.sql.Date databaseValue) {
    if (databaseValue != null) {
      return databaseValue.toLocalDate();
    }
    return null;
  }
}