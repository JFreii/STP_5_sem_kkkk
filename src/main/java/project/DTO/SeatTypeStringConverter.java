package project.DTO;

import javafx.util.StringConverter;

/**
 * Конвертор для отобращения объекта  ChoiceBox'а
 * Реализующий два метода абстрактного класса StringConverter
 * Type
 */

public class SeatTypeStringConverter extends StringConverter<SeatDTO> {

    /**
     * конвертация в строку
     * @param seatDTO - объект класа SeatDTO
     * @return строковое представление зоны
     */
    @Override
    public String toString(SeatDTO seatDTO) {
        return seatDTO.getType();
    }

    /**
     *
     * @param s -строка
     * @return null
     */

    @Override
    public SeatDTO fromString(String s) {
        return null;
    }
}
