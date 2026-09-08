package attendance;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class  AttendanceResourceLoader {
    Attendance attendance;
    public Attendance load() {
        attendance = new Attendance();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("attendances.csv");
        if (stream == null) { throw new RuntimeException("File not found!"); }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        List<String> lines = reader.lines().skip(1).toList();
        for (String line : lines) {
            String[] columns = line.split(",", -1);
            if (!attendance.hasStudent(columns[0])){
                addStudent(attendance, new Student(columns[0]));
            }
            Student student = attendance.getStudent(columns[0]);
            String[] splitedDateTime = columns[1].split(" ",-1);
            student.addAttendance(parseDate(splitedDateTime[0]), parseTime(splitedDateTime[1]));

        }
        return attendance;
    }
    public void addStudent(Attendance attendance, Student student) {
        attendance.addStudent(student);
    }
    private LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }
    private LocalTime parseTime(String time) {
        return LocalTime.parse(time);
    }

}
