package cours.iir4.livraison.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

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
	public Client getClient(@PathVariable long id) {
		
		return cs.getClient(id);
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