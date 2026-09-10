package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceRecord {
    private final LocalDate date;
    private final LocalTime attendanceTime;
    private final AttendanceStatus status;
    public AttendanceRecord(LocalDate date, LocalTime time) {
        this.date = date ;
        this.attendanceTime = time ;
        updateAttendanceStatus();
    }
    private void updateAttendanceStatus() {
        status = AttendanceStatus.from(date, attendanceTime);
    }
    public AttendanceStatus getAttendanceStatus() {
        return status;
    }
    public LocalTime getAttendanceTime(){
        return attendanceTime;
    }
    public LocalDate getDate() { return date; }
}
