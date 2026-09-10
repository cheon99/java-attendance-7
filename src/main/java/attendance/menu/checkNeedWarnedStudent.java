
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
        List<List<AttendanceReport>> listArr = attendance.findDisciplinaryCandidates(today) ;
        outputView.printPreStringForWarnedStudent();
        for  (List<AttendanceReport> list : listArr) {
            outputView.printNeedWarnedStudent(list);
        }
        return ActionResult.CONTINUE;
    }
}
