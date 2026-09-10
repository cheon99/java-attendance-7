package attendance.io;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class InputParser {
    public static LocalTime timeParse(String time) {
        LocalTime parsedTime;
        try {
            parsedTime = LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
        return parsedTime;
    }
    public static LocalDate intParse(String time) {
        try {
            return Integer.parseInt(time) ;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }
}
