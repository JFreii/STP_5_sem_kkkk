package project.spring.controllers;

import project.spring.repo.*;
import project.spring.models.*;
import org.springframework.web.bind.annotation.*;
import java.net.URLDecoder;
import java.util.List;

/**
 * Класс HallController, в котором осуществленны REST запросы
 * POST, GET
 */
@RestController
@RequestMapping("api/theater")
public class HallController {


    private final HallRepo hallRepo;
    private final PerformanceRepo performanceRepo;


    /**
     *  Инициализация контроллера и запись приватных переменных
     * @param hallRepo  - ребенок JPA репозитория HallRepo
     * @param performanceRepo  - ребенок JPA репозитория PerformanceRepo
     */
    public HallController( HallRepo hallRepo, PerformanceRepo performanceRepo) {
        this.hallRepo = hallRepo;
        this.performanceRepo = performanceRepo;
    }

    /**
     * Создание нового зала с помощью HallRepo - репозиторий является ребенком JPA репозитория
     * @param hall -  объект класса Hall
     * @return возвращает новый объект класса Hall
     */
    @PostMapping("halls/posthalls")
    Hall postHall(@RequestBody Hall hall){
        return this.hallRepo.save(hall);
    }


    /**
     * Вывод всех существующих залов  в БД, с помощью HallRepo
     * @return все залы БД
     */
    @GetMapping("/halls/all")
    List<Hall> getHalls(){
        return this.hallRepo.findAll();
    }


    /**
     * Вывод зала из БД по названию спектакля, с помощью HallRepo и PerformanceRepo
     * Декодировка строки
     * @param name - строка, содержащая название спектакля
     * @return Hall - объект по названию спектакля
     */
    @GetMapping("/halls/perfName={name}")
    Hall getHallOnPerf(@PathVariable String name){
        return this.hallRepo.findHallById(this.performanceRepo.findAllByName(URLDecoder.decode(name)).get(0).getHall().getId());
    }



}
