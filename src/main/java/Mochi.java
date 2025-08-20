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
        String s = scan.next();
        int listSize = 0;
        String[] toDoList = new String[100];

        while (!s.equals("bye")) {
            switch(s) {
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < listSize; i++) {
                        System.out.printf("%d. %s\n", i+1, toDoList[i]);
                    }
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    toDoList[listSize++] = s;
                    System.out.printf("""
                    ____________________________________________________________
                    added: %s
                    ____________________________________________________________
                    """, s);
            }
            // Read new input at the end of every command
            s = scan.next();
        }
        System.out.println("""
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """);

    }
}
