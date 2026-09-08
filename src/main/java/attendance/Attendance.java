package attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Attendance {
    Map<String, Student> studentBook = new HashMap<>();

    public void addStudent(Student student) {
        studentBook.put(student.name(), student);
    }
    public boolean hasStudent(String name) {
        return studentBook.containsKey(name);
    }
    public boolean hasAttendance(String name, LocalDate today) {
        Student student = studentBook.get(name) ;
        return student.hasAttendance(today);
    }
    public void addAttendance(String name, LocalDate date, LocalTime attendTime) {
        Student student = studentBook.get(name) ;
        student.addAttendance(date, attendTime);
    }
}
