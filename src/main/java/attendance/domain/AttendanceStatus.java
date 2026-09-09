package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public enum AttendanceStatus {
    PRESENT("출석"),
    LATE("지각"),
    ABSENT("결석");

    private final String value;
    AttendanceStatus(String text) {
        this.value = text ;
    }
    public String getValue() {
        return value;
    }

    public static AttendanceStatus from(LocalDate date, LocalTime time) {
        if (time == null) {
            return AttendanceStatus.ABSENT;
        }
        LocalTime startTime = AttendancePolicy.getStartTime(date);
        if (time.isBefore(startTime.plusMinutes(6))) {
            return PRESENT;
        }
        if (time.isBefore(startTime.plusMinutes(31))) {
            return LATE;
        }
        return ABSENT;
    }
}