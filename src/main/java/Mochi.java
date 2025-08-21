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
                taskList[Integer.parseInt(task)-1].mark();
            }
            else if (s.startsWith("unmark")) {
                String task = s.substring(6).trim();
                taskList[Integer.parseInt(task)-1].unmark();
            }
            else if (s.startsWith("todo")) {
                String task = s.substring(4).trim();
                addTask(new ToDo(task));
            }
            else if (s.startsWith("deadline")) {
                String task = s.substring(8).trim();
                String[] text = task.split("/by");
                addTask(new Deadline(text[0].trim(), text[1].trim()));
            }
            else if (s.startsWith("event")) {
                String task = s.substring(5).trim();
                String[] text = task.split("/from");
                String desc = text[0].trim();
                String[] duration = text[1].split("/to");
                addTask(new Event(desc, duration[0].trim(), duration[1].trim()));
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
