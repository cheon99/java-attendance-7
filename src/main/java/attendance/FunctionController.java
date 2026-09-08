package attendance;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FunctionController {
    InputView inputView ;
    OutputView outputView ;
    LocalDate today ;
    Attendance attendance;
    Map<Menu, MenuAction> actions = new HashMap<>();

    public FunctionController(LocalDate today, Attendance attendance, InputView inputView , OutputView outputView) {
        this.today = today;
        this.attendance = attendance;
        this.inputView = inputView;
        this.outputView = outputView;
        this.attendance = attendance;

        MenuAction check = new CheckAttendance(today, inputView, outputView, attendance);
        MenuAction modify = new ModifyAttendance();
        MenuAction record = new ShowAttendance();
        MenuAction warning = new checkNeedWarnedStudent();
        MenuAction exit = new ExitProgram();

        actions.put(Menu.CHECK, check);
        actions.put(Menu.MODIFY, modify);
        actions.put(Menu.RECORD, record);
        actions.put(Menu.WARNING, warning);
        actions.put(Menu.EXIT, exit);
    }
    public void run(String selection) {
        MenuAction action = actions.get(Menu.from(selection));
        action.run();
    }
}
