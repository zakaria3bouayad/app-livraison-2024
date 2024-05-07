package cours.iir4.livraison.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cours.iir4.livraison.model.Client;
import cours.iir4.livraison.repository.ClientRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

	@Mock
	private ClientRepository clientRepository;

	private Argon2 argon2 = Argon2Factory.create();

	@InjectMocks
	private ClientServiceImpl clientService;

	@Test
	public void testGetClients() {
		Client client1 = new Client();
		client1.setName("John Doe");
		Client client2 = new Client();
		client2.setName("Jane Doe");
		when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

		List<Client> clients = clientService.getClients();

		assertNotNull(clients);
		assertEquals(2, clients.size());
		verify(clientRepository).findAll();
	}

	@Test
	public void testGetClient() {
		Client client = new Client();
		client.setIdClient(1L);
		when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

		Client found = clientService.getClient(1L);

		assertNotNull(found);
		assertEquals(1L, found.getIdClient());
		verify(clientRepository).findById(1L);
	}

	@Test
	public void testAddClient() {
		Client client = new Client();
		client.setPassword("password");

		Client savedClient = clientService.addClient(client);
		assertNotNull(savedClient);
		boolean isCorrect = argon2.verify(savedClient.getPassword(), "password".toCharArray());
		assertEquals(true, isCorrect);

	}

	@Test
	public void testDeleteClient() {
		Client client = new Client();
		client.setIdClient(1L);
		when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
		doNothing().when(clientRepository).delete(any(Client.class));
		clientService.deleteClient(1L);
		verify(clientRepository).delete(client);
		verify(clientRepository).findById(1L);
	}

}
