package attendance.menu;

import attendance.domain.ActionResult;

public class ExitProgram implements MenuAction {
    public ActionResult run() {
        return ActionResult.EXIT;
    }
}
