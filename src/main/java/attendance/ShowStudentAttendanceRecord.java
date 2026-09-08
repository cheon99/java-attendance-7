package attendance;

import java.time.LocalDate;
import java.util.Map;

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
    public void run() {
        String nickname = inputView.readNickname();
        if (!attendance.hasStudent(nickname)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
        Map<LocalDate, AttendanceRecord> attendanceLog = attendance.getAttendanceLog(nickname);
        outputView.printStudentAttendanceRecord(today, nickname, attendanceLog);
    }
}
