package project.DTO;
import javafx.util.StringConverter;
/**
 * Конвертор для отобращения объекта  ChoiceBox'а
 * Реализующий два метода абстрактного класса StringConverter
 * Location
 */
public class SeatLocationStringConverter extends StringConverter<SeatDTO> {
    /**
     *  конвертация в строку
     * @param seatDTO - объект класса SeatDTO
     * @return строковое значение места
     */
    @Override
    public String toString(SeatDTO seatDTO) {
        return seatDTO.getLocation().toString();
    }
    /**
     * @param s -строка
     * @return null
     */
    @Override
    public SeatDTO fromString(String s) {
        return null;
    }
}
