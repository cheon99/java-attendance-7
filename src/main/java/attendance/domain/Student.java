package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String name;

    Map<LocalDate, AttendanceRecord> attendanceLog =  new HashMap<>();
    public Student(String name) {
        this.name = name;
    }
    public void addAttendance(LocalDate date, LocalTime time) {
        this.attendanceLog.put(date, new AttendanceRecord(date, time));
    }
    public String name() {
        return name;
    }
    public boolean hasAttendance(LocalDate date) {
        return this.attendanceLog.containsKey(date) ;
    }
    public void modifyAttendance(LocalDate date, LocalTime time) {
        attendanceLog.replace(date, new AttendanceRecord(date, time));
    }
    public AttendanceStatus getAttendanceStatus(LocalDate date) {
        AttendanceRecord record = this.attendanceLog.get(date);
        return record.getAttendanceStatus();
    }
    public LocalTime getAttendanceTime(LocalDate date) {
        AttendanceRecord record = this.attendanceLog.get(date);
        return record.getAttendanceTime() ;
    }
    public Map<LocalDate, AttendanceRecord> getAttendanceLog() {
        return attendanceLog;
    }

    public AttendanceReport checkPenaltyStatus(LocalDate today){
        today = Adjustment.date;
        List<AttendanceRecord> attendanceRecordExtended = new ArrayList<>();
        for (int day = 1; day <= today.lengthOfMonth(); day++) {
            LocalDate date = today.withDayOfMonth(day);
            if (AttendanceDayType.from(date.getDayOfWeek()) == AttendanceDayType.WEEKEND) {continue;}
            if (date == today || date.isAfter(today)) {break;}
            AttendanceRecord record = attendanceLog.get(date);
            if  (record == null) {
                attendanceRecordExtended.add(new AttendanceRecord(date, null));
                continue;
            }
            attendanceRecordExtended.add(new AttendanceRecord(date, record.getAttendanceTime()));
        }
        return new AttendanceReport(name,attendanceRecordExtended, today);
    }
}

