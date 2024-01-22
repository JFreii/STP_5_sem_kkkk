package project.spring.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import project.spring.models.Client;
import project.spring.repo.ClientRepo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    public MockMvc mvc;


    @Autowired
    public ClientRepo clientRepo;



    /**
     * Проверка Get-запроса, размер массива клиента
     *
     */

    @Test
    public void getClients(){
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/clients/all"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString();
                        JSONArray jsonArray = new JSONArray(body);
                        assertEquals(clientRepo.findAll().size(), jsonArray.length());

                    })
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка Get-запроса, поиск клиента по id
     */

    @Test
    public void getClient(){
        Long ClientId = Long.valueOf(1);
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/clients/"+ClientId))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> {
                String body = mvcResult.getResponse().getContentAsString();
                JSONArray jsonArray = new JSONArray(body);
                assertEquals(clientRepo.findClientById(ClientId), jsonArray.getLong(Integer.parseInt("id")));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * проверка post- запроса для создания клиента
     * @throws Exception
     */

    @Test
    public void postClient() throws Exception {
        String FirstName = "Вася";
        String LastName = "Иванов";
        String Contact = "+79457008990";
        Integer Age = 34;

        Client client = new Client(FirstName, LastName, Contact, Age);
        ObjectMapper mapper = new ObjectMapper();
        String StringClient = mapper.writeValueAsString(client);

        this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/theater/clients/postclient")
                .content(StringClient)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

    }

    /**
     * проверка put - запроса для обловления данных о клиенте
     */

    @Test
    public void updateClient(){
        String FirstName = "Ирина";
        String LastName = "Киселева";
        String Contact = "+79457008990";
        Integer Age = 18;

        Client client = new Client(FirstName, LastName, Contact, Age);
        ObjectMapper mapper = new ObjectMapper();
        String StringClient = null;
        try {
            StringClient = mapper.writeValueAsString(client);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            this.mvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/api/theater/clients/updateclient")
                    .content(StringClient)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
