package mochi;

import java.util.Scanner;

/*
 * Parses user commands and extracts relevant information for processing.
 * Also executes method calls and handles errors related to command parsing.
 */
public class CommandParser {
    private final Scanner scan;
    private String command;
    private String parameters;
    private boolean running;

    private final String[] COMMANDS = new String[] {
            "bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete", "find", "help"
    };

    public CommandParser() {
        this.scan = new Scanner(System.in);
        this.running = true;
    }

    /*
     * Reads a line of input from the user and determines the command type and parameters.
     * If the command is 'bye', sets running to false to indicate program termination.
     */
    public void read() {
        String s = this.scan.nextLine();
        this.parameters = s;
        if (s.startsWith("bye")) {
            this.running = false;
        } else {
            for (String word : COMMANDS) {
                if (s.startsWith(word)) {
                    this.command = word;
                    return;
                }
            }
            this.command = "unknown";
        }
    }

    /*
     * Checks if the parsed command matches the given string.
     *
     * @param s the command string to check against
     * @return true if the parsed command matches s, false otherwise
     */
    public boolean is(String s) {
        return s.equals(this.command);
    }

    /*
     * Returns whether the program should continue running.
     */
    public boolean running() {
        return this.running;
    }

    /*
     * Parses and validates the parameters for the 'mark' command.
     *
     * @param listSize the current size of the task list for validation
     * @return the 1-indexed task number to be marked as completed
     * @throws MarkingException if the parameters are invalid or out of range
     */
    public int markCommand(int listSize) throws MarkingException {
        String task = this.parameters.substring(4).trim();
        int taskNo;
        try {
            taskNo = Integer.parseInt(task);
        } catch (Exception e) {
            throw new MarkingException(task, listSize);
        }
        if (taskNo > listSize || taskNo < 1) {
            throw new MarkingException(task, listSize);
        }
        return taskNo;
    }

    /*
     * Parses and validates the parameters for the 'unmark' command.
     *
     * @param listSize the current size of the task list for validation
     * @return the 1-indexed task number to be marked as uncompleted
     * @throws MarkingException if the parameters are invalid or out of range
     */
    public int unmarkCommand(int listSize) throws MarkingException {
        String task = this.parameters.substring(6).trim();
        int taskNo;
        try {
            taskNo = Integer.parseInt(task);
        } catch (Exception e) {
            throw new MarkingException(task, listSize);
        }
        if (listSize == 0 || taskNo > listSize) {
            throw new MarkingException(task, listSize);
        }
        return taskNo;
    }

    public Task todoCommand() throws ToDoException {
        String task = this.parameters.substring(4).trim();
        if (task.isEmpty()) {
            throw new ToDoException();
        }
        return new ToDo(task);
    }

    public Task deadlineCommand() throws DeadlineException {
        try {
            String task = this.parameters.substring(8).trim();
            String[] text = task.split("/by", 2);
            String desc = text[0].trim(), by = text[1].trim();
            if (desc.isEmpty() || by.isEmpty()) {
                throw new DeadlineException();
            }
            return new Deadline(desc, by);
        } catch (Exception e) {
            throw new DeadlineException();
        }
    }

    public Task eventCommand() throws EventException {
        try {
            String task = this.parameters.substring(5).trim();
            String[] text = task.split("/from", 2);
            String desc = text[0].trim();
            String[] duration = text[1].split("/to", 2);
            String from = duration[0].trim(), to = duration[1].trim();
            if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new EventException();
            }
            return new Event(desc, duration[0].trim(), duration[1].trim());
        } catch (Exception e) {
            throw new EventException();
        }
    }

    /*
     * Parses and validates the parameters for the 'delete' command.
     *
     * @param listSize the current size of the task list for validation
     * @return the 1-indexed task number to be deleted
     * @throws MochiException if the parameters are invalid or out of range
     */
    public int deleteCommand(int listSize) throws MochiException {
        String task = this.parameters.substring(6).trim();
        int taskNo;
        try {
            taskNo = Integer.parseInt(task);
        } catch (Exception e) {
            throw new MochiException("Please provide a task number for delete operation.");
        }
        if (listSize == 0) {
            throw new MochiException("There are no tasks to delete.");
        }
        else if (taskNo > listSize || taskNo < 1) {
            throw new MochiException(String.format("Invalid task number provided. Range is from 1 to %d.", listSize));
        }
        return taskNo;
    }

    public String findCommand() {
        return this.parameters.substring(4).trim();
    }
}
