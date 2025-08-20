import java.util.Scanner;

public class Mochi {
    public static void main(String[] args) {
        System.out.println("""
                ____________________________________________________________
                 Hello! I'm Mochi
                 What can I do for you?
                ____________________________________________________________
                """);
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        int listSize = 0;
        Task[] toDoList = new Task[100];

        while (!s.equals("bye")) {
            switch(s) {
                case "list":
                    System.out.println("""
                            ____________________________________________________________
                            Here are the tasks in your list:
                            """);
                    for (int i = 0; i < listSize; i++) {
                        System.out.printf("%d.%s\n", i+1, toDoList[i].toString());
                    }
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    if (s.startsWith("mark")) {
                        String task = s.substring(4).trim();
                        System.out.println(task);
                        toDoList[Integer.parseInt(task)-1].mark();
                    }
                    else if (s.startsWith("unmark")) {
                        String task = s.substring(6).trim();
                        toDoList[Integer.parseInt(task)-1].unmark();
                    }
                    else {
                        toDoList[listSize++] = new Task(s);
                        System.out.printf("""
                                ____________________________________________________________
                                added: %s
                                ____________________________________________________________
                                """, s);
                    }
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
}
