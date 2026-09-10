package attendance.contoroller;

import attendance.domain.Attendance;
import attendance.io.InputView;
import attendance.io.OutputView;
import attendance.application.ActionResult;

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
        boolean quit =  false;
        functionController = new FunctionController(today, attendance, inputView, outputView);
        try {
            while (!quit) {
                outputView.printMenu(today);
                ActionResult result = functionController.run(inputView.readSelection());
                quit = result == ActionResult.EXIT;
            }
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException(e.getMessage()) ;
        }
    }
}
