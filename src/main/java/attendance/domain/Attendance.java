package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Attendance {
    Map<String, Student> studentBook = new HashMap<>();
    public Student getStudentByName(String studentName) {
        if (hasStudent(studentName)){ return studentBook.get(studentName); }
        throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
    }
    public void addStudent(Student student) {
        studentBook.put(student.name(), student);
    }
    public boolean hasStudent(String name) {
        return studentBook.containsKey(name);
    }
    public void addAttendance(String name, LocalDate date, LocalTime attendTime) {
        Student student = getStudentByName(name) ;
        student.addAttendance(date, attendTime);
    }
    public boolean hasAttendance(String name, LocalDate date) {
        Student student = getStudentByName(name) ;
        return student.hasAttendance(date) ;
    }ㅗ
    public Student getStudent(String name) {
        return getStudentByName(name);
    }

    public void modifyAttendance(String name, LocalDate attendanceDate, LocalTime attendTime) {
        Student student = getStudentByName(name) ;
        student.modifyAttendance(attendanceDate, attendTime);
    }
    public AttendanceStatus getAttendanceStatus(String name, LocalDate date) {
        Student student = getStudentByName(name) ;
        return student.getAttendanceStatus(date);
    }
    public LocalTime getAttendanceTime(String name, LocalDate date) {
        Student student = getStudentByName(name) ;
        return student.getAttendanceTime(date);
    }
    public List<List<AttendanceReport>> findDisciplinaryCandidates(LocalDate date) {
        List<AttendanceReport> EXPULSION = new ArrayList<>();
        List<AttendanceReport> MEETING = new ArrayList<>();
        List<AttendanceReport> WARN = new ArrayList<>();
        List<List<AttendanceReport>> listArr = List.of(EXPULSION, MEETING, WARN);
        for (Student student : studentBook.values()) {
            AttendanceReport result = student.reportUntil(date);
            if (result.getStatus() == DisciplinaryPolicy.EXPULSION){EXPULSION.add(result);}
            if (result.getStatus() == DisciplinaryPolicy.MEETING){MEETING.add(result);}
            if (result.getStatus() == DisciplinaryPolicy.WARN){WARN.add(result);}
        }
        return sortList(listArr);
    }
    private List<List<AttendanceReport>> sortList(List<List<AttendanceReport>> listArr) {
        for (List<AttendanceReport> list : listArr) {
            list.sort(Comparator.comparingInt((AttendanceReport result) -> DisciplinaryPolicy.unitConversion(result.getLate(), result.getAbsent())).reversed().thenComparing(AttendanceReport::getName));
        }
        return listArr;
    }
}
