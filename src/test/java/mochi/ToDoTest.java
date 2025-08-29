package mochi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void overloadConstructorTest() {
        ToDo t = new ToDo("Hackathon", true);
        assertEquals("[T][x] Hackathon", t.toString());
    }

    @Test
    public void saveStringTest() {
        ToDo t = new ToDo("Homework");
        assertEquals("T | 0 | Homework", t.getSaveString());
    }
}
