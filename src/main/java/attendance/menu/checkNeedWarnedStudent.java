
package attendance.menu;

import attendance.domain.*;
import attendance.io.OutputView;

import java.time.LocalDate;
import java.util.*;

public class checkNeedWarnedStudent implements MenuAction {
    LocalDate today ;
    Attendance attendance;
    OutputView outputView;
    public checkNeedWarnedStudent(LocalDate today, OutputView outputView, Attendance attendance) {
        this.today = today;
        this.attendance = attendance;
        this.outputView = outputView;
    }
    public ActionResult run() {
        List<List<AttendanceReport>>  listArr = getListArr() ;
        for (List<AttendanceReport> list : listArr) {
            list.sort(Comparator.comparingInt((AttendanceReport result) -> result.getLate() / 3 + result.getAbsent()).reversed().thenComparing(AttendanceReport::getName));
        }
        outputView.printPreStringForWarnedStudent();
        for  (List<AttendanceReport> list : listArr) {
            outputView.printNeedWarnedStudent(list);
        }
        return ActionResult.CONTINUE;
    }
    public List<List<AttendanceReport>> getListArr() {
        Map<String, Student> studentBook = attendance.getStudentBook();
        List<AttendanceReport> EXPULSION = new ArrayList<>();
        List<AttendanceReport> MEETING = new ArrayList<>();
        List<AttendanceReport> WARN = new ArrayList<>();
        List<List<AttendanceReport>> listArr = List.of(EXPULSION, MEETING, WARN);
        for (Student student : studentBook.values()) {
            AttendanceReport result = student.checkPenaltyStatus(today);
            if (result.getStatus() == DisciplinaryPolicy.EXPULSION){EXPULSION.add(result);}
            if (result.getStatus() == DisciplinaryPolicy.MEETING){MEETING.add(result);}
            if (result.getStatus() == DisciplinaryPolicy.WARN){WARN.add(result);}
        }
        return listArr;
    }
}
