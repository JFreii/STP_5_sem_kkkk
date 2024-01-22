package project.spring.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

/**
 * Класс Performance
 * Сущность Performance
 */

@Entity
@Table(name = "performances")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String timeofpremier, timeofend, time ;
    private Integer agelimit;

    /**
     * Связь многие к одному с сущность Hall
     */
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    /**
     * метод класса Hall
     * @return - hall
     */
    public Hall getHall() {
        return hall;
    }


    /**
     * Конструктор Performance
     */
    public Performance(){

    }

    /**
     * Конструктор, в котором определяются переменные ( атрибуты сущности)
     * @param name - назвнаие спектакля
     * @param timeofpremier - дата премьеры
     * @param timeofend - дата послежнего показа
     * @param time - время показала спектакля
     * @param agelimit - возрастное ограничение
     */

    public Performance(String name, String timeofpremier,
                       String timeofend, String time,  Integer agelimit ){
        this.name = name;
        this.timeofpremier = timeofpremier;
        this.timeofend = timeofend;
        this.time = time;
        this.agelimit = agelimit;
    }


    /**
     * метод класса Performance
     * @return возвращает id спектакля
     */


    public Long getId() {
        return id;
    }


    /**
     * метод класса Performance, который задает id спектакля
     * @param id - id спектакля
     */

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * метод класса Performance
     * @return возвращает название спектакля
     */


    public String getName() {
        return name;
    }



    /**
     * метод класса Performance, который задает зал, в котором проходит спектакль
     * @param hall - зал
     */


    public void setHall(Hall hall) {
        this.hall = hall;
    }


    /**
     * метод класса Performance
     * @return возвращает время проведения спектакля
     */

    public String getTime() {
        return time;
    }


    /**
     *  метод класса Performance, который задает время проведния спектакля
     * @param time - время спектакля
     */


    public void setTime(String time) {
        this.time = time;
    }


    /**
     *  метод класса Performance, который задает название спектакля
     * @param name - название спектакля
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * метод класса Performance
     * @return возвращает дату премьеры спектакля
     */


    public String getTimeofpremier() {
        return timeofpremier;
    }


    /**
     * метод класса Performance, который задает дату премьеры спектакля
     * @param timeofpremier - дата премьеры
     */

    public void setTimeofpremier(String timeofpremier) {
        this.timeofpremier = timeofpremier;
    }


    /**
     * метод класса Performance
     * @return возвращает дату последнего показа спектакля
     */

    public String getTimeofend() {
        return timeofend;
    }


    /**
     * метод класса Performance, который задает дату последнего показа спектакл
     * @param timeofend - дата последнего показа
     */

    public void setTimeofend(String timeofend) {
        this.timeofend = timeofend;
    }

    /**
     * метод класса Performance
     * @return возвращает возрастное ограничение спектакля
     */


    public Integer getAgelimit() {
        return agelimit;
    }



    /**
     * метод класса Performance, который задаетвозрастное ограничение спектакля
     * @param agelimit - возрастное ограничение
     */

    public void setAgelimit(Integer agelimit) {
        this.agelimit = agelimit;
    }


    /**
     * Вывод сущности Performance
     * @return сущность Performance со всеми значениями
     */


    @Override
    public String toString() {
        return "Performance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timeofpremier='" + timeofpremier + '\'' +
                ", timeofend='" + timeofend + '\'' +
                ", time='" + time + '\'' +
                ", agelimit=" + agelimit +
                '}';
    }
}
