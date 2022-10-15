package com.ershgem.mf.util;

public class Util {

    public static double ticksToDays(double ticks) {
        double seconds = ticks/20;
        double minutes = seconds / 60;
        double hours = minutes / 60;
        double days = hours/24;
        return days;
    }

    /**
     * Convert ticks to ticks
     * @param seconds
     * @return
     */
    public static int secondsToTicks(int seconds) {
        return seconds * 20;
    }

    /**
     * Each minecraft day is 20 minutes
     * @param minutes
     * @return
     */
    public static int minutesToSeconds(int minutes) {
        return minutes * secondsToTicks(60);
    }

    /**
     * Each minecraft day is 20 minutes
     * @param mcDays
     * @return
     */
    public static int mcDaysToMinutes(int mcDays) {
        return mcDays * minutesToSeconds(20);
    }

    public static int hoursToMinutes(int hours) {
        return hours * minutesToSeconds(60);
    }

    public static int daysFromHours(int days) {
        return days * hoursToMinutes(24);
    }
}
