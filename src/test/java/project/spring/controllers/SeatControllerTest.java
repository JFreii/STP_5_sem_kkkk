package project.spring.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import project.spring.repo.HallRepo;
import project.spring.repo.SeatRepo;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SeatControllerTest {

    @Autowired
    public MockMvc mvc;



    @Autowired
    public HallRepo hallRepo;
    public SeatRepo seatRepo;

    /**
     * Проверка Get-запроса, размер массива мест
     *
     */
    @Test
    void getSeats() {
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/seats/all"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString();
                        JSONArray jsonArray = new JSONArray(body);
                        assertEquals(seatRepo.findAll().size(), jsonArray.length());

                    })
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка Get-запроса, вывода всех мест по залу
     */

    @Test
    void getseatsByHall() {
        String HallName = "левый зал";

        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/seats/hall="+ HallName))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> {
                String body = mvcResult.getResponse().getContentAsString();
                JSONObject jsonObject = new JSONObject(body);
                byte bytes[] = jsonObject.getString("name").getBytes("ISO-8859-1");
                String value = new String(bytes, "UTF-8");
                assertEquals(seatRepo.findAllByHall_Id(hallRepo.findHallByName(URLDecoder.decode(HallName, StandardCharsets.UTF_8)).getId()), value);

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}