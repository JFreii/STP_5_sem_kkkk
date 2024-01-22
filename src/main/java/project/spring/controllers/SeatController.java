package project.spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.spring.models.Performance;
import project.spring.models.Seat;
import project.spring.models.Ticket;
import project.spring.repo.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс SeatController, в котором осуществленны REST запросы
 * GET
 */

@RestController
@RequestMapping("api/theater")
public class SeatController {

    private final TicketRepo ticketRepo;
    private final HallRepo hallRepo;
    private final SeatRepo seatRepo;
    private final PerformanceRepo performanceRepo;

    /**
     * Инициализация контроллера и запись приватнных переменных
     * @param ticketRepo  - ребенок JPA репозитория TicketRepo
     * @param hallRepo  - ребенок JPA репозитория HallRepo
     * @param seatRepo  - ребенок JPA репозитория SeatRepo
     * @param performanceRepo - ребенок JPA репозитория PerformanceRepo
     */

    public SeatController(TicketRepo ticketRepo, HallRepo hallRepo,
                             SeatRepo seatRepo, PerformanceRepo performanceRepo) {

        this.ticketRepo = ticketRepo;
        this.hallRepo = hallRepo;
        this.seatRepo = seatRepo;
        this.performanceRepo = performanceRepo;

    }


    /**
     * Вывод всех существующих мест в БД, с помощью SeatRepo
     * @return все места БД
     */

    @GetMapping("/seats/all")
    List<Seat> getSeats(){
        return this.seatRepo.findAll();
    }


    /**
     * Вывод мест в зале по типу места (зоне), с помощью  SeatRepo
     * @param type - строка, которая принимает тип зала
     * @return list - список мест по определеному типу зала
     */
    @GetMapping("/seats/type={type}")
    List<Seat> getTypeofSeats(@PathVariable String type){
        List<Seat> allseat = this.seatRepo.findAll();
        List<Seat> list = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for ( Seat typeinseat  :  allseat){
            if(!names.contains(typeinseat.getType())){
                list.add(typeinseat);
                names.add(typeinseat.getType());
                System.out.println(names);
            }else{
                System.out.println("ошибка");
            }

        }
        return list;




    }

    /**
     * Вывод всех мест по залу, с помощью  SeatRepo и HallRepo
     * @param name - строка, принимающач название зала, по  которому выводим места
     * @return список всех мест, находящихся в зале
     */

    @GetMapping("/seats/hall={name}")
    List<Seat> getseatsByHall(@PathVariable String name){
        return this.seatRepo.findAllByHall_Id(this.hallRepo.findHallByName(URLDecoder.decode(name, StandardCharsets.UTF_8)).getId());
    }


    /**
     * Ввывод всех  свободных мест по названию спектакля и по времени проведения спектакля,
     * с помощью  SeatRepo, TicketRepo и PerformanceRepo.
     * Нахождение спектакля по его названию и времени
     * Нахождение всех мест  по hall id
     * Нахождение всех билетов по Performance id
     * @param name - строка, отображающая название спектакля
     * @param time - строка, отобращающая время проведения спектакля
     * @return все свободные места на спектакль
     */

    @GetMapping("/seats/name={name}/time={time}")
    List<Seat> getperfByNameandTime(@PathVariable String name, @PathVariable String time){
        Performance chosen = this.performanceRepo.findPerformanceByNameAndTime(URLDecoder.decode(name,
                StandardCharsets.UTF_8), time);
        List<Seat> seatsForPerf = this.seatRepo.findAllByHall_Id(chosen.getHall().getId());
        List<Seat> freeForPerf = new ArrayList<>();
        List<Ticket> ticketsForPerf = this.ticketRepo.findAllByPerformanceId(chosen.getId());
        List<Long> ids = new ArrayList<>();
        for (Ticket tick: ticketsForPerf){
            ids.add(tick.getId());
        }
        for (int i=0; i<seatsForPerf.size(); i++){
            Long seatId = seatsForPerf.get(i).getId();
            if(!ids.contains(seatId)){
                freeForPerf.add(seatsForPerf.get(i));
            }
        }
        System.out.println(freeForPerf);
        return freeForPerf;
    }



}
