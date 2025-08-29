package mochi;

public class MochiException extends Exception {
    private String info = null;

    public MochiException() {
        super();
    }

    public MochiException(String info) {
        super();
        this.info = info;
    }

    @Override
    public String toString() {
        if (this.info != null) {
            return String.format("""
                ========================== ERROR ===========================
                 %s
                ============================================================""", this.info);
        }
        else {
            return """
                ========================== ERROR ===========================
                 Very confused, much wow. Invalid command entered.
                 Enter command 'help' for a list of commands
                ============================================================""";
        }
    }
}
