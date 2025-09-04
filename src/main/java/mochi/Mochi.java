package mochi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Handles main Mochi application logic.
 */
public class Mochi extends Application {
    private final TaskList taskList;
    private final CommandParser cmd;
    private final Ui ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/MochiMascot.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/UserIcon.png"));

    /**
     * Constructor for Mochi application that takes in a data file name.
     *
     * @param fileName the name of the data file located in the /data folder
     */
    public Mochi(String fileName) {
        this.ui = new Ui();
        this.cmd = new CommandParser();
        FileHandler fh = new FileHandler(fileName);
        this.taskList = new TaskList(fh.load(), fh);
    }

    /**
     * Default constructor for JavaFX utility.
     */
    public Mochi() {
        this("data.txt");
    }

    /**
     * Required by JavaFX to provide concrete implementation.
     *
     * @param stage the primary stage that JavaFX provides
     */
    @Override
    public void start(Stage stage) {
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        DialogBox dialogBox = new DialogBox("Hello!", userImage);
        dialogContainer.getChildren().addAll(dialogBox);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        // Window formatting
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main program flow for user input and command handling.
     */
    public void run() {
        cmd.read();
        while (cmd.running()) {
            try {
                if (cmd.is("list")) {
                    ui.print(taskList.toString());
                } else if (cmd.is("mark")) {
                    try {
                        ui.print(taskList.complete(cmd.markCommand(taskList.size())));
                    } catch (MarkingException e) {
                        ui.error(e);
                    }
                } else if (cmd.is("unmark")) {
                    try {
                        ui.print(taskList.undo(cmd.unmarkCommand(taskList.size())));
                    } catch (MarkingException e) {
                        ui.error(e);
                    }
                } else if (cmd.is("todo")) {
                    try {
                        taskList.add(cmd.todoCommand());
                    } catch (ToDoException e) {
                        ui.error(e);
                    }
                } else if (cmd.is("deadline")) {
                    try {
                        taskList.add(cmd.deadlineCommand());
                    } catch (DeadlineException e) {
                        ui.error(e);
                    }
                } else if (cmd.is("event")) {
                    try {
                        taskList.add(cmd.eventCommand());
                    } catch (EventException e) {
                        ui.error(e);
                    }
                } else if (cmd.is("delete")) {
                    try {
                        taskList.remove(cmd.deleteCommand(taskList.size()));
                        ui.print(taskList.toString());
                    } catch (MochiException e) {
                        ui.error(e);
                    }
                } else if (cmd.is("find")) {
                    taskList.prinTasksWithWord(cmd.findCommand());
                } else if (cmd.is("help")) {
                    ui.showHelp();
                } else {
                    throw new MochiException();
                }
            } catch (Exception e) {
                ui.error(e);
            }
            // Always read new input
            cmd.read();
        }
        ui.exit();
    }

    /**
     * Application main entrypoint.
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        new Mochi("data.txt").run();
    }
}
