package korenski.repository.xml_pomocni;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import korenski.model.sifrarnici.Activity;
import korenski.model.xml_pomocni.RegistrovanaPoslovna;

@Repository
public interface RegistrovanaPoslovnaRepository extends CrudRepository<RegistrovanaPoslovna,Long> {
	public RegistrovanaPoslovna save(RegistrovanaPoslovna poslovnaBanka);
	public RegistrovanaPoslovna findOne(Long id);
	public void delete(Long id);
	public RegistrovanaPoslovna  findRegistrovanaPoslovnaByPrveTri(String prveTri);
	public RegistrovanaPoslovna  findRegistrovanaPoslovnaByPort(int port);
	
}