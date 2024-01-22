package project.controllerfx;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import project.JavaFX;
import project.spring.models.Ticket;
import project.util.Rest;
import project.util.Parsing;
import java.io.IOException;
import java.util.Optional;
/**
 * Контроллер окна root.fxml
 */
public class RootController {

    private Parsing parsing = new Parsing();
    private Rest rest = new Rest();
    @FXML
    private TableView<Ticket> clientInfo;
    @FXML
    private TableColumn<Ticket, String> surnamecolumn;
    @FXML
    private TableColumn<Ticket, String> namecolumn;
    @FXML
    private Label lablecontact;
    @FXML
    private Label lableprice;
    @FXML
    private Label lableid;
    @FXML
    private Label lablehallname;
    @FXML
    private Label lablepertime;
    @FXML
    private Label lablenameofper;
    @FXML
    private Label labeldate;
    @FXML
    private Label lableseatlocation;
    @FXML
    private Label lableseattype;
    @FXML
    private TextField searchtext;
    @FXML
    private TextField firstnametext;
    @FXML
    private TextField lastnametext;
    @FXML
    private TextField contacttext;
    @FXML
    private TextField agetext;
    private JavaFX main;
    private Stage stage;
    private PostController postController;

    /**
     * констурктор RootController
     * @throws IOException - ошибка соединения
     */

    public RootController() throws IOException {
    }

    /**
     * заполнение таблицы в инициализации
     * вызовы методов  initTable() и SearchTable()
     */

