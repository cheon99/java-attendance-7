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
        require(AttendanceDayType.from(date.getDayOfWeek()) == AttendanceDayType.WEEKEND, "[ERROR] %d월 %d일 %s은 등교일이 아닙니다.".formatted(date.getMonthValue(), date.getDayOfMonth(), dayStr));
        if (time != null) {
            require(!AttendancePolicy.isOperationTime(time), "[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
        }
        require(date == Context.getDate() || date.isAfter(Context.getDate()), "[ERROR] 잘못된 형식을 입력하였습니다.");
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
        if (condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
