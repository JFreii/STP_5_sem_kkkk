package project.DTO;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * PerformanceDTO - подобный объект Performance, который хранит данных
 */
public class PerformanceDTO {
    private Long id;
    private String name,timeofpremier,timeofend,time ;
    private Integer agelimit;
    private HallDTO hall;

    /**
     * Конструктор PerformanceDTO
     */
    public PerformanceDTO() {
    }

    /**
     * Задает переменные PerformanceDTO
     * @param id - id
     * @param name - название спектакля
     * @param timeofpremier - премьера
     * @param timeofend - последний день показа
     * @param time - время проведения
     * @param agelimit - возрастное ограничение
     * @param hall - зал
     */

    protected PerformanceDTO(Long id, String name, String timeofpremier,
                             String timeofend, String time, int agelimit, HallDTO hall) {
        this.id = id;
        this.name = name;
        this.timeofpremier = timeofpremier;
        this.timeofend = timeofend;
        this.time = time;
        this.agelimit = agelimit;
        this.hall = hall;
    }

    /**
     * Возврает объект класса PerformanceDTO
     * @param jsonObject -  серверное Json представление объекта Performance
     * @return PerformanceDTO
     * @throws JSONException - json ошибка
     */

    static public PerformanceDTO instanceOf(JSONObject jsonObject) throws JSONException {
        return new PerformanceDTO(
                jsonObject.getLong("id"),
                jsonObject.getString("name"),
                jsonObject.getString("timeofpremier"),
                jsonObject.getString("timeofend"),
                jsonObject.getString("time"),
                jsonObject.getInt("agelimit"),
                HallDTO.instanceOf(jsonObject.getJSONObject("hall"))
        );
    }

    /**
     * метод класса PerformanceDTO
     * @return возвращает id спектакля
     */

    public Long getId() {
        return id;
    }


    /**
     * метод класса PerformanceDTO, который задает id спектакля
     * @param id - id спектакля
     */

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * метод класса PerformanceDTO
     * @return возвращает название спектакля
     */
    public String getName() {
        return name;
    }

    /**
     * метод класса PerformanceDTO
     * @return возвращает зал
     */

    public HallDTO getHall() {
        return hall;
    }


    /**
     * метод класса PerformanceDTO, который задает зал, в котором проходит спектакль
     * @param hall - зал
     */

    public void setHall(HallDTO hall) {
        this.hall = hall;
    }


    /**
     * метод класса PerformanceDTO, который задает название спектакля
     * @param name - название спектакля
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * метод класса PerformanceDTO
     * @return возвращает время проведения спектакля
     */
    public String getTime() {
        return time;
    }


    /**
     * метод класса PerformanceDTO, который задает время проведния спектакля
     * @param time - время спектакля
     */

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * конвертирует в строку
     * @return строковое представление объекта
     */

    @Override
    public String toString() {
        return name;
    }


}
