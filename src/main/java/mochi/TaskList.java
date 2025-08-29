package mochi;

import java.util.ArrayList;

// 1-indexed list
public class TaskList {
    private final ArrayList<Task> list;
    private final FileHandler fh;

    public TaskList(ArrayList<Task> arr, FileHandler fh) {
        this.list = arr;
        this.fh = fh;
    }

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

    public void remove(int taskNumber) {
        list.remove(taskNumber - 1);
        System.out.println("""
            ____________________________________________________________
            Delete operation successful.""");
        fh.save(list);
    }

    public int size() {
        return this.list.size();
    }

    // Returns output message
    public String complete(int taskNumber) {
        String res = list.get(taskNumber-1).mark();
        fh.save(list);
        return res;
    }

    // Returns output message
    public String undo(int taskNumber) {
        String res = list.get(taskNumber-1).unmark();
        fh.save(list);
        return res;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void prinTasksWithWord(String word) {
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t.descriptionContains(word)) {
                if (!found) {
                    found = true;
                    System.out.println("""
                        ____________________________________________________________
                         Here are the matching tasks in your list:""");
                }
                System.out.printf("%d.%s \n", i + 1, t.toString());
            }
        }
        if (found) {
            System.out.println("____________________________________________________________");
        } else {
            System.out.printf("""
                        ____________________________________________________________
                         There are no tasks that contain the word(s) '%s'
                        ------------------------------------------------------------ \n""", word);
        }
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
