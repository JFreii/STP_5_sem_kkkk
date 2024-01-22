package project.controllerfx;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Контроллер окна author.fxml
 */

public class AuthorController {

    private Stage stage;
    /**
     * инициализация контроллера
     */
    @FXML
    private void initialize(){
        System.out.println("полученная инфа из автора");
    }
    /**
     * Конструктор AuthorController
     */
    public AuthorController(){ }

    /**
     * метод класса AuthorController
     * @return stage
     */

    public Stage getStage() {
        return stage;
    }
    /**
     * Метод класса AuthorController, который задает сцену
     * @param stage - сцена
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
