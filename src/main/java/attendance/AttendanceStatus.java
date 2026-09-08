package attendance;

import java.time.LocalDate;
import java.time.LocalTime;

public enum AttendanceStatus {
    PRESENT,
    LATE,
    ABSENT;

    public static AttendanceStatus from(LocalDate date, LocalTime time) {
        if (time.isBefore(LocalTime.of(10, 6))) {
            return PRESENT;
        }

        if (time.isBefore(LocalTime.of(10, 31))) {
            return LATE;
        }

        return ABSENT;
    }
}