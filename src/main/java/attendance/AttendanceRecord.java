package attendance;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceRecord {
    LocalDate localDate;
    LocalTime localTime;
    boolean isLate;
    boolean isNotAttended;
    public AttendanceRecord(String dateTime) {
        String[] splitedDateTime = dateTime.split(" ", -1);
        this.localDate = LocalDate.parse(splitedDateTime[0]);
        this.localTime = LocalTime.parse(splitedDateTime[1]);
    }
    public boolean isLated() {return isLate;}
    public boolean isNotAttended() {return isNotAttended;}
}
