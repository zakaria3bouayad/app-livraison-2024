package cours.iir4.livraison.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cours.iir4.livraison.model.Client;
import cours.iir4.livraison.service.ClientService;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    public void testGetClients() throws Exception {
        Client client1 = new Client();
        client1.setIdClient(1L);
        client1.setName("John Doe");
        client1.setEmail("john@example.com");
        client1.setPassword("123456");

        Client client2 = new Client();
        client2.setIdClient(2L);
        client2.setName("Jane Doe");
        client2.setEmail("jane@example.com");
        client2.setPassword("654321");

        List<Client> allClients = Arrays.asList(client1, client2);
        when(clientService.getClients()).thenReturn(allClients);

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[1].name", is("Jane Doe")));
    }

    @Test
    public void testGetClientById() throws Exception {
        Client client = new Client();
        client.setIdClient(1L);
        client.setName("John Doe");
        client.setEmail("john@example.com");
        client.setPassword("123456");

        when(clientService.getClient(1L)).thenReturn(client);

        mockMvc.perform(get("/clients/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john@example.com")));
    }

    @Test
    public void testAddClient() throws Exception {
        Client client = new Client();
        client.setName("New Client");
        client.setEmail("newclient@example.com");
        client.setPassword("new123456");

        when(clientService.addClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("New Client")));
    }

    @Test
    public void testModifClient() throws Exception {
        Client client = new Client();
        client.setIdClient(1L);
        client.setName("Updated Name");
        client.setEmail("update@example.com");
        client.setPassword("updated123");

        doNothing().when(clientService).modifierClient(any(Client.class));

        mockMvc.perform(put("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(client)))
                .andExpect(status().isOk());
    }
}
