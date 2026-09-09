package attendance.domain;

import java.time.LocalDate;
import java.util.Map;

public class PenaltyResult {
    private String name;
    private DisciplinaryPolicy status;
    private int present;
    private int late;
    private int absent;
    private LocalDate date;
    private Map<LocalDate, AttendanceRecord> attendanceRecordExtended ;
    public PenaltyResult(String name, DisciplinaryPolicy status, Map<LocalDate, AttendanceRecord> attendanceRecordExtended, int present, int late, int absent, LocalDate date) {
        this.name = name;
        this.status = status;
        this.present = present;
        this.late = late;
        this.absent = absent;
        this.date = date;
        this.attendanceRecordExtended = attendanceRecordExtended;
    }
    public DisciplinaryPolicy getStatus() { return status; }
    public int getPresent() { return present; }
    public int getLate() { return late; }
    public int getAbsent() { return absent; }
    public String getName() { return name; }
    public LocalDate getDate() { return date; }
    public Map<LocalDate, AttendanceRecord> getAttendanceRecordExtended() { return attendanceRecordExtended; }
}
