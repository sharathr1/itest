/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip.itest.common;

import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, java.sql.Time> {

  @Override
  public java.sql.Time convertToDatabaseColumn(LocalTime entityValue) {
    if (entityValue != null) {
      return java.sql.Time.valueOf(entityValue);
    }
    return null;
  }

  @Override
  public LocalTime convertToEntityAttribute(java.sql.Time databaseValue) {
    if (databaseValue != null) {
      return databaseValue.toLocalTime();
    }
    return null;
  }


}