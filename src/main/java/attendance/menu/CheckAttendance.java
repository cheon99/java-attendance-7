package attendance.menu;

import attendance.application.ActionResult;
import attendance.domain.Attendance;
import attendance.domain.AttendanceDayType;
import attendance.io.InputView;
import attendance.io.OutputView;
import attendance.domain.AttendancePolicy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

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
        LocalTime attendTime;
        try {
            attendTime = LocalTime.parse(inputView.readAttendTime());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
        if (attendance.hasAttendance(nickname, today)){
            throw new IllegalArgumentException("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
        }
        attendance.addAttendance(nickname, today, attendTime);
        outputView.printCheckAttendanceResult(today, attendTime, attendance.getAttendanceStatus(nickname, today));

        return ActionResult.CONTINUE;
    }
}
