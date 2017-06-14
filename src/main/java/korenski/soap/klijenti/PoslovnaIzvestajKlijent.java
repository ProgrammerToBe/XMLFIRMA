package korenski.soap.klijenti;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import korenski.model.xml_pomocni.RegistrovanaPoslovna;
import korenski.repository.xml_pomocni.RegistrovanaPoslovnaRepository;
import korenski.soap.izvestaji_model.IzvestajRequest;
import korenski.soap.izvestaji_model.IzvestajResponse;
import korenski.soap.izvestaji_model.ZahtevZaIzvod;
import korenski.soap.nalozi_model.ObjectFactory;

@Component
public class PoslovnaIzvestajKlijent {

	@Autowired
	private WebServiceTemplate webServiceTemplate;
	@Autowired
	private RegistrovanaPoslovnaRepository poslovneRepository;

	public String posaljiZahtevZaIzvestaj(ZahtevZaIzvod zahtev) {

		

		IzvestajRequest request = new IzvestajRequest();

//		ZahtevZaIzvod zahtev = new ZahtevZaIzvod();
//		zahtev.setBrojRacuna("123-7894546-97");

		String[] parts = zahtev.getBrojRacuna().split("-");
		String prveTri = parts[0];
		RegistrovanaPoslovna registrovana = poslovneRepository.findRegistrovanaPoslovnaByPrveTri(prveTri);

		if (registrovana == null) {
			return "Nije registrovana banka!";
		}

		int port = registrovana.getPort();

		request.setZahtev(zahtev);

		ObjectFactory factory = new ObjectFactory();

		System.out.println("Saljem zahtev za izvestaj!");
		webServiceTemplate.setDefaultUri("http://localhost:" + port + "/ws_poslovne/izvestaj");
		webServiceTemplate.setMarshaller(jaxb2Marshaller("korenski.soap.izvestaji_model"));
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller("korenski.soap.izvestaji_model"));

		IzvestajResponse odgovor = null;
		
		try {
			odgovor = (IzvestajResponse) webServiceTemplate.marshalSendAndReceive(request);
		} catch (Exception e) {
			System.out.println("Neuspesno uspostavljanje veze!");
			return null;
		}

		System.out.println("Stigao odgovor!");

		if(odgovor.getPresek()	 == null){
			return "Odgovor je NULL";
		}
		
		return odgovor.getPresek().getBrojRacuna();
	}

	private Jaxb2Marshaller jaxb2Marshaller(String path) {

		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath(path);
		return jaxb2Marshaller;
	}
}
