package mochi;

/**
 * Handles visuals or texts displayed to the user, such as menus or error messasges.
 */
public class Ui {
    /**
     * Returns a UI object to display Mochi specific messages.
     */
    @SuppressWarnings("checkstyle:Regexp")
    public Ui() {
        // Initialization message
        System.out.println("""
            ____________________________________________________________
             Hello! I'm your personalized assistant,
              __  __            _     _
             |  \\/  | ___   ___| |__ (_)
             | |\\/| |/ _ \\ / __| '_ \\| |
             | |  | | (_) | (__| | | | |
             |_|  |_|\\___/ \\___|_| |_|_| \n
             Type 'help' to begin!
            ____________________________________________________________"""
        );
    }

    /**
     * Prints out a list of commands for user reference.
     */
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
             7. 'find <word>'
                -> prints a list of tasks that contain the word(s) given
             8. 'bye'
                -> exit the program
            ____________________________________________________________""");
    }

    public void error(Exception e) {
        System.out.println(e.toString());
    }

    public void print(String s) {
        System.out.println(s);
    }

    /**
     * Prints out the exit message when user quits.
     */
    public void exit() {
        System.out.println("""
            ____________________________________________________________
            Bye. Hope to see you again soon!
            ____________________________________________________________
            """);
    }
}
