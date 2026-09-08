package attendance;

import java.time.LocalDate;

public class UiController {
    InputView inputView ;
    OutputView outputView ;
    LocalDate today ;
    Attendance attendance;
    FunctionController functionController;
    public UiController(LocalDate today,  Attendance attendance, InputView inputView , OutputView outputView) {
        this.today = today;
        this.attendance = attendance;
        this.inputView = inputView;
        this.outputView = outputView;
    }
    public void run() {
        functionController = new FunctionController(today, attendance, inputView, outputView);
        boolean quit = false;
        try {
            while (!quit) {
                outputView.printMenu(today);
                functionController.run(inputView.readSelection());
                break;
            }
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
