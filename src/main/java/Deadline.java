public class Deadline extends Task {
    private final String due;

    public Deadline(String desc, String dueDate) {
        super(desc);
        this.due = dueDate;
    }

    public Deadline(String desc, String dueDate, boolean status) {
        super(desc, status);
        this.due = dueDate;
    }

    @Override
    public String getSaveString() {
        return String.format("D | %d | %s | %s", this.completed ? 1 : 0, this.description, this.due);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.due);
    }
}
