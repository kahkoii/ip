public class Deadline extends Task {
    private final String due;

    public Deadline(String desc, String dueDate) {
        super(desc);
        this.due = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.due);
    }
}
