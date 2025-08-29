package mochi;

public class Mochi {
    private final TaskList taskList;
    private final CommandParser cmd;
    private final Ui ui;

    public Mochi(String fileName) {
        this.ui = new Ui();
        this.cmd = new CommandParser();
        FileHandler fh = new FileHandler(fileName);
        this.taskList = new TaskList(fh.load(), fh);
    }

    public void run() {
        cmd.read();
        while (cmd.running()) {
            try {
                if (cmd.is("list")) {
                    ui.print(taskList.toString());
                } else if (cmd.is("mark")) {
                    try {
                        ui.print(taskList.complete(cmd.markCommand(taskList.size())));
                    } catch (MarkingException e) {
                        ui.error(e);
                    }
                } else if (cmd.is("unmark")) {
                    try {
                        ui.print(taskList.undo(cmd.unmarkCommand(taskList.size())));
                    } catch (MarkingException e) {
                        ui.error(e);
                    }
                } else if (cmd.is("todo")) {
                    try {
                        taskList.add(cmd.todoCommand());
                    } catch (ToDoException e) {
                        ui.error(e);
                    }
                } else if (cmd.is("deadline")) {
                    try {
                        taskList.add(cmd.deadlineCommand());
                    } catch (DeadlineException e) {
                        ui.error(e);
                    }
                } else if (cmd.is("event")) {
                    try {
                        taskList.add(cmd.eventCommand());
                    } catch (EventException e) {
                        ui.error(e);
                    }
                } else if (cmd.is("delete")) {
                    try {
                        taskList.remove(cmd.deleteCommand(taskList.size()));
                        ui.print(taskList.toString());
                    } catch (MochiException e) {
                        ui.error(e);
                    }
                } else if (cmd.is("find")) {
                    taskList.prinTasksWithWord(cmd.findCommand());
                } else if (cmd.is("help")) {
                    ui.showHelp();
                } else {
                    throw new MochiException();
                }
            } catch (Exception e) {
                ui.error(e);
            }
            // Always read new input
            cmd.read();
        }
        ui.exit();
    }

     public static void main(String[] args) {
        new Mochi("data.txt").run();
    }
}
