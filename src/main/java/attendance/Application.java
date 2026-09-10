package attendance;

import attendance.domain.Context;
import attendance.resource.AttendanceResourceLoader;
import attendance.contoroller.UiController;
import attendance.domain.Attendance;
import attendance.io.InputView;
import attendance.io.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        LocalDate today = Context.getDate();
        Attendance attendance = new AttendanceResourceLoader().load() ;
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        new UiController(today, attendance, inputView, outputView).run();
    }
}
