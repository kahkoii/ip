public class EventException extends MochiException {
    public EventException() {
        super();
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "\n" + """
            ------------------------- Details -------------------------
             Invalid event command used.
             Format: `event <description> /from <start> /to <end>`
             Example: event gym /from Wednesday 5pm /to 6pm
             -----------------------------------------------------------""");
    }
}
