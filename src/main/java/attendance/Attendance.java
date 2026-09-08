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
    public void addAttendance(String name, LocalDate date, LocalTime attendTime) {
        Student student = studentBook.get(name) ;
        student.addAttendance(date, attendTime);
    }
    public boolean hasAttendance(String name, LocalDate date) {
        Student student = studentBook.get(name) ;
        return student.hasAttendance(date) ;
    }
    public Student getStudent(String name) {
        return studentBook.get(name);
    }

    public void modifyAttendance(String name, LocalDate attendanceDate, LocalTime attendTime) {
        Student student = studentBook.get(name) ;
        student.modifyAttendance(attendanceDate, attendTime);
    }
    public AttendanceStatus getAttendanceStatus(String name, LocalDate date) {
        Student student = studentBook.get(name) ;
        return student.getAttendanceStatus(date);
    }
    public LocalTime getAttendanceTime(String name, LocalDate date) {
        Student student = studentBook.get(name) ;
        return student.getAttendanceTime(date);
    }
    public  Map<LocalDate, AttendanceRecord> getAttendanceLog(String name) {
        Student student = studentBook.get(name) ;
        return student.getAttendanceLog();
    }
    public Map<String, Student> getStudentBook() {
        return studentBook;
    }
}
