package attendance.menu;

import attendance.application.ActionResult;
import attendance.domain.Attendance;
import attendance.io.Parser;
import attendance.io.InputView;
import attendance.io.OutputView;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class ModifyAttendance implements MenuAction {
    LocalDate today ;
    InputView inputView ;
    Attendance attendance;
    OutputView outputView;
    public ModifyAttendance(LocalDate today, InputView inputView, OutputView outputView, Attendance attendance) {
        this.today = today;
        this.inputView = inputView;
        this.attendance = attendance;
        this.outputView = outputView;
    }
    public ActionResult run() {
        String nickname = inputView.readNickname();
        LocalDate attendanceDate = Parser.dateParse(today, Parser.intParse(inputView.readDay()));
        String dayStr = attendanceDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        LocalTime attendTime = Parser.timeParse(inputView.readModifiedAttendTime());
        outputView.printPreStringForModifyAttendanceResult(attendanceDate.getMonthValue(), attendanceDate.getDayOfMonth(), dayStr, attendance.getAttendanceTime(nickname, attendanceDate).toString(), attendance.getAttendanceStatus(nickname, attendanceDate).getValue() );
        attendance.modifyAttendance(nickname, attendanceDate, attendTime);
        outputView.printModifyAttendanceResult(attendTime.toString(), attendance.getAttendanceStatus(nickname, attendanceDate).getValue());
        return ActionResult.CONTINUE;
    }
}
