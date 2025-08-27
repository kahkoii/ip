public class ToDo extends Task {
    public ToDo (String desc) {
        super(desc);
    }

    public ToDo (String desc, boolean status) {
        super(desc,status);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
