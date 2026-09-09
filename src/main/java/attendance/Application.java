package attendance;

import attendance.resource.AttendanceResourceLoader;
import attendance.contoroller.UiController;
import attendance.domain.Attendance;
import attendance.io.InputView;
import attendance.io.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        LocalDate today = LocalDate.parse(DateTimes.now().toString().substring(0,10));
        Attendance attendance = new AttendanceResourceLoader().load() ;
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        new UiController(today, attendance, inputView, outputView).run();
    }
}
