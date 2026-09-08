package attendance;

import java.time.LocalDate;
import java.time.LocalTime;

public final class AttendancePolicy {
    private static final LocalTime OPERATION_START = LocalTime.of(8, 0);
    private static final LocalTime OPERATION_END = LocalTime.of(23, 0);
    private AttendancePolicy() {
    }
    public static boolean isOperationTime(LocalTime time) {
        return !time.isBefore(OPERATION_START) && !time.isAfter(OPERATION_END);
    }
    public static LocalTime getStartTime(LocalDate date) {
        if (AttendanceDayType.from(date.getDayOfWeek()) == AttendanceDayType.MONDAY) {
            return LocalTime.of(13, 00);
        }
        if (AttendanceDayType.from(date.getDayOfWeek()) == AttendanceDayType.WEEKDAY) {
            return LocalTime.of(10, 00);
        }
        throw new IllegalArgumentException("[ERROR] 이상한 접근, 주말엔 교육 시작시간이 없습니다.");
    }
}