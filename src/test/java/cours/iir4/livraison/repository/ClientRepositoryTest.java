package cours.iir4.livraison.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import cours.iir4.livraison.model.Client;


//@TestPropertySource(locations = "classpath:application-test.properties")
// DataJpaTest Utilise une base de données en mémoire pour les tests.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DataJpaTest
class ClientRepositoryTest {

	 @Autowired
	    private TestEntityManager entityManager;

	    @Autowired
	    private ClientRepository clientRepository;

	    @Test
	    public void testFindById() {
	        // Given
	        Client client = new Client();
	        client.setName("John Doe");
	        client.setEmail("john.doe@example.com");
	        client.setPassword("password123");
	        client = entityManager.persistFlushFind(client);

	        // When
	        Optional<Client> found = clientRepository.findById(client.getIdClient());

	        // Then
	        assertThat(found.isPresent()).isTrue();
	        assertThat(found.get().getName()).isEqualTo(client.getName());
	    }

	    @Test
	    public void testSave() {
	        // Given
	        Client newClient = new Client();
	        newClient.setName("Jane Doe");
	        newClient.setEmail("jane.doe@example.com");
	        newClient.setPassword("securepassword");

	        // When
	        Client savedClient = clientRepository.save(newClient);

	        // Then
	        assertThat(savedClient).isNotNull();
	        assertThat(savedClient.getIdClient()).isNotNull();
	        assertThat(savedClient.getName()).isEqualTo(newClient.getName());
	    }
}
