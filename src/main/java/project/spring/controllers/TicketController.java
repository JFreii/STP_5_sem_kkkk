package project.spring.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import project.spring.models.Ticket;
import project.spring.repo.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * Класс TicketController, в котором осуществленны REST запросы
 * POST, GET, DELETE
 */
@RestController
@RequestMapping("api/theater")
public class TicketController {

    private final ClientRepo clientRepo;
    private final TicketRepo ticketRepo;
    private final SeatRepo seatRepo;
    private final PerformanceRepo performanceRepo;

    /**
     * Инициализация контроллера и запись приватных переменных
     * @param clientRepo - ребенок JPA репозитория ClientRepo
     * @param ticketRepo - ребенок JPA репозитория TicketRepo
     * @param seatRepo- ребенок JPA репозитория SeatRepo
     * @param performanceRepo - ребенок JPA репозитория PerformanceRepo
     */

    public TicketController(ClientRepo clientRepo, TicketRepo ticketRepo,
                             SeatRepo seatRepo, PerformanceRepo performanceRepo) {
        this.clientRepo = clientRepo;
        this.ticketRepo = ticketRepo;
        this.seatRepo = seatRepo;
        this.performanceRepo = performanceRepo;

    }


    /**
     * Создание нового билета с помощью TicketRepo - репозиторий является ребенком JPA репозитория
     * Создается новый JSON-объект, в который передается сущность билета и записывается в БД
     * @param ticket - строка, содержащая json с необходимыми полями
     * @return сохраненный билет
     * @throws JSONException
     */


    @PostMapping(value = "tickets/posttickets")
    Ticket postTicket(@RequestBody String ticket) throws JSONException {
        JSONObject ticketObject = new JSONObject(ticket);
        Ticket tick  = new Ticket(ticketObject.getInt("price"), ticketObject.getString("date"));
        tick.setSeat(this.seatRepo.findSeatById(ticketObject.getJSONObject("seat").getLong("id")));
        tick.setPerformance(this.performanceRepo.findPerformanceById(ticketObject.getJSONObject("performance").getLong("id")));
        tick.setClient(this.clientRepo.findClientById(ticketObject.getJSONObject("client").getLong("id")));
        System.out.println(tick);
        return this.ticketRepo.save(tick);

    }


    /**
     * Вывод всех данных по билету, который задан по id параметру, с помощью TicketRepo
     * @param id - значение, по которому определяется билет в БД
     * @return билет по id
     */


    @GetMapping("/tickets/{id}")
    Ticket getTicket(@PathVariable Long id) {
        System.out.println(id);
//        curl -X GET http://127.0.0.1:8080/api/theater/tickets/2
        return this.ticketRepo.findTicketById(id);
    }

    /**
     * Вывод всех существующих билетов в БД, с помощью TicketRepo
     * @return все билеты БД
     */

    @GetMapping("/tickets/all")
    List<Ticket> getTickets(){
        return this.ticketRepo.findAll();
    }


    /**
     * Удаление билета из БД по id, с помощью TicketRepo
     * @param id - значение, по которому определяется билет в БД
     * @return билет по id
     */

    @DeleteMapping("/tickets")

        // http://127.0.0.1:8080/api/theater/tickets?id=..
    Ticket deleteTicket(@RequestParam Long id) {

        Ticket foundTicket = this.ticketRepo.findTicketById(id);
        this.ticketRepo.delete(foundTicket);
        return foundTicket;
    }

    /**
     * Вывод всех билетов по названию спектакля, с помощью TicketRepo
     * Декодировка названия спектакля
     * @param name - строка, обозначающая название спектакля, по которому
     *             хочу найти билеты
     * @return список билетов, купленных на определенный спектакль с названием name
     */


    @GetMapping("/tickets/perName={name}")
    List<Ticket> ticketOfPerName(@PathVariable String name){
        List<Ticket> tickList = this.ticketRepo.findTicketByPerformance_Name(URLDecoder.decode(name, StandardCharsets.UTF_8));
        return tickList;
    }

    /**
     * Вывод всех билетов по типу места, с помощью TicketRepo
     * Декодировка типа места
     * @param name - строка, обозначающая название зоны, по которой
     *                   хочу найти билеты
     * @return  писок билетов, купленных в определенные зоны с названием name
     */


    @GetMapping("/tickets/typeName={name}")
    List<Ticket> ticketOfTypeName(@PathVariable String name){
        List<Ticket> tickList = this.ticketRepo.findTicketBySeat_Type(URLDecoder.decode(name, StandardCharsets.UTF_8));
        return tickList;
    }
}
