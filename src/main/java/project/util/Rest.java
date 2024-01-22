package project.util;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * класс Rest
 * методы для осуществления rest-запросов с сервера на javafx
 */
public class Rest {

    /**
     * delete запрос с сервера
     * @param link - ссылка, поо которой совершается delete-запрос
     * @throws IOException - ошибка соединения
     */
    public void DeleteRest(String link) throws IOException {
        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Content-Type", "utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestMethod("DELETE");
        InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream()); //получение данных из  источника
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); //считывает текст
        String str = " ";

        /**
         * считываеи из url  построчно информацию и добавляем ее в строку,
         * затем возвращаем строку уже со всей информацией, которая находится в url
         */

        while ((str = bufferedReader.readLine()) != null) {
            stringBuilder.append(str);
        }
        String response = stringBuilder.toString();
        bufferedReader.close();
    }

    /**
     * post запрос с сервера
     * @param link -  ссылка, поо которой совершается post-запрос
     * @param jsonObject - объект json
     * @return вывод с post - запроса
     * @throws IOException - ошибка соединеия
     */
    public String PostRest(String link, JSONObject jsonObject) throws IOException{
        System.out.println("+++++++++++++++++ "+URLEncoder.encode(jsonObject.toString(),StandardCharsets.UTF_8));
        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Content-Type","application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        System.out.println("вывод объекта из рест "+jsonObject);
        StringBuilder stringBuilder = new StringBuilder();
        String str = " ";
        try(OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream(),StandardCharsets.UTF_8)
        )){
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str);
            }
        }
        String response = stringBuilder.toString();
        System.out.println("post вывод " + response);
        return response;
    }

    /**
     * get запрос с сервера
     * @param link -  ссылка, поо которой совершается get-запрос
     * @return вывод с get - запроса
     * @throws IOException - ошибка соединения
     */
    public String GetRest(String link) throws IOException{
        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Content-Type", "utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestMethod("GET");
        InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());  //получение данных из  источника
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); //считывает текст
        String str = " ";
        /**
         * считываеи из url  построчно информацию и добавляем ее в строку,
         * затем возвращаем строку уже со всей информацией, которая находится в url
         */
        while ((str = bufferedReader.readLine()) != null) {
            stringBuilder.append(str);

        }
        String response = stringBuilder.toString();
        bufferedReader.close();
        System.out.println("гет вывод " + response);
        return response;
    }

    /**
     * put запрос с сервера
     * @param link ссылка, поо которой совершается put-запрос
     * @param jsonObject - объект json
     * @return вывод с put - запроса
     * @throws IOException - ошибка соединения
     */
    public String PutRest(String link, JSONObject jsonObject) throws IOException{

        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Content-Type","application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setDoOutput(true);
        System.out.println("вывод объекта из рест "+jsonObject);
        StringBuilder stringBuilder = new StringBuilder();
        String str = " ";
        try(OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream(),StandardCharsets.UTF_8)
        )){
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str);
            }
        }
        String response = stringBuilder.toString();
        System.out.println("put вывод " + response);
        return response;
    }






}
