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

        while (!s.equals("bye")) {
            System.out.printf("""
                    ____________________________________________________________
                    %s
                    ____________________________________________________________
                    """, s);
            s = scan.next();
        }
        System.out.println("""
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """);

    }
}
