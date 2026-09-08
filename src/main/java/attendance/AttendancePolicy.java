package attendance;

import java.time.LocalTime;

public final class AttendancePolicy {
    private static final LocalTime OPERATION_START = LocalTime.of(8, 0);
    private static final LocalTime OPERATION_END = LocalTime.of(23, 0);
    private AttendancePolicy() {
    }
    public static boolean isOperationTime(LocalTime time) {
        return !time.isBefore(OPERATION_START) && !time.isAfter(OPERATION_END);
    }
}