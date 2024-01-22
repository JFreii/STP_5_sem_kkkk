package project.spring.repo;
import project.spring.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
/**
 * Репозиторий SeatRepo - ребенок JPA репозитория
 */

public interface SeatRepo extends JpaRepository<Seat, Long> {

    /**
     * поиск места по id зала
     * @param id - id зала
     * @return список мест
     */

    List<Seat> findAllByHall_Id(Long id);

    /**
     * поиск местк по id
     * @param id - id места
     * @return место
     */
    Seat findSeatById(Long id);
}
