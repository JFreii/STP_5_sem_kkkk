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
import project.spring.repo.TicketRepo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TicketControllerTest {

    @Autowired
    public MockMvc mvc;



    @Autowired
    public HallRepo hallRepo;
    public TicketRepo ticketRepo;


    /**
     * Проверка Get-запроса, поиск билета по id
     */

    @Test
    void getTicket() {
        Long TicketId = Long.valueOf(1);
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/tickets/"+TicketId))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> {
                String body = mvcResult.getResponse().getContentAsString();
                JSONArray jsonArray = new JSONArray(body);
                assertEquals(ticketRepo.findTicketById(TicketId), jsonArray.getLong(Integer.parseInt("id")));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка Get-запроса, размер массива билета
     */

    @Test
    void getTickets() {
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/tickets/all"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString();
                        JSONArray jsonArray = new JSONArray(body);
                        assertEquals(ticketRepo.findAll().size(), jsonArray.length());

                    })
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка Get-запроса, нахождение билета по названию спектакля
     */

    @Test
    void ticketOfPerName() {
        String PerfName = "Отцы и дети";
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/tickets/perName="+PerfName))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> {
                String body = mvcResult.getResponse().getContentAsString();
                JSONObject jsonObject = new JSONObject(body);
                byte bytes[] = jsonObject.getString("name").getBytes("ISO-8859-1");
                String value = new String(bytes, "UTF-8");
                assertEquals(ticketRepo.findTicketByPerformance_Name(PerfName), value);

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}