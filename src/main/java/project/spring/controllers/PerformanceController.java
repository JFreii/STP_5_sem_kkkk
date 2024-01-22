package project.spring.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import project.spring.models.Performance;
import project.spring.repo.*;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс PerformanceController, в котором осуществленны REST запросы
 * POST, GET
 */
@RestController
@RequestMapping("api/theater")
public class PerformanceController {


    private final HallRepo hallRepo;
    private final PerformanceRepo performanceRepo;

    /**
     * Инициализация контроллера и запись приватных переменных
     * @param hallRepo  - ребенок JPA репозитория HallRepo
     * @param performanceRepo - ребенок JPA репозитория PerformanceRepo
     */

    public PerformanceController( HallRepo hallRepo, PerformanceRepo performanceRepo) {

        this.hallRepo = hallRepo;
        this.performanceRepo = performanceRepo;

    }


    /**
     * Создание нового зала с помощью PerformanceRepo, а так же HallRepo - репозитории являются детьми JPA репозитория
     * Создается  новый JSON-объект, в который передается сущность спектакля и записывается в БД
     * @param performance - строка, содержащая json с необходимыми полями
     * @return сохраненный спектакль
     * @throws JSONException
     */

    @PostMapping("/perf/postperf")
    Performance postPerformance(@RequestBody String performance) throws JSONException {
        JSONObject performanceObject = new JSONObject(performance);
        Performance per = new Performance();
        per.setName(performanceObject.getString("name"));
        per.setTimeofend(performanceObject.getString("timeofpremier"));
        per.setHall(this.hallRepo.findHallById(performanceObject.getJSONObject("hall").getLong("id")));
        return this.performanceRepo.save(per);
    }


    /**
     * Вывод всех существующих спектаклей в БД, с помощью PerformanceRepo
     * @return все спектакли БД
     */
    @GetMapping("/perfs/all")
    List<Performance> getPerfs(){
        return this.performanceRepo.findAll();
    }


    /**
     * Вывол всех спектаклей из БД, по дате показа самого спектакля.
     * Сравнивается день премьеры с датой выбранной в календаре пользователем и последний день показа
     * с датой выбранной в календаре пользователем.
     * @param data - строка, принимающая значение, выбранное в календаре пользователем
     * @return list, в котором записаны все спектакли подходящии под критерий выборки
     */

    @GetMapping("/perfs/data={data}")
    List<Performance> getPerformances(@PathVariable String data){
        List<Performance> allper = this.performanceRepo.findAll();
        List<Performance> list = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for ( Performance datainper  :  allper){
            LocalDate start = LocalDate.parse(datainper.getTimeofpremier());
            LocalDate end = LocalDate.parse(datainper.getTimeofend());
            LocalDate calendar = LocalDate.parse(data);
            if (start != null  &&  end != null){
                if((start.isBefore(calendar) && end.isAfter(calendar)) || (start.isEqual(calendar) || end.isEqual(calendar))){
                    if(!names.contains(datainper.getName())){
                        list.add(datainper);
                        names.add(datainper.getName());
                        System.out.println(names);
                    }
                }

            }else{
                System.out.println("пусто");
            }
        }
        return  list;
    }


    /**
     * Вывод спектаклей по времени проведения спектакля, с помощью PerformanceRepo
     * @param time - строка, в которой указывается время, во время которого идет спектакль
     * @return список спектаклей по определенному времени
     */

    @GetMapping("/perfs/time={time}")
    List<Performance> getPerfByTime(@PathVariable String time){
        return this.performanceRepo.findAllByTime(time);
    }

    /**
     * Вывод спектаклей по времени проведения спектакля  и названию самого спектакля, с помощью PerformanceRepo
     * @param name -  строка, в которой указывается название самого спектакля
     * @param time - строка, в которой указывается время, во время которого идет спектакль
     * @return спектакль по времени  и названию
     */

    @GetMapping("/perfs/name={name}/time={time}")
    Performance getPerByNameandTime(@PathVariable String name, @PathVariable String time) {
        return this.performanceRepo.findPerformanceByNameAndTime(URLDecoder.decode(name), time);
    }

    /**
     * Вывод спектаклей по названию самого спектакля, с помощью PerformanceRepo
     * @param name -  строка, в которой указывается название самого спектакля
     * @return список спектаклей по названию
     */
    @GetMapping("/perfs/name={name}")
    List<Performance> getTimesOnPerf(@PathVariable String name){
        return this.performanceRepo.findAllByName(URLDecoder.decode(name));
    }
}
