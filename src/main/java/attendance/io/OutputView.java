package attendance.io;

import attendance.domain.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.time.format.TextStyle;
import java.util.Map;

public class OutputView {
    public void printMenu(LocalDate today) {
        String dayStr = today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        System.out.printf(
                "오늘은 %d월 %02d일 %s입니다. 기능을 선택해 주세요.%n",
                today.getMonthValue(),
                today.getDayOfMonth(),
                dayStr
        );
        System.out.println("1. 출석 확인");
        System.out.println("2. 출석 수정");
        System.out.println("3. 크루별 출석 기록 확인");
        System.out.println("4. 제적 위험자 확인");
        System.out.println("Q. 종료");
    }
    public void printCheckAttendanceResult(LocalDate today, LocalTime attendTime, AttendanceStatus status ) {
        String dayStr = today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        System.out.printf("%d월 %02d일 %s %s (%s) %n", today.getMonthValue(), today.getDayOfMonth(), dayStr, attendTime.toString(), status.getValue());
    }
    public void printModifyAttendanceResult(int month, int day, String dayStr, String time, String status, String time2, String status2){
        System.out.print("%d월 %02d일 %s %s (%s) -> ".formatted(month, day, dayStr, time, status));
        System.out.println("%s (%s) 수정 완료!".formatted(time2, status2));
    }
    public void printStudentAttendanceRecordLog(AttendanceReport result) {
        System.out.printf("이번 달 %s의 출석 기록입니다.%n", result.getName());
        for (AttendanceRecord record : result.getAttendanceRecordExtended()) {
            printAttendanceRecord(record);
        }
    }
    private void printAttendanceRecord(AttendanceRecord record) {
        LocalDate date = record.getDate();
        String dayStr = date.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.KOREAN);
        String timeStr = formatTime(record.getAttendanceTime());

        System.out.printf("%d월 %02d일 %s %s (%s)%n",
                date.getMonthValue(),
                date.getDayOfMonth(),
                dayStr,
                timeStr,
                record.getAttendanceStatus().getValue());
    }
    private String formatTime(LocalTime time) {
        if (time == null) {
            return "--:--";
        }
        return time.toString();
    }
    public void printStudentAttendanceRecordSummary(AttendanceReport result){
        int present = result.getPresent();
        int late = result.getLate();
        int absent = result.getAbsent();
        System.out.printf("출석: %d회%n", present);
        System.out.printf("지각: %d회%n", late);
        System.out.printf("결석: %d회%n", absent);
        DisciplinaryPolicy status = result.getStatus();
        if (status != DisciplinaryPolicy.FINE) {
            System.out.printf("%s 대상자입니다.%n", status.getValue());
        }
    }
    public void printPreStringForWarnedStudent() {
        System.out.println("제적 위험자 조회 결과");
    }
    public void printNeedWarnedStudent(List<AttendanceReport> list) {
        for  (AttendanceReport result : list) {
            System.out.printf("- %s: 결석 %d회, 지각 %d회 (%s)%n",result.getName(),result.getAbsent(),result.getLate(),result.getStatus().getValue());
        }
    }
}
