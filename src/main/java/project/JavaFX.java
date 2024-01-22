package project;
import javafx.application.Application;
import javafx.scene.control.Alert;
import project.controllerfx.RootController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * Класс JavaFX для построения сцены
 */
public class JavaFX extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    /**
     * запуск сцены
     * @param primaryStage - сцена
     * @throws Exception - обработка ошибки
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Информационно-справочная система театра");
        initRootLayout();
    }
    /**
     * метод инициализации для запуска главного окна
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JavaFX.class.getResource("/fxml/root.fxml"));
            rootLayout = loader.load();
            RootController rootController = loader.getController();
            rootController.setStage(primaryStage);
            rootController.setMain(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Ошибка");
            alert2.setHeaderText("Сервер не запущен. Запустите сервер!");
            alert2.showAndWait();
        }
    }

    /**
     * метод класса JavaFX, задает главную сцену
     * @return primaryStage
     */

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     *  статический метод main
     * @param args - строка
     */
    public static void main(String[] args) {
        launch(args);
    }
}
