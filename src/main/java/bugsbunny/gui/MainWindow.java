package bugsbunny.gui;

import bugsbunny.app.BugsBunny;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private BugsBunny bugsbunny;

    // Image source: https://ar.inspiredpencil.com/pictures-2023/how-to-draw-daffy-duck-step-by-step
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/daffyduck.png"));

    // Image source: https://pin.it/3Y7ORlM9p
    private Image bugsbunnyImage = new Image(this.getClass().getResourceAsStream("/images/bugsbunny.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Bugs Bunny instance */
    public void setBugsBunny(BugsBunny b) {
        bugsbunny = b;

        dialogContainer.getChildren().add(
                DialogBox.getBugsBunnyDialog(
                        "Neeaah, What's up Doc!?\nType 'help' to see available commands.",
                        bugsbunnyImage
                )
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing the chatbot's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bugsbunny.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBugsBunnyDialog(response, bugsbunnyImage)
        );
        userInput.clear();
    }
}
