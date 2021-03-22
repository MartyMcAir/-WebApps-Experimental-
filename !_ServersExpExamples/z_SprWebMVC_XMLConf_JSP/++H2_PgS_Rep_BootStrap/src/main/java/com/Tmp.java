package com;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Tmp {
    public static void main(String[] args) {
//        method();

    }

    private static void method() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.UK);

        String formattedDate = dateFormat.format(date);
        System.out.println(formattedDate); // 16 February 2021 at 18:18:26 MSK
    }
}
