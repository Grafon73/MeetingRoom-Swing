package com.grafon.test.calendarDB;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CalendarDB {
    static String[] columnHeaders;

    public void setNames(Date date) {
        CalendarTasks calendarTasks = new CalendarTasks();
        calendarTasks.changeTableHeader(date);
    }


    public  String[] getColumnNames(Date date) {
        String[] columnNames = null;

        String dayOfTheWeek = new SimpleDateFormat("EEEE").format(date);

        switch (dayOfTheWeek) {
            case ("понедельник"):
                columnNames = columnNamesCheck(date, 0);
                break;
            case ("вторник"):
                columnNames = columnNamesCheck(date, 1);
                break;
            case ("среда"):
                columnNames = columnNamesCheck(date, 2);
                break;
            case ("четверг"):
                columnNames = columnNamesCheck(date, 3);
                break;
            case ("пятница"):
                columnNames = columnNamesCheck(date, 4);
                break;
            case ("суббота"):
                columnNames = columnNamesCheck(date, 5);
                break;
            case ("воскресенье"):
                columnNames = columnNamesCheck(date, 6);
                break;

        }
        columnHeaders=columnNames;
        return columnNames;
    }


    public static String[] columnNamesCheck(Date date, int n) {
        String[] columnNames = new String[7];
        LocalDate ldate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        switch (n) {
            case (0):
                columnNames = new String[]{"",
                        "Понедельник  " + ldate.minusDays(0),
                        "Вторник " + ldate.minusDays(-1),
                        "Среда " + ldate.minusDays(-2),
                        "Четверг " + ldate.minusDays(-3),
                        "Пятница " + ldate.minusDays(-4),
                        "Суббота " + ldate.minusDays(-5),
                        "Воскресенье " + ldate.minusDays(-6)
                };
                break;
            case (1):
                columnNames = new String[]{"",
                        "Понедельник  " + ldate.minusDays(1),
                        "Вторник " + ldate.minusDays(0),
                        "Среда " + ldate.minusDays(-1),
                        "Четверг " + ldate.minusDays(-2),
                        "Пятница " + ldate.minusDays(-3),
                        "Суббота " + ldate.minusDays(-4),
                        "Воскресенье " + ldate.minusDays(-5)
                };
                break;
            case (2):
                columnNames = new String[]{"",
                        "Понедельник  " + ldate.minusDays(2),
                        "Вторник " + ldate.minusDays(1),
                        "Среда " + ldate.minusDays(0),
                        "Четверг " + ldate.minusDays(-1),
                        "Пятница " + ldate.minusDays(-2),
                        "Суббота " + ldate.minusDays(-3),
                        "Воскресенье " + ldate.minusDays(-4)
                };
                break;

            case (3):
                columnNames = new String[]{"",
                        "Понедельник  " + ldate.minusDays(3),
                        "Вторник " + ldate.minusDays(2),
                        "Среда " + ldate.minusDays(1),
                        "Четверг " + ldate.minusDays(0),
                        "Пятница " + ldate.minusDays(-1),
                        "Суббота " + ldate.minusDays(-2),
                        "Воскресенье " + ldate.minusDays(-3)
                };
                break;
            case (4):
                columnNames = new String[]{"",
                        "Понедельник  " + ldate.minusDays(4),
                        "Вторник " + ldate.minusDays(3),
                        "Среда " + ldate.minusDays(2),
                        "Четверг " + ldate.minusDays(1),
                        "Пятница " + ldate.minusDays(0),
                        "Суббота " + ldate.minusDays(-1),
                        "Воскресенье " + ldate.minusDays(-2)
                };
                break;
            case (5):
               columnNames = new String[]{"",
                        "Понедельник  " + ldate.minusDays(5),
                        "Вторник " + ldate.minusDays(4),
                        "Среда " + ldate.minusDays(3),
                        "Четверг " + ldate.minusDays(2),
                        "Пятница " + ldate.minusDays(1),
                        "Суббота " + ldate.minusDays(0),
                        "Воскресенье " + ldate.minusDays(-1)
                };
                break;
            case (6):

                columnNames = new String[]{"",
                        "Понедельник  " + ldate.minusDays(6),
                        "Вторник " + ldate.minusDays(5),
                        "Среда " + ldate.minusDays(4),
                        "Четверг " + ldate.minusDays(3),
                        "Пятница " + ldate.minusDays(2),
                        "Суббота " + ldate.minusDays(1),
                        "Воскресенье " + ldate.minusDays(0)
                };

        }

                return columnNames;

    }

    public String[] getColumnHeaders() {
        return columnHeaders;
    }
}
