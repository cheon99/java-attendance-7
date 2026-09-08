package attendance;

public class ExitProgram implements MenuAction {
    UiController uiController;
    public ExitProgram(UiController uiController) {
        this.uiController = uiController;
    }
    public void run() {
        uiController.quit();
    }
}
