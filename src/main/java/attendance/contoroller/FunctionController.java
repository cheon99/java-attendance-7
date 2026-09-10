package attendance.contoroller;

import attendance.application.ActionResult;
import attendance.menu.*;
import attendance.domain.Attendance;
import attendance.application.Menu;
import attendance.io.InputView;
import attendance.io.OutputView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FunctionController {
    Map<Menu, MenuAction> actions = new HashMap<>();
    public FunctionController(LocalDate today, Attendance attendance, InputView inputView , OutputView outputView) {
        actions.put(Menu.CHECK, new CheckAttendance(today, inputView, outputView, attendance));
        actions.put(Menu.MODIFY, new ModifyAttendance(today, inputView, outputView, attendance));
        actions.put(Menu.RECORD, new ShowStudentAttendanceRecord(today, inputView, outputView, attendance));
        actions.put(Menu.WARNING, new checkNeedWarnedStudent(today, outputView, attendance));
        actions.put(Menu.EXIT, new ExitProgram());
    }
    public ActionResult run(String selection) {
        MenuAction action = actions.get(Menu.from(selection));
        return action.run();
    }
}
