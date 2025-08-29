package mochi;

public class ToDo extends Task {
    public ToDo (String desc) {
        super(desc);
    }

    public ToDo (String desc, boolean status) {
        super(desc,status);
    }

    @Override
    public String getSaveString() {
        return String.format("T | %d | %s", this.completed ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
