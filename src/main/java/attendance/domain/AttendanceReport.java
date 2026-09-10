package attendance.domain;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AttendanceReport {
    private String name;
    private DisciplinaryPolicy status;
    private int present;
    private int late;
    private int absent;
    private LocalDate date;
    private List<AttendanceRecord> attendanceRecordExtended ;
    public AttendanceReport(String name, List<AttendanceRecord> attendanceRecordExtended, LocalDate date) {
        this.name = name;
        this.date = date;
        this.attendanceRecordExtended = List.copyOf(attendanceRecordExtended);
        updatePenaltyResult();
    }
    private void updatePenaltyResult() {
        for (AttendanceRecord record : attendanceRecordExtended) {
            if (record.getAttendanceStatus() == AttendanceStatus.ABSENT){ absent++;}
            if (record.getAttendanceStatus() == AttendanceStatus.PRESENT){present++;}
            if (record.getAttendanceStatus() == AttendanceStatus.LATE){late++;}
        }
        status = DisciplinaryPolicy.from(late, absent);
    }
    public DisciplinaryPolicy getStatus() { return status; }
    public int getPresent() { return present; }
    public int getLate() { return late; }
    public int getAbsent() { return absent; }
    public String getName() { return name; }
    public LocalDate getDate() { return date; }
    public List<AttendanceRecord> getAttendanceRecordExtended() { return attendanceRecordExtended; }
}
