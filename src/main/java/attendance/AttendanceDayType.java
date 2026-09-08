package attendance;

import java.time.DayOfWeek;
import java.time.LocalTime;

public enum AttendanceDayType {
    MONDAY,
    WEEKDAY,
    WEEKEND;

    public static AttendanceDayType from(DayOfWeek dayOfWeek) {
        if (dayOfWeek == DayOfWeek.MONDAY) {
            return MONDAY;
        }
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return WEEKEND;
        }
        return WEEKDAY;
    }
}