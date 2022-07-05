package ceppa.siwDocente.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceppa.siwDocente.model.Utente;
import ceppa.siwDocente.repository.UtenteRepository;

@Service
public class UtenteService { 

	@Autowired UtenteRepository utenteRepository;
	
	public Utente getUtente(Long id) {
		Optional<Utente> utenteOpt = this.utenteRepository.findById(id);
		if (utenteOpt.isPresent()) return utenteOpt.get();
		return null;
	}
	
	@Transactional
	public Utente saveUtente(Utente utente) {
		return this.utenteRepository.save(utente);
	}
	
    @Transactional
    public List<Utente> getAllUtenti() {
        List<Utente> result = new ArrayList<>();
        Iterable<Utente> iterable = this.utenteRepository.findAll();
        for(Utente utente : iterable)
            result.add(utente);
        return result;
    }
}
