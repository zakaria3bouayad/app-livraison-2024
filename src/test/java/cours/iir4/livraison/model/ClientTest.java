package cours.iir4.livraison.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ClientTest {

	@Test
    public void testSetAndGetIdClient() {
        Client client = new Client();
        client.setIdClient(1L);
        assertEquals(1L, client.getIdClient(), "L'ID du client devrait être 1");
    }

    @Test
    public void testSetAndGetName() {
        Client client = new Client();
        client.setName("John Doe");
        assertEquals("John Doe", client.getName(), "Le nom du client devrait être John Doe");
    }

}
