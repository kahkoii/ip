public class EventException extends MochiException {
    private String message = null;

    public EventException() {
        super();
    }

    public EventException(String s) {
        this();
        this.message = s;
    }

    @Override
    public String toString() {
        if (this.message == null) {
            return String.format(super.toString() + "\n" + """
                    ------------------------- Details -------------------------
                     Invalid event command used.
                     Format: `event <description> /from <date> <time (optional)> /to <date> <time (optional)>`
                              date format: YYYY-MM-DD | time format: HHmm
                     Example: event gym /from Wednesday 5pm /to 6pm
                     -----------------------------------------------------------""");
        }
        return String.format(super.toString() + "\n" + """
                ------------------------- Details -------------------------
                 Invalid event command used.
                 %s
                 -----------------------------------------------------------""",  this.message);
    }
}
