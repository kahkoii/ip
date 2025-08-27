public class Task {
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

    public void mark() {
        this.completed = true;
        System.out.printf("""
                ____________________________________________________________
                 Nice! I've marked this task as done:
                   [X] %s
                ____________________________________________________________
                """, description);
    }

    public void unmark() {
        this.completed = false;
        System.out.printf("""
                ____________________________________________________________
                 OK, I've marked this task as not done yet:
                   [ ] %s
                ____________________________________________________________
                """, description);
    }

    @Override
    public String toString() {
        char status = completed ? 'x' : ' ';
        return String.format("[%c] %s", status, description);
    }
}
