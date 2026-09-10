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
    public PenaltyResult(String name, Map<LocalDate, AttendanceRecord> attendanceRecordExtended, LocalDate date) {
        this.name = name;
        this.date = date;
        this.attendanceRecordExtended = Map.copyOf(attendanceRecordExtended);
        updatePenaltyResult();
    }
    private void updatePenaltyResult() {
        for (Map.Entry<LocalDate, AttendanceRecord> entry : attendanceRecordExtended.entrySet()) {
            LocalDate key = entry.getKey();
            AttendanceRecord value = entry.getValue();
            if (value.getAttendanceStatus() == AttendanceStatus.ABSENT){
                absent++;
                continue;
            }
            if (value.getAttendanceStatus() == AttendanceStatus.PRESENT){
                present++;
                continue;
            }
            if (value.getAttendanceStatus() == AttendanceStatus.LATE){
                late++;
            }
        }
        status = DisciplinaryPolicy.from(late, absent);
    }
    public DisciplinaryPolicy getStatus() { return status; }
    public int getPresent() { return present; }
    public int getLate() { return late; }
    public int getAbsent() { return absent; }
    public String getName() { return name; }
    public LocalDate getDate() { return date; }
    public Map<LocalDate, AttendanceRecord> getAttendanceRecordExtended() { return attendanceRecordExtended; }
}
