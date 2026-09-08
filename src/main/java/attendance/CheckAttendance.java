package attendance;

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
    public void run() {
        String dayStr = today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        if (AttendanceDayType.from(today.getDayOfWeek()) == AttendanceDayType.WEEKEND) {
            throw new IllegalArgumentException("[ERROR] %d월 %d일 %s은 등교일이 아닙니다.".formatted(today.getMonthValue(), today.getDayOfMonth(), dayStr));
        }
        String nickname = inputView.readNickname();
        if (!attendance.hasStudent(nickname)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
        LocalTime attendTime;
        try {
            attendTime = LocalTime.parse(inputView.readAttendTime());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
        if (!AttendancePolicy.isOperationTime(attendTime)) {
            throw new IllegalArgumentException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
        }
        if (attendance.hasAttendance(nickname, today)){
            throw new IllegalArgumentException("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
        }
        attendance.addAttendance(nickname, today, attendTime);
        outputView.printCheckAttendanceResult(today, attendTime, attendance.getAttendanceStatus(nickname, today));
    }
}
