import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime due;

    public Deadline(String desc, String dueDate) throws DeadlineException {
        super(desc);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.due = LocalDateTime.parse(dueDate, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            try {
                // Handle no time specified
                this.due = LocalDate.parse(dueDate).atStartOfDay();
            } catch (DateTimeParseException e2) {
                throw new DeadlineException();
            }
        }
    }

    public Deadline(String desc, String dueDate, boolean status) throws DeadlineException {
        this(desc, dueDate);
        if (status) {
            this.mark();
        }
    }

    @Override
    public String getSaveString() {
        return String.format("D | %d | %s | %s", this.completed ? 1 : 0, this.description, this.due.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.due.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm")));
    }
}
