package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class AttendanceRecord {
    private final LocalDate date;
    private final LocalTime attendanceTime;
    private final AttendanceStatus status;
    public AttendanceRecord(LocalDate date, LocalTime time) {
        String dayStr = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        require(AttendanceDayType.from(date.getDayOfWeek()) == AttendanceDayType.WEEKEND,
                "[ERROR] %d월 %d일 %s은 등교일이 아닙니다.".formatted(date.getMonthValue(), date.getDayOfMonth(), dayStr);
        this.date = date ;
        this.attendanceTime = time ;
        this.status = AttendanceStatus.from(date, attendanceTime);
    }
    public AttendanceStatus getAttendanceStatus() {
        return status;
    }
    public LocalTime getAttendanceTime(){
        return attendanceTime;
    }
    public LocalDate getDate() { return date; }
    public void require(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
