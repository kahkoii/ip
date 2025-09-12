package mochi;

/**
 * Represents an abstract task meant to be extended by ToDo, Deadline, and Event classes.
 * This class provides basic functionality for marking tasks as completed or not completed,
 * but requires subclasses to implement a method for saving the task's data as string.
 */
public abstract class Task {
    protected final String description;
    protected boolean completed;

    /**
     * Creates a Task meant to be used internally by subclasses.
     *
     * @param desc the description of the task
     */
    public Task(String desc) {
        assert !desc.isEmpty() : "Task descriptions should not be empty.";
        this.description = desc;
        this.completed = false;
    }

    /**
     * Creates a Task meant to be used internally by subclasses.
     *
     * @param desc the description of the task
     * @param status the completion status of the task
     */
    public Task(String desc, boolean status) {
        assert !desc.isEmpty() : "Task descriptions should not be empty.";
        this.description = desc;
        this.completed = status;
    }

    /**
     * Marks this task as completed.
     *
     * @return A string indicating the details of the task has been marked as done.
     */
    public String mark() {
        this.completed = true;
        return String.format("""
                 Nice! I've marked this task as done:
                   [X] %s
                """, description);
    }

    /**
     * Marks this task as not completed.
     *
     * @return A string indicating the details of the task that has been marked as not done.
     */
    public String unmark() {
        this.completed = false;
        return String.format("""
                 OK, I've marked this task as not done yet:
                   [ ] %s
                """, description);
    }

    public boolean descriptionContains(String word) {
        return this.description.contains(word);
    }

    // Returns the string representation of this task to be saved as persistent data
    /**
     *  Returns the string representation of this task to be saved as persistent data
     */
    public abstract String getSaveString();

    /**
     *  Returns the task in string format for display.
     *
     *  @return the task in the format "[status] description", e.g. "[X] return book".
     */
    @Override
    public String toString() {
        char status = completed ? 'x' : ' ';
        return String.format("[%c] %s", status, description);
    }
}
