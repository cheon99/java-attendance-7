package attendance;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        Attendance attendance = new AttendanceResourceLoader().load() ;
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        new UiController(today, attendance, inputView, outputView).run();
    }
}
