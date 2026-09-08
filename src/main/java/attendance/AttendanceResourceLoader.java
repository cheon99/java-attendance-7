package attendance;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class  AttendanceResourceLoader {
    Attendance attendance;
    public Attendance load() {
        attendance = new Attendance();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("attendance.csv");
        if (stream == null) { throw new RuntimeException("File not found!"); }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        List<String> lines = reader.lines().skip(1).toList();
        for (String line : lines) {
            String[] columns = line.split(",", -1);
            Student student = new Student(columns[0]);
            student.addAttendanceLog(columns[1]);
            addStudent(attendance, student);
        }
        return attendance;
    }
    public void addStudent(Attendance attendance, Student student) {
        attendance.addStudent(student);
    }
}
