package mochi;

public class ToDoException extends MochiException {
    public ToDoException() {
        super();
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "\n" + """
            ------------------------- Details -------------------------
             The description of a todo task cannot be empty.
            -----------------------------------------------------------""");
    }
}
