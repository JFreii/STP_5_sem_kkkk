package project.spring.controllers;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.spring.models.Client;
import project.spring.repo.*;
import java.util.List;


/**
 * Класс ClientController, в котором осуществленны REST запросы
 * POST, GET, PUT, DELETE
 */
@RestController
@RequestMapping("api/theater")
public class ClientController {


    private final ClientRepo clientRepo;

    /**
     * Инициализация контроллера и запись приватной переменной
     * @param clientRepo - ребенок JPA репозитория ClientRepo
     */

    public ClientController(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;

    }

    /**
     * Создание нового клиента с помощью ClientRepo - репозиторий является ребенком JPA репозитория
     * Создается новый JSON-объект, в который передается сущность клиента и записывается в БД
     * @param client - строка, содержащая json с необходимыми полями
     * @return сохраненный клиент
     * @throws JSONException
     */

    @PostMapping("/clients/postclient")
    Client postClient(@RequestBody String client) throws JSONException {
        JSONObject rawClient = new JSONObject(client);
        Client finalClient = new Client(rawClient.getString("firstname"), rawClient.getString("lastname"),
                rawClient.getString("contact"), rawClient.getInt("age"));
        System.out.println(finalClient);
        return this.clientRepo.save(finalClient);
    }


    /**
     * Вывод всех данных по клиенту, который задан по id параметру, с помощью ClientRepo
     * @param id - значение, по которому определяется клиент в БД
     * @return клиента по id
     */

    @GetMapping("/clients/{id}")
    Client getClient(@PathVariable Long id) {
        return this.clientRepo.findClientById(id);
    }

    /**
     * Вывод всех существующих клиентов в БД, с помощью ClientRepo
     * @return все клиенты БД
     */


    @GetMapping("/clients/all")
    List<Client> getClients(){
        return this.clientRepo.findAll();
    }

    /**
     * Удаление клиента из БД по id, с помощью ClientRepo
     * @param id - значение, по которому определяется клиент в БД
     * @return клиента по id
     */

    @DeleteMapping("/clients")

        // http://127.0.0.1:8080/api/theater/clients?id=..
    Client deleteClient(@RequestParam Long id) {
        Client foundClient = this.clientRepo.findClientById(id);
        this.clientRepo.delete(foundClient);
        return foundClient;
    }

    /**
     * Изменение данных клиента по id, с помощью ClientRepo. Задаются новые данные в существующие параметры
     * @param newClient - объект класса Client
     * @return обновленный объект класса Client
     */

    @Transactional
    @PutMapping("/updateclient")
    public Client updateClient(@RequestBody Client newClient){
        Client client = this.clientRepo.findClientById(newClient.getId());
        client.setId(newClient.getId());
        client.setFirstname(newClient.getFirstname());
        client.setLastname(newClient.getLastname());
        client.setContact(newClient.getContact());
        client.setAge(newClient.getAge());
        System.out.println(client);
        return this.clientRepo.save(client);
    }

}
