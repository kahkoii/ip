public class MochiException extends Exception {
    public MochiException() {
        super();
    }

    @Override
    public String toString() {
            return """
            ========================== ERROR ===========================
             Very confused, much wow. Invalid command entered.
             Enter command 'help' for a list of commands
            ============================================================""";
    }
}
