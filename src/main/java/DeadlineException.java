public class DeadlineException extends MochiException {
    public DeadlineException() {
        super();
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "\n" + """
            ------------------------- Details -------------------------
             Invalid deadline command used.
             Format: `deadline <description> /by <due date>`
             Example: deadline watch lecture videos /by tonight
             -----------------------------------------------------------""");
    }
}
