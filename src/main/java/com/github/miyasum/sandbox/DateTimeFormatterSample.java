package com.github.miyasum.sandbox;

import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.NANO_OF_SECOND;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * @author MIYASAKA Yasumasa
 */
public class DateTimeFormatterSample {

  public static void main(String[] args) {
    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
      .append(DateTimeFormatter.ISO_LOCAL_DATE)
      .appendLiteral('T')
      .appendValue(HOUR_OF_DAY, 2)
      .appendLiteral(':')
      .appendValue(MINUTE_OF_HOUR, 2)
      .appendLiteral(':')
      .appendValue(SECOND_OF_MINUTE, 2)
      .appendLiteral('.')
      .appendFraction(NANO_OF_SECOND, 6, 6, false)
      .appendOffsetId()
      .toFormatter();
    System.out.println(ZonedDateTime.now().format(formatter));
  }
}
