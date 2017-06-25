package korenski.repository.sifrarnici;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import korenski.model.sifrarnici.Artikl;

@Repository
public interface ArtiklRepository extends CrudRepository<Artikl, Long>{
	
	public Set<Artikl> findAll();
	
}
