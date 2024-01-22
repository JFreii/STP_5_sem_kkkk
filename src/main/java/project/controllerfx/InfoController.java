package project.controllerfx;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Контроллер окна info.fxml
 */

public class InfoController {

    private Stage stage;

    /**
     * инициализация контроллера
     * @throws IOException
     */

    @FXML
    private void initialize() throws IOException {
        System.out.println("полученная инфа");

    }

    /**
     * Конструктор InfoController
     */

    public InfoController(){ }

    /**
     * метод класса InfoController
     * @return stage
     */

    public Stage getStage() {
        return stage;
    }

    /**
     * Метод класса InfoController, который задает сцену
     * @param stage - сцена
     */

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}



