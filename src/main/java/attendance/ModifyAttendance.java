package attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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
    public void run() {
        String nickname = inputView.readNickname();
        if (!attendance.hasStudent(nickname)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
        LocalDate attendanceDate;
        try {
            attendanceDate = today.withDayOfMonth(inputView.readDay());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
        String dayStr = attendanceDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        if (AttendanceDayType.from(attendanceDate.getDayOfWeek()) == AttendanceDayType.WEEKEND) {
            throw new IllegalArgumentException("[ERROR] %d월 %d일 %s은 등교일이 아닙니다.".formatted(attendanceDate.getMonthValue(), attendanceDate.getDayOfMonth(), dayStr));
        }
        if (attendanceDate.isAfter(today)) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
        LocalTime attendTime;
        try {
            attendTime = LocalTime.parse(inputView.readModifiedAttendTime());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
        if (!AttendancePolicy.isOperationTime(attendTime)) {
            throw new IllegalArgumentException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
        }
        if (!attendance.hasAttendance(nickname, attendanceDate)){
            throw new IllegalArgumentException("[ERROR] 해당 날짜에 수정할 기록이 없습니다");
        }
        String preString = "%d월 %d일 %s %s (%s) -> ".formatted(attendanceDate.getMonthValue(), attendanceDate.getDayOfMonth(), dayStr, attendance.getAttendanceTime(nickname, attendanceDate).toString(), attendance.getAttendanceStatus(nickname, attendanceDate).getValue() );
        attendance.modifyAttendance(nickname, attendanceDate, attendTime);
        String finalString = preString + "%s (%s) 수정 완료!".formatted(attendTime.toString(), attendance.getAttendanceStatus(nickname, attendanceDate).getValue());
        outputView.printModifyAttendanceResult(finalString);
    }
}
