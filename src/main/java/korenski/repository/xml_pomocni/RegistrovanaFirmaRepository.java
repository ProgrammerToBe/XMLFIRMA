package korenski.repository.xml_pomocni;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import korenski.model.xml_pomocni.RegistrovanaFirma;

@Repository
public interface RegistrovanaFirmaRepository extends CrudRepository<RegistrovanaFirma,Long> {
	public RegistrovanaFirma save(RegistrovanaFirma firma);
	public RegistrovanaFirma findOne(Long id);
	public Set<RegistrovanaFirma> findAll();
	public void delete(Long id);
	public RegistrovanaFirma  findRegistrovanaFirmaByPib(String pib);
	public RegistrovanaFirma  findRegistrovanaFirmaByPort(int port);
	
}