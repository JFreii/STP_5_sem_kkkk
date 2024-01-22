package project.spring.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

/**
 * Класс Seat
 * Сущность Seat
 */
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Связь многие к одному с сущностью Hall
     */

    @JsonManagedReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    private  String type;
    private Integer  location;

    /**
     * Конструктор Seat
     */
    public Seat(){
    }

    /**
     * Конструктор, в котором определяются переменные ( атрибуты сущности)
     * @param location - номер места
     * @param type - тип места
     */

    public Seat(Integer location, String type) {
        this.location = location;
        this.type = type;
    }

    /**
     * метод класса Seat
     * @return возвращает  зал, в котором находится место
     */

    public Hall getHall() {
        return hall;
    }


    /**
     * метод класса Seat, который задает зал, в котором находится место
     * @param hall - зал
     */

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    /**
     * метод класса Seat
     * @return - возвращает место
     */

    public Integer getLocation() {
        return location;
    }

    /**
     * метод класса Seat
     * @return - возвращает id
     */

    public Long getId() {
        return id;
    }


    /**
     * метод класса Seat, который задает id места
     * @param id - id места
     */


    public void setId(Long id) {
        this.id = id;
    }


    /**
     * метод класса Seat, который задает само место
     * @param location - место
     */


    public void setLocation(Integer location) {
        this.location = location;
    }

    /**
     * метод класса Seat
     * @return - возвращает зону
     */

    public String getType() {
        return type;
    }


    /**
     * метод класса Seat, который задает зону места
     * @param type - тип места
     */


    public void setType(String type) {
        this.type = type;
    }

    /**
     *  Вывод сущности Seat
     * @return - сущность Seat со всеми значениями
     */

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", hall=" + hall +
                ", type='" + type + '\'' +
                ", location=" + location +
                '}';
    }
}
