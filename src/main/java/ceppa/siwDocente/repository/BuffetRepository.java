package ceppa.siwDocente.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ceppa.siwDocente.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {

	@Query(value = "SELECT * FROM Buffet b WHERE b.chef_id = ?1", nativeQuery = true)
	Set<Buffet> findBuffetsByChefId(Long chefId);
	
}
