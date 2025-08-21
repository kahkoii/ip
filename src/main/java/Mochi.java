import java.util.Scanner;

public class Mochi {
    private static Task[] taskList;
    private static int listSize;

    public static void main(String[] args) {
        System.out.println("""
            ____________________________________________________________
             Hello! I'm
              __  __            _     _
             |  \\/  | ___   ___| |__ (_)
             | |\\/| |/ _ \\ / __| '_ \\| |
             | |  | | (_) | (__| | | | |
             |_|  |_|\\___/ \\___|_| |_|_|
            
             What can I do for you?
            ____________________________________________________________""");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        listSize = 0;
        taskList = new Task[100];

        while (!s.equals("bye")) {
            try {
                if (s.equals("list")) {
                    System.out.println("""
                        ____________________________________________________________
                        Here are the tasks in your list:
                        """);
                    for (int i = 0; i < listSize; i++) {
                        System.out.printf("%d.%s\n", i + 1, taskList[i].toString());
                    }
                    System.out.println("____________________________________________________________");
                }
                else if (s.startsWith("mark")) {
                    String task = s.substring(4).trim();
                    int taskNo;
                    try {
                        taskNo = Integer.parseInt(task);
                    } catch (Exception e) {
                        throw new MarkingException(task, listSize);
                    }
                    if (taskNo > listSize || taskNo < 1) {
                        throw new MarkingException(task, listSize);
                    }
                    else {
                        taskList[taskNo-1].mark();
                    }
                }
                else if (s.startsWith("unmark")) {
                    String task = s.substring(6).trim();
                    int taskNo;
                    try {
                        taskNo = Integer.parseInt(task);
                    } catch (Exception e) {
                        throw new MarkingException(task, listSize);
                    }
                    if (listSize == 0 || taskNo > listSize) {
                        throw new MarkingException(task, listSize);
                    }
                    else {
                        taskList[taskNo-1].mark();
                    }
                }
                else if (s.startsWith("todo")) {
                    String task = s.substring(4).trim();
                    if (task.isEmpty()) {
                        throw new ToDoException();
                    }
                    else {
                        addTask(new ToDo(task));
                    }
                }
                else if (s.startsWith("deadline")) {
                    try {
                        String task = s.substring(8).trim();
                        String[] text = task.split("/by", 2);
                        String desc = text[0].trim(), by = text[1].trim();
                        if (desc.isEmpty() || by.isEmpty()) {
                            throw new DeadlineException();
                        }
                        addTask(new Deadline(desc, by));
                    }
                    catch (Exception e) {
                        throw new DeadlineException();
                    }
                }
                else if (s.startsWith("event")) {
                    try {
                        String task = s.substring(5).trim();
                        String[] text = task.split("/from", 2);
                        String desc = text[0].trim();
                        String[] duration = text[1].split("/to", 2);
                        String from = duration[0].trim(), to = duration[1].trim();
                        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                            throw new EventException();
                        }
                        addTask(new Event(desc, duration[0].trim(), duration[1].trim()));
                    }
                    catch (Exception e) {
                        throw new EventException();
                    }
                }
                else if (s.startsWith("help")) {
                    System.out.println("""
                        ____________________________________________________________
                         1. 'todo <description>'
                            -> create a todo task
                         2. 'deadline <description> /by <due date>'
                            -> create a deadline task with due date
                         3. 'event <description> /from <start time> /to <end time'>
                            -> create an event task with start and end time
                         4. 'list'
                            -> display the list of tasks created and their details
                         5. 'mark <number>'
                            -> check off a task, specified by its order in the list
                               as completed
                         6. 'unmark <number>'
                            -> uncheck a task to set it to incomplete
                         7. 'bye'
                            -> exit the program
                        ____________________________________________________________""");
                }
                else {
                    throw new MochiException();
                }
            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
            // Read new input at the end of every command
            s = scan.nextLine();
        }
        System.out.println("""
            ____________________________________________________________
            Bye. Hope to see you again soon!
            ____________________________________________________________
            """);

    }

    private static void addTask(Task t) {
        taskList[listSize++] = t;
        System.out.printf("""
                    ____________________________________________________________
                    Got it. I've added this task:
                        %s
                    Now you have %d tasks in  the list.
                    ____________________________________________________________
                    """, t.toString(), listSize);
    }
}
