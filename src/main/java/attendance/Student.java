package attendance;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    Map<String, AttendanceRecord> attendanceLog =  new HashMap<>();
    public Student(String name) {
        this.name = name;
    }
    public void addAttendanceLog(String dateTime) {
        this.attendanceLog.put(dateTime.split(" ", -1)[0], new AttendanceRecord(dateTime));
    }
    public String name() {
        return name;
    }
}
