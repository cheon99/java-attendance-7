package attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private DisciplinaryPolicy status;
    private int late;
    private int absent;
    private int pseudoAbsent;

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
        AttendanceRecord record = this.attendanceLog.get(date);
        record.modifyAttendance(date,  time);
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
    public void setDisciplinaryPolicyStatus(int Late, int Absent) {
        this.late = Late;
        this.absent = Absent;
        this.status =  DisciplinaryPolicy.from(Late, Absent) ;
        this.pseudoAbsent = late/3 + absent;
    }
    public DisciplinaryPolicy getDisciplinaryPolicyStatus() {
        return status;
    }
    public int getLate() {
        return late;
    }
    public int getAbsent() {
        return absent;
    }
    public int getPseudoAbsent() {
        return pseudoAbsent;
    }
    public String getName() {
        return name;
    }
}

