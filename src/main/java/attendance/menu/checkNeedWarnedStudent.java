
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
        List<List<PenaltyResult>>  listArr = getListArr() ;
        for (List<PenaltyResult> list : listArr) {
            list.sort(Comparator.comparingInt((PenaltyResult result) -> result.getLate() / 3 + result.getAbsent()).reversed().thenComparing(PenaltyResult::getName));
        }
        outputView.printPreStringForWarnedStudent();
        for  (List<PenaltyResult> list : listArr) {
            outputView.printNeedWarnedStudent(list);
        }
        return ActionResult.CONTINUE;
    }
    public List<List<PenaltyResult>> getListArr() {
        Map<String, Student> studentBook = attendance.getStudentBook();
        List<PenaltyResult> EXPULSION = new ArrayList<>();
        List<PenaltyResult> MEETING = new ArrayList<>();
        List<PenaltyResult> WARN = new ArrayList<>();
        List<List<PenaltyResult>> listArr = List.of(EXPULSION, MEETING, WARN);
        for (Student student : studentBook.values()) {
            PenaltyResult result = student.checkPenaltyStatus(today);
            if (result.getStatus() == DisciplinaryPolicy.EXPULSION){EXPULSION.add(result);}
            if (result.getStatus() == DisciplinaryPolicy.MEETING){MEETING.add(result);}
            if (result.getStatus() == DisciplinaryPolicy.WARN){WARN.add(result);}
        }
        return listArr;
    }
}
