package attendance.domain;

import java.time.LocalDate;

public class Context {
    public static LocalDate date = LocalDate.of(2024,12,16);
    public static LocalDate getDate() {
        return date;
    }
}
