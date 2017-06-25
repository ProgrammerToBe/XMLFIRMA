package korenski.repository.soap;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import korenski.model.sifrarnici.Message;
import korenski.soap.fakture_model.Faktura;

@Repository
public interface FakturaRepository  extends CrudRepository<Faktura, Long> {
	
	public Faktura findOne(Long id);
	public Set<Faktura> findAll();
	public void delete(Long id);
	public Faktura save(Faktura faktura);
//	public Set<Faktura> findByTipAndByZatvorena(boolean tip, boolean zatvoren);
	public Set<Faktura> findByTipTrueAndZatvorenaFalse();

}
