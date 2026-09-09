package attendance.domain;

import java.time.DayOfWeek;

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