public class MarkingException extends MochiException {
    private final String inputProvided;
    private final int listRange;

    public MarkingException(String inputProvided, int listRange) {
        super();
        this.inputProvided = inputProvided;
        this.listRange = listRange;
    }

    @Override
    public String toString() {
        if (listRange == 0) {
            return String.format(super.toString() + "\n" + """
                ------------------------- Details --------------------------
                 There are no items in the list to mark or unmark.
                ------------------------------------------------------------""");
        }
        return String.format(super.toString() + "\n" + """
            ------------------------- Details -------------------------
             Marking or unmarking tasks require a valid task number
             between the range of 1 and %d.
             Invalid input provided was: %s
             -----------------------------------------------------------""", this.listRange, this.inputProvided);
    }
}
