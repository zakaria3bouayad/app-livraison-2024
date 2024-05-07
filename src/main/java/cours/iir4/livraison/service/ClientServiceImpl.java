package cours.iir4.livraison.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cours.iir4.livraison.model.Client;
import cours.iir4.livraison.repository.ClientRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;
@Service
public class ClientServiceImpl implements ClientService {
	private Argon2 arg= Argon2Factory.create();

	private ClientRepository cr;
	
	public ClientServiceImpl(ClientRepository cr ) {
		
		this.cr=cr;
	}

	@Override
	public List<Client> getClients() {
		
		return cr.findAll();
	}

	@Override
	public Client getClient(long idClient) {
		
		return cr.findById(idClient).get();
	}

	@Override
	public Client addClient(Client nouvClient) {
		String pass= arg.hash(2, 65536, 1, nouvClient.getPassword().toCharArray());
		nouvClient.setPassword(pass);
		cr.save(nouvClient);
		
		return nouvClient;
	}

	@Override
	public void deleteClient(long idClient) {
		cr.delete(getClient(idClient));
		
	}

	@Override
	public void modifierClient(Client nouvClient) {
		Optional<Client> client= cr.findById(nouvClient.getIdClient());
		if (client.isPresent()) {
			Client ancCl= client.get();
			ancCl.setEmail(nouvClient.getEmail());
			ancCl.setName(nouvClient.getName());
			String pass= arg.hash(2, 65536, 1, nouvClient.getPassword().toCharArray());
			ancCl.setPassword(pass);
			cr.save(ancCl);
		}
	}

}
