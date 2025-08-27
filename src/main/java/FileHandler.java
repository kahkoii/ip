import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final String fileName;

    public FileHandler(String s) {
        this.fileName = s;
    }

    public void save(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter("data/" + this.fileName);
            for (Task task : list) {
                fw.write(task.getSaveString());
                fw.write(System.lineSeparator()); // Start next task on a new line
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File("data/" + this.fileName);
        try {
            boolean newFileCreated = file.createNewFile();
            if (newFileCreated) {
                return new ArrayList<>();
            }
            System.out.println("""
                Save file found, loading task list from saved data.
                ____________________________________________________________""");
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                Task newTask = parseLine(s.nextLine());
                tasks.add(newTask);
            }
        } catch (IOException e) {
            System.out.println("""
            ____________________________________________________________
            Error parsing data file, please try again.
            ____________________________________________________________""");
        } catch (MochiException e) {
            System.out.println(e.toString());
        }
        return tasks;
    }

    /**
     * T | 1 | read book
     * D | 0 | return book | June 6th
     * E | 0 | project meeting | Aug 6th | Aug 8th
     */
    private Task parseLine(String line) throws MochiException {
        String[] data = line.split("\\|");
        return switch (data[0].toUpperCase().trim()) {
            case ("T") -> new ToDo(data[2].trim(), data[1].trim().equals("1"));
            case ("D") ->  new Deadline(data[2].trim(), data[3].trim(), data[1].trim().equals("1"));
            case ("E") ->  new Event(data[2].trim(), data[3].trim(), data[4].trim(), data[1].trim().equals("1"));
            default -> throw new MochiException("Data file corrupted or in incorrect format.");
        };
    }
}
