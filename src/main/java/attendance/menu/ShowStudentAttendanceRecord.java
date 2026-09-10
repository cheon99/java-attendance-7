package attendance.menu;

import attendance.domain.ActionResult;
import attendance.domain.Attendance;
import attendance.domain.AttendanceReport;
import attendance.io.InputView;
import attendance.io.OutputView;

import java.time.LocalDate;

public class ShowStudentAttendanceRecord implements MenuAction {
    InputView inputView;
    OutputView outputView;
    Attendance attendance;
    LocalDate today;
    public ShowStudentAttendanceRecord(LocalDate today, InputView inputView, OutputView outputView, Attendance attendance) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.attendance = attendance;
        this.today = today;
    }
    public ActionResult run() {
        String nickname = inputView.readNickname();
        if (!attendance.hasStudent(nickname)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
        AttendanceReport result = attendance.getStudent(nickname).checkPenaltyStatus(today);
        outputView.printStudentAttendanceRecordLog(result) ;
        outputView.printStudentAttendanceRecordSummary(result) ;
        return  ActionResult.CONTINUE;
    }
}