    @FXML
    public void initialize() {
        initTable();
        SearchTable();
        namecolumn.setCellValueFactory(cellData -> cellData.getValue().getClient().getFirstNameProp());
        surnamecolumn.setCellValueFactory(cellData -> cellData.getValue().getClient().getLastNameProp());
        ShowInfo(null); //очищаем справа
        clientInfo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> ShowInfo(newValue));
    }

    /**
     * метод класса RootController, вызывающий класс JavaFX
     * @return main
     */
    public JavaFX getMain() {
        return main;
    }

    /**
     * запуск
     * @param main - main
     */

    public void setMain(JavaFX main) {
        this.main = main;
    }

    /**
     * метод класса RootController
     * @return stage
     */
    public Stage getStage() {
        return stage;
    }
    /**
     * Метод класса RootController, который задает сцену
     * @param stage - сцена
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    /**
     * заполнения билета по клиенту в таблице
     */
    public void initTable() {
        this.clientInfo.setItems(parsing.getTickets());
    }
    /**
     * обновление страницы
     */
    public void updatePage(){
        this.main.initRootLayout();
    }

    /**
     * получение полной информации о клиенте и его билете
     * @param ticket - объект сущности Ticket
     *
     */
    private void ShowInfo(Ticket ticket) {
        if (ticket != null) {
            lablecontact.setText(String.valueOf(ticket.getClient().getContact()));
            lableid.setText(String.valueOf(ticket.getId()));
            lableprice.setText(String.valueOf(ticket.getPrice()));
            labeldate.setText(String.valueOf(ticket.getDate()));
            lablehallname.setText(String.valueOf(ticket.getSeat().getHall().getName()));
            firstnametext.setText(String.valueOf(ticket.getClient().getFirstname()));
            lastnametext.setText(String.valueOf(ticket.getClient().getLastname()));
            contacttext.setText(String.valueOf(ticket.getClient().getContact()));
            agetext.setText(String.valueOf(ticket.getClient().getAge()));
            lablenameofper.setText(String.valueOf(ticket.getPerformance().getName()));
            lablepertime.setText(String.valueOf(ticket.getPerformance().getTime()));
            lableseatlocation.setText(String.valueOf(ticket.getSeat().getLocation()));
            lableseattype.setText(String.valueOf(ticket.getSeat().getType()));

        } else {
            lablecontact.setText("нет информации");
        }

    }

    /**
     * метод, окрывающий окно инструкции
     * @param actionEvent - событие, возникающие при  нажатие кнопки
     */
    @FXML
    public void searchButton(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/info.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setTitle("Инструкция");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * метод, открывающий окно "об авторе"
     * @param actionEvent - событие, возникающие при  нажатие кнопки
     */

    @FXML
    public void AuthorButton(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/author.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setTitle("об Авторе");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * метод, открывающий окно статистики
     * @param actionEvent - событие, возникающие при  нажатие кнопки
     */
    @FXML

    public void GraphicButton(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/graphic.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setTitle("Статистика");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * метод, который ищет клиентов по бд
     */

    @FXML
    public void SearchTable() {
        System.out.println("зашел");
        FilteredList<Ticket> filteredList = new FilteredList<>(parsing.getTickets(), b -> true);
        searchtext.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(ticket -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String filter = newValue.toLowerCase();
                if (ticket.getClient().getFirstname().toLowerCase().indexOf(filter) != -1) {
                    return true;
                } else if (ticket.getClient().getLastname().toLowerCase().indexOf(filter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Ticket> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(clientInfo.comparatorProperty());
        clientInfo.setItems(sortedList);
    }


    /**
     * метод удаления клиента из бд + окно с предупрежденим и ошибкой
     * @param actionEvent - событие, возникающие при  нажатие кнопки
     * @throws IOException - ошибка соединения
     */
    @FXML
    public void DeleteClient(javafx.event.ActionEvent actionEvent) throws IOException {

        System.out.println("удаление");
        int clientrow = clientInfo.getSelectionModel().getSelectedIndex();
        if (clientrow >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение");
            alert.setHeaderText("Нажмите ОК, если хотите удалить пользователя");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                System.out.println("перед удалением");
                if(clientInfo.getSelectionModel().getSelectedItem()  == null){
                    try{
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Ошибка");
                        alert2.setHeaderText("Не выбран пользователь для удаления");
                        alert2.showAndWait();
                    }catch (Exception e){
                    }
                }else{
                    Long idticket = clientInfo.getSelectionModel().getSelectedItem().getId();
                    Long idclient = clientInfo.getSelectionModel().getSelectedItem().getClient().getId();

                    rest.DeleteRest("http://localhost:8080/api/theater/tickets?id="+idticket);
                    rest.DeleteRest("http://localhost:8080/api/theater/clients?id="+idclient);
                    initTable();
                    clientInfo.getItems().removeAll(clientInfo.getSelectionModel().getSelectedItem());
                }
            }else if (option.get() == ButtonType.CANCEL){
                System.out.println("окно закрыто ");
            }
            alert.showAndWait();
        } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Ошибка");
                alert2.setHeaderText("Не выбран пользователь для удаления");
                alert2.showAndWait();
            }
    }

    /**
     * метод, окрывающий окно для добавления нового клиента и его билета
     */

    @FXML
    public void addButton() {
        System.out.println("зашел в добавление");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(main.getClass().getResource("/fxml/post.fxml"));
            AnchorPane root1 = fxmlLoader.load();
            stage = new Stage();
            stage.setTitle("Добавление");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("окно не открывается ");
        }
    }


    /**
     * изменение данных о пользователе
     * @throws JSONException - json ошибка
     * @throws IOException - ошибка соединения
     */
    public void UpdateClient() throws JSONException, IOException {
        System.out.println("вносим изменения");
        if((postController.TestString(firstnametext.getText())) &&
                (postController.TestString(lastnametext.getText())) &&
                (postController.TestContact(contacttext.getText()))  &&
                (postController.TestInt(agetext.getText())) ){
            System.out.println("проверка пройдена");
            JSONObject jsonObjectclient = new JSONObject();
            jsonObjectclient.put("id", clientInfo.getSelectionModel().getSelectedItem().getClient().getId());
            jsonObjectclient.put("firstname", firstnametext.getText());
            jsonObjectclient.put("lastname", lastnametext.getText());
            jsonObjectclient.put("contact", contacttext.getText());
            jsonObjectclient.put("age", agetext.getText());
            rest.PutRest("http://127.0.0.1:8080/api/theater/updateclient", jsonObjectclient);
            this.main.initRootLayout();
        }else{
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Ошибка");
            alert2.setHeaderText("Неверный тип данных. Введите имя/фамилию русскими символами." +
                    " Номер телефона вводится через +7");
            alert2.showAndWait();
        }
    }

}






