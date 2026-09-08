package attendance;

import java.time.LocalDate;

public class UiController {
    InputView inputView ;
    OutputView outputView ;
    LocalDate today ;
    Attendance attendance;
    FunctionController functionController;
    private boolean quit = false;
    public UiController(LocalDate today,  Attendance attendance, InputView inputView , OutputView outputView) {
        this.today = today;
        this.attendance = attendance;
        this.inputView = inputView;
        this.outputView = outputView;
    }
    public void run() {
        functionController = new FunctionController(today, attendance, inputView, outputView, this);
        try {
            while (!quit) {
                outputView.printMenu(today);
                functionController.run(inputView.readSelection());
            }
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public void quit() {
        quit = true;
    }
}
