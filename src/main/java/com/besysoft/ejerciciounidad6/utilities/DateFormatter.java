package com.besysoft.ejerciciounidad6.utilities;

import java.util.Calendar;
import java.util.Date;

public class DateFormatter {
    public static Date fechaFormatter(int day, int month, int year) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }
}
