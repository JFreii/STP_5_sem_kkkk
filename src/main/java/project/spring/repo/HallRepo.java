package project.spring.repo;
import project.spring.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Репозиторий HallRepo - ребенок JPA репозитория
 */
public interface HallRepo extends JpaRepository<Hall, Long> {
    /**
     * поиск зала по названию
     * @param name - название зала
     * @return зал
     */
    Hall findHallByName(String name);
    /**
     * поиск зала по id
     * @param id - id зала
     * @return зал
     */
    Hall findHallById(Long id);
}




