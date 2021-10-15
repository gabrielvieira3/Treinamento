package com.gt.trainee.helpers;

import com.gt.trainee.core.Config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelpers {
    private StringHelpers() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatTimetable(Date s) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(Config.dateFormatTimetable);

        return dateFormat.format(s);

    }

    public static String formatDate(Date s) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(Config.dateFormat);

        return dateFormat.format(s);

    }

    public static Date parseDateISO(String s) {

        try {

            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(s);

        } catch (ParseException e) {


            return null;

        }

    }

    public static Date parseDateIncompleteISO(String s) {

        try {

            return new SimpleDateFormat("yyyy-MM-dd").parse(s);

        } catch (ParseException e) {


            return null;

        }

    }

    public static Date parseDateISOTimezone(String s) {

        try {

            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(s);

        } catch (ParseException e) {


            return null;

        }

    }

    public static Date parseDate(String s) {

        try {

            return new SimpleDateFormat(Config.dateFormat).parse(s);

        } catch (ParseException e) {


            return null;

        }

    }

    public static Date parseDateTimetable(String s) {

        try {

            return new SimpleDateFormat(Config.dateFormatTimetable).parse(s);

        } catch (ParseException e) {


            return null;

        }

    }
}
