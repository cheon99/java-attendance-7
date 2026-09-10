package attendance.menu;

import attendance.application.ActionResult;
import attendance.domain.Attendance;
import attendance.domain.AttendanceRecord;
import attendance.io.InputView;
import attendance.io.OutputView;
import attendance.io.Parser;
import java.time.LocalDate;
import java.time.LocalTime;


public class CheckAttendance implements MenuAction {
    LocalDate today ;
    InputView inputView ;
    Attendance attendance;
    OutputView outputView;
    public CheckAttendance(LocalDate today, InputView inputView, OutputView outputView, Attendance attendance) {
        this.today = today;
        this.inputView = inputView;
        this.attendance = attendance;
        this.outputView = outputView;
    }
    public ActionResult run() {
        new AttendanceRecord(today, LocalTime.of(10,00));
        String nickname = inputView.readNickname();
        attendance.getStudent(nickname);
        LocalTime attendTime = Parser.timeParse(inputView.readAttendTime());
        attendance.addAttendance(nickname, today, attendTime);
        outputView.printCheckAttendanceResult(today, attendTime, attendance.getAttendanceStatus(nickname, today));
        return ActionResult.CONTINUE;
    }
}
