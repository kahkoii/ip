public abstract class Task {
    protected final String description;
    protected boolean completed;

    public Task(String desc) {
        this.description = desc;
        this.completed = false;
    }

    public Task(String desc, boolean status) {
        this.description = desc;
        this.completed = status;
    }

    public String mark() {
        this.completed = true;
        return String.format("""
                ____________________________________________________________
                 Nice! I've marked this task as done:
                   [X] %s
                ____________________________________________________________
                """, description);
    }

    public String unmark() {
        this.completed = false;
        return String.format("""
                ____________________________________________________________
                 OK, I've marked this task as not done yet:
                   [ ] %s
                ____________________________________________________________
                """, description);
    }

    // Returns the string representation of this task to be saved as persistent data
    public abstract String getSaveString();

    @Override
    public String toString() {
        char status = completed ? 'x' : ' ';
        return String.format("[%c] %s", status, description);
    }
}
