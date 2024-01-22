package project.spring.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import project.spring.models.Performance;
import project.spring.repo.HallRepo;
import project.spring.repo.PerformanceRepo;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PerformanceControllerTest {
    @Autowired
    public MockMvc mvc;



    @Autowired
    public HallRepo hallRepo;
    public PerformanceRepo performanceRepo;


    /**
     * Проверка Get-запроса, размер массива  спектакля
     */
    @Test
    void getPerfs() {
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/perfs/all"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString();
                        JSONArray jsonArray = new JSONArray(body);
                        assertEquals(performanceRepo.findAll().size(), jsonArray.length());

                    })
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка Get-запроса, поиск спектакля по времени
     */

    @Test
    void getPerfByTime() {
        String time = "10:00";
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/perfs/time="+time))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> {
                String body = mvcResult.getResponse().getContentAsString();
                JSONObject jsonObject = new JSONObject(body);
                assertEquals(performanceRepo.findAllByTime(time), jsonObject.getString("time"));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка Get-запроса, поиск спектакля по названию
     */


    @Test
    void getTimesOnPerf() {
        String name = "красавица и чудовище";
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/perfs/name="+name))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> {
                String body = mvcResult.getResponse().getContentAsString();
                JSONObject jsonObject = new JSONObject(body);
                byte bytes[] = jsonObject.getString("name").getBytes("ISO-8859-1");
                String value = new String(bytes, "UTF-8");
                assertEquals(performanceRepo.findAllByName(value), jsonObject.getString("name"));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     *  проверка post- запроса для создания  спектакля
     */


    @Test
    public void postPerformance(){
        String Name = "Отцы и дети";
        String Prem = "2021-03-02";
        String End = "2021-06-07";
        String Time = "10:00";
        Integer Age = 12;

        Performance performance = new Performance(Name, Prem, End,Time,Age);
        ObjectMapper mapper = new ObjectMapper();
        String StringPer = null;
        try {
            StringPer = mapper.writeValueAsString(performance);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/theater/perf/postperf")
                    .content(StringPer)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}