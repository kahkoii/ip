package mochi;

import java.util.ArrayList;

/*
 * Manages the 1-indexed list of tasks created by the user.
 */
public class TaskList {
    private final ArrayList<Task> list;
    private final FileHandler fh;

    public TaskList(ArrayList<Task> arr, FileHandler fh) {
        this.list = arr;
        this.fh = fh;
    }

    /*
     * Adds a task to the list and saves the updated list to the save file.
     */
    public void add(Task t) {
        list.add(t);
        System.out.printf("""
            ____________________________________________________________
            Got it. I've added this task:
                %s
            Now you have %d tasks in  the list.
            ____________________________________________________________
            """, t.toString(), list.size());
        fh.save(list);
    }

    /*
     * Removes a task from the list by its 1-indexed position and saves the updated list to the save file.
     */
    public void remove(int taskNumber) {
        list.remove(taskNumber - 1);
        System.out.println("""
            ____________________________________________________________
            Delete operation successful.""");
        fh.save(list);
    }

    /*
     * Returns the number of tasks in the list.
     * 
     * @return int number of tasks in the list
     */
    public int size() {
        return this.list.size();
    }

    /*
     * Marks a task as completed by its 1-indexed position and saves the updated list to the save file.
     * 
     * @return Completion output message
     */
    public String complete(int taskNumber) {
        String res = list.get(taskNumber-1).mark();
        fh.save(list);
        return res;
    }

    /*
     * Marks a task as uncompleted by its 1-indexed position and saves the updated list to the save file.
     * 
     * @return Undo output message
     */
    public String undo(int taskNumber) {
        String res = list.get(taskNumber-1).unmark();
        fh.save(list);
        return res;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        String s = """
            ____________________________________________________________
            Here are the tasks in your list:
            """;
        for (int i = 0; i < list.size(); i++) {
            s = s.concat(String.format("%d.%s\n", i + 1, list.get(i).toString()));
        }
        s = s.concat("____________________________________________________________");
        return s;
    }
}
