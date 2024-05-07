package cours.iir4.livraison.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cours.iir4.livraison.model.Client;
import cours.iir4.livraison.service.ClientService;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientController {
	
	private ClientService cs;
	
	public ClientController(ClientService cs) {
		this.cs=cs;
	}
	
	@GetMapping
	public List<Client> getClients() {
		
		return cs.getClients();
	}
	
	@GetMapping("/{id}")
	public Client getClient(long idClient) {
		
		return cs.getClient(idClient);
	}
	
	
	@PostMapping
	public Client addClient(Client cl) {
		return cs.addClient(cl);
	}
	
	@PutMapping
	public void modifClient(Client cl) {
		cs.modifierClient(cl);
	}
	

}