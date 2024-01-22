package project.spring.repo;
import project.spring.models.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Репозиторий PerformanceRepo - ребенок JPA репозитория
 */

public interface PerformanceRepo extends JpaRepository<Performance, Long> {
    /**
     * поиск спектаклей по названию
     * @param name - название спектакля
     * @return список спектаклей
     */
    List<Performance> findAllByName(String name);
    /**
     * поиск спектаклей по времени
     * @param time - время спекаткля
     * @return список спектаклей
     */
    List<Performance> findAllByTime(String time);
    /**
     * поиск спектакля по id
     * @param id - id  спектакля
     * @return спектакль
     */
    Performance findPerformanceById(Long id);
    /**
     * поиск спектакля по названию и времени
     * @param name - название спектакля
     * @param time - время спектакля
     * @return спектакль
     */
    Performance findPerformanceByNameAndTime(String name, String time);

}
