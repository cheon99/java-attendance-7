package attendance.menu;

import attendance.application.ActionResult;
import attendance.domain.Attendance;
import attendance.io.InputView;
import attendance.io.OutputView;
import attendance.io.InputParser;
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
        String nickname = inputView.readNickname();
        LocalTime attendTime = InputParser.timeParse(inputView.readAttendTime());
        if (attendance.hasAttendance(nickname, today)){
            throw new IllegalArgumentException("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
        }
        attendance.addAttendance(nickname, today, attendTime);
        outputView.printCheckAttendanceResult(today, attendTime, attendance.getAttendanceStatus(nickname, today));

        return ActionResult.CONTINUE;
    }
}
