package attendance.menu;

import attendance.application.ActionResult;

public class ExitProgram implements MenuAction {
    public ActionResult run() {
        return ActionResult.EXIT;
    }
}
