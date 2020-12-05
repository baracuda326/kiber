package com.gateway.gateway.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utils {
    public static Pattern pattern;
    public static Matcher matcher;
    public static final String JPEG = ".jpeg";
    //****************************************************************************
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    //****************************************************************************
    private static final String PHONE_NUMBER_PATTERN =
            "(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){8,14}(\\s*)?";

    //****************************************************************************

    private static final String FIELD_NAME_PATTERN =
            "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$";

    //****************************************************************************
    public static boolean isValidEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return !matcher.matches();
    }

    //****************************************************************************
    public static boolean isValidPhoneNumber(String phoneNumber) {
        pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        matcher = pattern.matcher(phoneNumber);
        return !matcher.matches();
    }

    //****************************************************************************
    public static boolean isValidPassword(String password) {
        int res = 0;
        if (password.length() >= 8) {
            res = res | 1; // 00001
        }
        char[] arr = password.toCharArray();
        for (char c : arr) {
            if (Character.isUpperCase(c)) {
                res = res | 2; // 00010
            }
            if (Character.isLowerCase(c)) {
                res = res | 4; // 00100
            }
            if (Character.isDigit(c)) {
                res = res | 8; // 01000
            }
        }
        return res != 15;
    }

    //****************************************************************************
    public static long getHoursTotalBetweenTwoDates(LocalDateTime start_Date_Time, LocalDateTime end_Date_Time) {
        LocalDateTime fromDateTime = LocalDateTime.of(start_Date_Time.getYear()
                , start_Date_Time.getMonth()
                , start_Date_Time.getDayOfMonth()
                , start_Date_Time.getHour()
                , start_Date_Time.getMinute());
        LocalDateTime toDateTime = LocalDateTime.of(end_Date_Time.getYear()
                , end_Date_Time.getMonth()
                , end_Date_Time.getDayOfMonth()
                , end_Date_Time.getHour()
                , end_Date_Time.getMinute());
        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);
        return Math.abs(tempDateTime.until(toDateTime, ChronoUnit.HOURS));
    }

    //****************************************************************************
    public static long getHoursTotalBetweenTwoDatesInMinutes(LocalDateTime start_Date_Time, LocalDateTime end_Date_Time) {
        LocalDateTime fromDateTime = LocalDateTime.of(start_Date_Time.getYear()
                , start_Date_Time.getMonth()
                , start_Date_Time.getDayOfMonth()
                , start_Date_Time.getHour()
                , start_Date_Time.getMinute());
        LocalDateTime toDateTime = LocalDateTime.of(end_Date_Time.getYear()
                , end_Date_Time.getMonth()
                , end_Date_Time.getDayOfMonth()
                , end_Date_Time.getHour()
                , end_Date_Time.getMinute());
        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);
        return Math.abs(tempDateTime.until(toDateTime, ChronoUnit.MINUTES));
    }

    //****************************************************************************
    public static boolean checkIfBoolean(String value) {
        return value.equals("true") || value.equals("false");
    }

    //****************************************************************************
    public static boolean checkIfDigits(String value) {
        try {
            Integer.valueOf(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    //****************************************************************************
    public static boolean isValidFieldName(String name) {
        if (name.length() >= 30) return true;
        pattern = Pattern.compile(FIELD_NAME_PATTERN);
        matcher = pattern.matcher(name);
        return !matcher.matches();
    }

    //****************************************************************************
    public static boolean isValidFieldContent(String message) {
        String[] chars = {"<", ">", "=", "*"};
        if (message.length() >= 200) return true;
        for (String charBuf : chars) {
            if (message.contains(charBuf)) return true;
        }
        return false;
    }

    //****************************************************************************
    public static String generateUUIDWithFormat() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(UUID.randomUUID().toString()).append(JPEG).toString();
    }
    //****************************************************************************

    public static int generateRandomCode(){
        return new Random().nextInt(89_999) + 10_000;
    }
}
