package project.spring.models;

import javax.persistence.*;

/**
 * Класс Hall
 * Сущность Hall
 */

@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    /**
     * Конструктор Hall
     */

    public Hall(){}

    /**
     * Конструктор, в котором определяются переменные ( атрибуты сущности)
     * @param name - название зала
     */

    public Hall(String name) {
        this.name =  name;

    }

    /**
     * метод класса Hall
     * @return возвращает id зала
     */
    public Long getId() {
        return id;
    }




    /**
     * метод класса Hall, который задает id зала
     * @param id - id зала
     */

    public void setId(Long id) {
        this.id = id;
    }


    /**
     * метод класса Hall
     * @return возвращает название зала
     */
    public String getName() {
        return name;
    }



    /**
     * метод класса Hall, который задает название зала
     * @param name - название зала
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Вывод сущности Hall
     * @return - сущность Hall со всеми значениями
     */

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
