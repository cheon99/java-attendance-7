package attendance;

import java.util.HashMap;
import java.util.Map;

public class Attendance {
    Map<String, Student> studentBook = new HashMap<>();

    public void addStudent(Student student) {
        studentBook.put(student.name(), student);
    }
}
