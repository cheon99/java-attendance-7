package attendance.domain;

import java.time.LocalDate;

public class Context {
    private static LocalDate date = LocalDate.of(2024,12,14) ;
    public static LocalDate getDate() {
        return date;
    }
}
