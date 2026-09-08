
package attendance;

import java.time.LocalDate;
import java.util.*;

public class checkNeedWarnedStudent implements MenuAction {
    LocalDate today ;
    InputView inputView ;
    Attendance attendance;
    OutputView outputView;
    public checkNeedWarnedStudent(LocalDate today, InputView inputView, OutputView outputView, Attendance attendance) {
        this.today = today;
        this.inputView = inputView;
        this.attendance = attendance;
        this.outputView = outputView;
    }
    public void run() {
        Map<String, Student> studentBook = attendance.getStudentBook();
        List<Student> EXPULSION = new ArrayList<>();
        List<Student> MEETING = new ArrayList<>();
        List<Student> WARN = new ArrayList<>();
        List<Student>[] listArr = new List[3];
        listArr[0] = EXPULSION;
        listArr[1] = MEETING;
        listArr[2] = WARN;
        for (Student student : studentBook.values()) {
            updateDisciplinaryPolicyStatus(student, today);
            if (student.getDisciplinaryPolicyStatus() == DisciplinaryPolicy.EXPULSION){
                EXPULSION.add(student);
                continue;
            }
            if (student.getDisciplinaryPolicyStatus() == DisciplinaryPolicy.MEETING){
                MEETING.add(student);
                continue;
            }
            if (student.getDisciplinaryPolicyStatus() == DisciplinaryPolicy.WARN){
                WARN.add(student);
            }
        }
        for (List<Student> list : listArr) {
            list.sort(Comparator.comparingInt(Student::getPseudoAbsent).reversed().thenComparing(Student::name));
        }
        outputView.printPreStringForWarnedStudent();
        for  (List<Student> list : listArr) {
            outputView.printNeedWarnedStudent(list);
        }
    }
    public void updateDisciplinaryPolicyStatus(Student student, LocalDate today){
        today = LocalDate.of(2024,12,25);
        int Late = 0;
        int Absent = 0;
        for (int day = 1; day <= today.lengthOfMonth(); day++) {
            LocalDate date = today.withDayOfMonth(day);
            if (AttendanceDayType.from(date.getDayOfWeek()) == AttendanceDayType.WEEKEND) {
                continue;
            }
            if (date == today) {
                break;
            }
            Map<LocalDate, AttendanceRecord> attendanceLog = student.getAttendanceLog();
            AttendanceRecord record = attendanceLog.get(date);
            if  (record == null) {
                Absent++;
                continue;
            }
            AttendanceStatus status = record.getAttendanceStatus();
            if (status == AttendanceStatus.ABSENT) {
                Absent++;
            }
            if (status == AttendanceStatus.LATE) {
                Late++;
            }
        }
        student.setDisciplinaryPolicyStatus(Late, Absent);
    }
}
