package project.spring.repo;
import project.spring.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Репозиторий TicketRepo - ребенок JPA репозитория
 */

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    /**
     * поиск билета по id
     * @param id - id билета
     * @return билет
     */
    Ticket findTicketById(Long id);
    /**
     * поиск билетов по спекатклю
     * @param id - id спекаткля
     * @return список билетов по спектаклю
     */
    List<Ticket> findAllByPerformanceId(Long id);

    /**
     * поиск билетов по названию спектакля
     * @param name - название спектакл
     * @return список билетов по названию спекатакля
     */

    List<Ticket> findTicketByPerformance_Name(String name);

    /**
     * поиск билетов по типу места
     * @param type - тип  места
     * @return список билетов по типу места
     */

    List<Ticket> findTicketBySeat_Type(String type);
}
