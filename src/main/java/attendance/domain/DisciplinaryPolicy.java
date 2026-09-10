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
        int num = unitConversion(LATE, ABSENT);
        if (num > 5){
            return EXPULSION;
        }
        if (num >= 3){
            return MEETING;
        }
        if (num >= 2){
            return WARN;
        }
        return FINE;
    }
    public static int unitConversion(int LATE, int ABSENT){
        return ABSENT + LATE/3;
    }
}