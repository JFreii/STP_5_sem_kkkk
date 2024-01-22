package project.spring.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

/**
 * Класс Ticket
 * Сущность Ticket
 */

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Связь многие к одному со сущностью Client, Seat, Performance
     */

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perf_id")
    private Performance performance;

    private Integer price;
    private String date;

    /**
     * Конструктор, в котором определяются переменные ( атрибуты сущности)
     * @param price - цена билета
     * @param date - дата проведения
     */

    public Ticket( Integer price, String date) {
        this.price = price;
        this.date = date;
    }

    /**
     * Конструктор Ticket
     */

    public Ticket(){}

    /**
     * метод класса Ticket
     * @return возвращает  спектакль, на который куплен билет
     */
    public Performance getPerformance() {
        return performance;
    }


    /**
     * метод класса Ticket, который задает спектакль, на который куплен билет
     * @param performance - спектакль
     */

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    /**
     * метод класса Ticket
     * @return - возвращает  id билета
     */

    public Long getId() {
        return id;
    }

    /**
     * метод класса Ticket
     * @return - возвращает дату на билете
     */

    public String getDate() {
        return date;
    }

    /**
     * метод класса Ticket, который задает дату на билете
     * @param date - дата на билет
     */

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * метод класса Ticket, который задает id билета
     * @param id -id билета
     */

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * метод класса Ticket
     * @return - возвращает  клиента, который купил билет
     */

    public Client getClient() {
        return client;
    }

    /**
     * метод класса Ticket, который задает клиента, который покупает билет
     * @param client - клиент
     */

    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * метод класса Ticket
     * @return - возвращает  место, которое передается в билет
     */

    public Seat getSeat() {
        return seat;
    }

    /**
     * метод класса Ticket, который задает место, которое передается в билет
     * @param seat - место
     */

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /**
     * метод класса Ticket
     * @return - возвращает  цену билета
     */

    public Integer getPrice() {
        return price;
    }

    /**
     * метод класса Ticket, который задает цену билета
     * @param price - цена билета
     */

    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Вывод сущности Ticket
     * @return - сущность Ticket со всеми значениями
     */

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", client=" + client +
                ", seat=" + seat +
                ", performance=" + performance +
                ", price=" + price +
                ", date='" + date + '\'' +
                '}';
    }
}
