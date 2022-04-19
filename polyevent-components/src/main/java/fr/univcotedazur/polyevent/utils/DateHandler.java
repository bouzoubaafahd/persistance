package fr.univcotedazur.polyevent.utils;

import fr.univcotedazur.polyevent.entities.TimeSlot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateHandler {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static Date parseStringToDate(String date) {
        try {
            return SIMPLE_DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static TimeSlot createTimeSlot(String startDate, String endDate) {
        Date start = parseStringToDate(startDate);
        Date end = parseStringToDate(endDate);
        TimeSlot timeSlot = new TimeSlot(start, end);
        return timeSlot;
    }

    public static boolean isExpired(Date date) {
        return date.before(new Date());
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static Date getDateAfterDays(Date date, int days) {
        return new Date(date.getTime() + days * 24 * 60 * 60 * 1000);
    }

    public static Date getDateAfterHours(Date date, int hours) {
        return new Date(date.getTime() + hours * 60 * 60 * 1000);
    }

    public static Date getDateAfterMinutes(Date date, int minutes) {
        return new Date(date.getTime() + minutes * 60 * 1000);
    }

    public static TimeSlot getTimeSlotAfterDays(TimeSlot timeSlot, int days) {
        return new TimeSlot(getDateAfterDays(timeSlot.getStartDate(), days), getDateAfterDays(timeSlot.getEndDate(), days));
    }


    public static TimeSlot createAFutureTimeSlot() {
        return createAFutureTimeSlot(2, 2);
    }

    public static TimeSlot createAFutureTimeSlot(int days, int durationInHours) {
        Date currentDate = getCurrentDate();
        Date start = getDateAfterDays(currentDate, days);
        Date end = getDateAfterHours(start, durationInHours);
        return new TimeSlot(start, end);
    }

    public static boolean areOverlapping(TimeSlot timeSlot1, TimeSlot timeSlot2) {
        return timeSlot1.getStartDate().before(timeSlot2.getEndDate()) && timeSlot2.getStartDate().before(timeSlot1.getEndDate())
                || timeSlot2.getStartDate().before(timeSlot1.getEndDate()) && timeSlot1.getStartDate().before(timeSlot2.getEndDate())
                || timeSlot1.getStartDate().equals(timeSlot2.getStartDate()) || timeSlot1.getEndDate().equals(timeSlot2.getEndDate())
                || timeSlot1.getStartDate().before(timeSlot2.getStartDate()) && timeSlot2.getEndDate().before(timeSlot1.getEndDate())
                || timeSlot2.getStartDate().before(timeSlot1.getStartDate()) && timeSlot1.getEndDate().before(timeSlot2.getEndDate());

    }

    public static int getDaysBetween(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static Date getDateBeforeDays(Date date, int days) {
        return new Date(date.getTime() - days * 24 * 60 * 60 * 1000);
    }

    public static double getDurationInHours(TimeSlot timeSlot) {
        return (timeSlot.getEndDate().getTime() - timeSlot.getStartDate().getTime()) / (1000 * 60 * 60);
    }
}



