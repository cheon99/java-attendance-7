package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceRecord {
    LocalDate date;
    LocalTime AttendanceTime;
    AttendanceStatus status;
    public AttendanceRecord(LocalDate date, LocalTime time) {
        this.date = date ;
        this.AttendanceTime = time ;
        updateAttendanceStatus();
    }
    public void modifyAttendance(LocalDate date, LocalTime time) {
        this.date = date;
        this.AttendanceTime = time;
        updateAttendanceStatus();
    }
    private void updateAttendanceStatus() {
        status = AttendanceStatus.from(date, AttendanceTime);
    }
    public AttendanceStatus getAttendanceStatus() {
        return status;
    }
    public LocalTime getAttendanceTime(){
        return AttendanceTime;
    }
    public LocalDate getDate() { return date; }
}
