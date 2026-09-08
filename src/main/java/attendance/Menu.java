package attendance;

public enum Menu {
    CHECK("1"),
    MODIFY("2"),
    RECORD("3"),
    WARNING("4"),
    EXIT("Q");

    private final String command;
    Menu(String command) {
        this.command = command;
    }
    public static Menu from(String input) {
        for (Menu action : values()) {
            if (action.command.equals(input)) {
                return action;
            }
        }
        throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
    }
}
