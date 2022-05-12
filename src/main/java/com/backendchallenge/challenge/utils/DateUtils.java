package com.backendchallenge.challenge.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public final class DateUtils {

    public static LocalDateTime getYesterday() {
        LocalDateTime endDate = LocalDateTime.of(
            LocalDateTime.now().getYear(),
            LocalDateTime.now().getMonth(),
            LocalDateTime.now().getDayOfMonth(),
            LocalDateTime.now().getHour(),
            LocalDateTime.now().getMinute(),
            LocalDateTime.now().getSecond()
        ).minus(1, ChronoUnit.DAYS);

        return endDate;
    }

    public static LocalDateTime getNow() {
        LocalDateTime endDate = LocalDateTime.of(
                LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth(),
                LocalDateTime.now().getHour(),
                LocalDateTime.now().getMinute(),
                LocalDateTime.now().getSecond()
        );

        return endDate;
    }
}
