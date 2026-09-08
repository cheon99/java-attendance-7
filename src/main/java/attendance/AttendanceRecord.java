package attendance;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceRecord {
    LocalDate localDate;
    LocalTime localTime;
    boolean isLate;
    boolean isNotAttended;
    public AttendanceRecord(LocalDate date, LocalTime time) {
        this.localDate = date ;
        this.localTime = time ;
    }
    public boolean isLated() {return isLate;}
    public boolean isNotAttended() {return isNotAttended;}
}
