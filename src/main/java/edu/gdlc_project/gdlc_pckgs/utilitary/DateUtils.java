package edu.gdlc_project.gdlc_pckgs.utilitary;

import java.time.LocalDate;
import java.time.ZoneId;
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
    }

