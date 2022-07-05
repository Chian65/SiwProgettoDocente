package ceppa.siwDocente.service;

import java.util.Set;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceppa.siwDocente.model.Chef;
import ceppa.siwDocente.model.Piatto;
import ceppa.siwDocente.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired private ChefRepository chefRepository;
	private PiattoService piattoService;

	public Set<Chef> getAllChefs() {
		Set<Chef> chefs = new HashSet<Chef>();
		for (Chef chef : chefRepository.findAll()) {
			chefs.add(chef);
		}
		return chefs;
	}
	
	public Chef getChefById(Long id) {
		return this.chefRepository.findById(id).get();
	}
		
	@Transactional
	public Chef saveChef(Chef chef) {
		return this.chefRepository.save(chef);
	}
	
	@Transactional
	public void deleteChefById(Long id) {
	Chef c = this.chefRepository.findById(id).get();
	for (Piatto p : c.getPiatti()) {
	this.piattoService.deletePiattoById(p.getId());
	}
	this.chefRepository.deleteById(id);
	}
	
}
