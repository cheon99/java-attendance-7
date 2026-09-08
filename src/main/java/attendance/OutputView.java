package attendance;

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
                "오늘은 %d월 %d일 %s입니다. 기능을 선택해 주세요.%n",
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
        System.out.printf("%d월 %d일 %s %s (%s) %n", today.getMonthValue(), today.getDayOfMonth(), dayStr, attendTime.toString(), status.getValue());
    }
    public void printModifyAttendanceResult(String string){
        System.out.println(string);
    }
    public void printStudentAttendanceRecord(LocalDate today, String name, Map<LocalDate, AttendanceRecord> attendanceLog) {
        System.out.printf("이번 달 %s의 출석 기록입니다.%n", name);
        today = LocalDate.of(2024,12,25);
        int Absent = 0 ;
        int Late = 0 ;
        int Present = 0 ;
        for (int day = 1; day <= today.lengthOfMonth(); day++) {
            LocalDate date = today.withDayOfMonth(day);
            if (AttendanceDayType.from(date.getDayOfWeek()) == AttendanceDayType.WEEKEND) {
                continue;
            }
            if (date == today) {
                break;
            }
            AttendanceRecord record = attendanceLog.get(date);
            String dayStr = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
            if  (record == null) {
                System.out.printf("%d월 %d일 %s --:-- (결석)%n", date.getMonthValue(), date.getDayOfMonth(), dayStr);
                Absent++;
                continue;
            }
            LocalTime attendanceTIme = record.getAttendanceTime();
            AttendanceStatus status = record.getAttendanceStatus();
            System.out.printf("%d월 %d일 %s %s (%s)%n", date.getMonthValue(), date.getDayOfMonth(), dayStr, attendanceTIme.toString(), status.getValue());
            if (status == AttendanceStatus.ABSENT) {
                Absent++;
            }
            if (status == AttendanceStatus.LATE) {
                Late++;

            }
            if (status == AttendanceStatus.PRESENT){
                Present++;
            }
        }
        System.out.printf("출석: %d회%n", Present);
        System.out.printf("지각: %d회%n", Late);
        System.out.printf("결석: %d회%n", Absent);
        DisciplinaryPolicy status = DisciplinaryPolicy.from(Late, Absent);
        if (status != DisciplinaryPolicy.FINE) {
            System.out.printf("%s 대상자입니다.", status.getValue());
        }
    }
    public void printPreStringForWarnedStudent() {
        System.out.println("제적 위험자 조회 결과");
    }
    public void printNeedWarnedStudent(List<Student> list) {
        for  (Student student : list) {
            System.out.printf("- %s: 결석 %d회, 지각 %d회 (%s)%n",student.getName(),student.getAbsent(),student.getLate(),student.getDisciplinaryPolicyStatus().getValue());
        }
    }
}
