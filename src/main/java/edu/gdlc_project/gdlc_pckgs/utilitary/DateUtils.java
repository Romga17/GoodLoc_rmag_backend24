package edu.gdlc_project.gdlc_pckgs.utilitary;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

    public class DateUtils {
        public static Date convertToDateViaInstant(LocalDate dateToConvert) {
            return java.util.Date.from(dateToConvert.atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
        }

        public static java.sql.Date convertToSqlDate(LocalDate dateToConvert) {
            return java.sql.Date.valueOf(dateToConvert);
        }

        public static Long dateDistanceCheck(LocalDate datenow, LocalDate bookingStartDate) {
            long daysBetween = ChronoUnit.DAYS.between(datenow, bookingStartDate);
            return daysBetween;
        }

        public static LocalDate convertToLocalDate(Date date) {
            if (date instanceof java.sql.Date) {
                return ((java.sql.Date) date).toLocalDate();
            } else {
                return date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
            }
        }
    }

