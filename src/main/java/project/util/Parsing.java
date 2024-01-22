package project.util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.spring.models.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
/**
 * Класс Parsing
 * парсинг JSON - объекта
 *
 */
public class Parsing {
    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private ObservableList<Ticket> tickets = FXCollections.observableArrayList();
    /**
     * метод парсинга jsonObject
     * @throws IOException - ошибка соединения
     */
    public Parsing() throws IOException{

        URL url = new URL("http://localhost:8080/api/theater/tickets/all");
        URLConnection urlConnection = url.openConnection(); // предоставляем доступ к атрибутам url
        InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream()); //получение данных из  источника
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); //считывает текст
        String str = " ";
        /**
         * считываеи из url  построчно информацию и добавляем ее в строку,
         *  затем возвращаем строку уже со всей информацией, которая находится в url
         */
        while ((str = bufferedReader.readLine()) != null) {
            stringBuilder.append(str);
        }
        String response = stringBuilder.toString();
        bufferedReader.close();
        /**
         * создание json - объекта
         */

        try {
            JSONArray json = new JSONArray(response);
            for (int i =0; i<json.length(); i++) {
                Ticket tempTicket = new Ticket();
                tempTicket.setId(json.getJSONObject(i).getLong("id"));
                tempTicket.setPrice(json.getJSONObject(i).getInt("price"));
                tempTicket.setDate(json.getJSONObject(i).getString("date"));

                String clientString = json.getJSONObject(i).getString("client");
                JSONObject client = new JSONObject(clientString);
                Client tempClient = new Client();
                tempClient.setId(client.getLong("id"));
                tempClient.setFirstname(client.getString("firstname"));
                tempClient.setLastname(client.getString("lastname"));
                tempClient.setContact(client.getString("contact"));
                tempClient.setAge(client.getInt("age"));
                tempTicket.setClient(tempClient);

                String seatString = json.getJSONObject(i).getString("seat");
                JSONObject seat = new JSONObject(seatString);

                Seat tempSeat = new Seat();
                tempSeat.setId(seat.getLong("id"));
                tempSeat.setType(seat.getString("type"));
                tempSeat.setLocation(seat.getInt("location"));

                String hallString = seat.getString("hall");

                JSONObject hall = new JSONObject(hallString);

                Hall tempHall = new Hall();
                tempHall.setId(hall.getLong("id"));
                tempHall.setName(hall.getString("name"));

                String perfString = json.getJSONObject(i).getString("performance");
                JSONObject perf = new JSONObject(perfString);
                Performance tempPerformance = new Performance();
                tempPerformance.setId(perf.getLong("id"));
                tempPerformance.setName(perf.getString("name"));
                tempPerformance.setTimeofpremier(perf.getString("timeofpremier"));
                tempPerformance.setTimeofend(perf.getString("timeofend"));
                tempPerformance.setTime(perf.getString("time"));
                tempPerformance.setAgelimit(perf.getInt("agelimit"));
                tempSeat.setHall(tempHall);
                tempTicket.setSeat(tempSeat);
                tempTicket.setPerformance(tempPerformance);
                System.out.println(tempTicket);
                tickets.add(tempTicket);
                clients.add(tempTicket.getClient());
                System.out.println(clients);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод для вывода билетов
     * @return список билетов
     */
    public ObservableList<Ticket> getTickets(){
        return tickets;
    }

}
