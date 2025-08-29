package mochi;

public class Ui {
    public Ui() {
        // Initialization message
        System.out.println("""
            ____________________________________________________________
             Hello! I'm your personalized assistant,
              __  __            _     _
             |  \\/  | ___   ___| |__ (_)
             | |\\/| |/ _ \\ / __| '_ \\| |
             | |  | | (_) | (__| | | | |
             |_|  |_|\\___/ \\___|_| |_|_|
            
             Type 'help' to begin!
            ____________________________________________________________""");
    }

    public void showHelp() {
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

    public void error(Exception e) {
        System.out.println(e.toString());
    }

    public void print(String s) {
        System.out.println(s);
    }

    public void exit() {
        System.out.println("""
            ____________________________________________________________
            Bye. Hope to see you again soon!
            ____________________________________________________________
            """);
    }
}
