package project.DTO;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * SeatDTO - подобный объект Seat, который хранит данных
 */
public class SeatDTO  {

    private Long id;
    private String type;
    private Integer location;
    private HallDTO hall;

    /**
     * Задает переменные SeatDTO
     * @param id - id
     * @param type - тип места
     * @param location - место
     * @param hall - зал
     */

    protected SeatDTO(Long id, String type, Integer location, HallDTO hall){
        this.id = id;
        this.type = type;
        this.location = location;
        this.hall = hall;
    }

    /**
     * Возврает объект класса SeatDTO
     * @param jsonObject -  серверное Json представление объекта Seat
     * @return SeatDTO
     * @throws JSONException - json ошибка
     */

    static public SeatDTO instanceOf(JSONObject jsonObject) throws JSONException {
        return new SeatDTO(
                jsonObject.getLong("id"),
                jsonObject.getString("type"),
                jsonObject.getInt("location"),
                HallDTO.instanceOf(jsonObject.getJSONObject("hall"))

        );
    }

    /**
     * метод класса SeatDTO
     * @return возвращает id
     */

    public Long getId() {
        return id;
    }



    /**
     * метод класса SeatDTO, который задает id места
     * @param id - id места
     */

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * метод класса SeatDTO
     * @return возвращает зону
     */

    public String getType() {
        return type;
    }



    /**
     *  метод класса SeatDTO, который задает зону места
     * @param type - тип места
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     * метод класса SeatDTO
     * @return возвращает место
     */


    public Integer getLocation() {
        return location;
    }

    /**
     * метод класса SeatDTO
     * @return возвращает  зал, в котором находится место
     */

    public HallDTO getHall() {
        return hall;
    }



    /**
     * метод класса SeatDTO, который задает зал, в котором находится место
     * @param hall - зал
     */

    public void setHall(HallDTO hall) {
        this.hall = hall;
    }


}
