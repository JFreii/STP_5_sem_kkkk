package project.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import project.spring.models.Hall;
import project.spring.repo.HallRepo;
import project.spring.repo.PerformanceRepo;

import java.net.URLDecoder;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class HallControllerTest {
    @Autowired
    public MockMvc mvc;



    @Autowired
    public HallRepo hallRepo;
    public PerformanceRepo performanceRepo;


    /**
     * Проверка Get-запроса, размер массива залов
     *
     */
    @Test
    public void getHalls() {
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/halls/all"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString();
                        JSONArray jsonArray = new JSONArray(body);
                        assertEquals(hallRepo.findAll().size(), jsonArray.length());

                    })
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка Get-запроса, поиск зала по названию спектакля
     */

    @Test
    public void getHallOnPerf() {
        String PerfName = "Отцы и дети";

        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/halls/perfName="+PerfName))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> {
                String body = mvcResult.getResponse().getContentAsString();
                JSONObject jsonObject = new JSONObject(body);
                byte bytes[] = jsonObject.getString("name").getBytes("ISO-8859-1");
                String value = new String(bytes, "UTF-8");
                assertEquals(hallRepo.findHallById(this.performanceRepo.findAllByName(URLDecoder.decode(PerfName)).
                        get(0).getHall().getId()).getName(), value);

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * проверка post- запроса для создания зала
     * @throws Exception
     */

    @Test
    public void postHall() throws Exception {
        String Name = "левый зал";
        Hall hall = new Hall(Name);

        ObjectMapper mapper = new ObjectMapper();
        String StringHall = mapper.writeValueAsString(hall);
        this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/theater/halls/posthalls")
                .content(StringHall)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

    }

}