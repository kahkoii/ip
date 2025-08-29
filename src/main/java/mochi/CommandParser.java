package mochi;

import java.util.Scanner;
import java.util.ArrayList;

public class CommandParser {
    private final Scanner scan;
    private String command;
    private String parameters;
    private boolean running;

    private final String[] commands = new String[] {
            "bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete", "find", "help"
    };

    public CommandParser() {
        this.scan = new Scanner(System.in);
        this.running = true;
    }

    public void read() {
        String s = this.scan.nextLine();
        this.parameters = s;
        if (s.startsWith("bye")) {
            this.running = false;
        } else {
            for (String word : commands) {
                if (s.startsWith(word)) {
                    this.command = word;
                    return;
                }
            }
            this.command = "unknown";
        }
    }

    public boolean is(String s) {
        return s.equals(this.command);
    }

    public boolean running() {
        return this.running;
    }

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
        }
        catch (Exception e) {
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
        }
        catch (Exception e) {
            throw new EventException();
        }
    }

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
