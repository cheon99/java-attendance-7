package attendance.domain;

public enum DisciplinaryPolicy{
    FINE(""),
    WARN("경고"),
    MEETING("면담"),
    EXPULSION("제적");
    private final String value;
    DisciplinaryPolicy(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
    public static DisciplinaryPolicy from(int LATE, int ABSENT) {
        if (ABSENT + LATE/3 > 5){
            return EXPULSION;
        }
        if (ABSENT + LATE/3 >= 3){
            return MEETING;
        }
        if (ABSENT + LATE/3 >= 2){
            return WARN;
        }
        return FINE;
    }
}