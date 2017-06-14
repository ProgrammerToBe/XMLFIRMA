package korenski.soap.klijenti;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import korenski.model.xml_pomocni.RegistrovanaPoslovna;
import korenski.repository.xml_pomocni.RegistrovanaPoslovnaRepository;
import korenski.soap.nalozi_model.NalogRequest;
import korenski.soap.nalozi_model.NalogResponse;
import korenski.soap.nalozi_model.NalogZaPrenos;
import korenski.soap.nalozi_model.NalogZaPrenos.PodaciOPlacanju;
import korenski.soap.nalozi_model.ObjectFactory;
import korenski.soap.nalozi_model.TFinansijskiPodaci;

@Component
public class PoslovnaNalogKlijent {

	 @Autowired
	  private WebServiceTemplate webServiceTemplate;
	 @Autowired
	 private RegistrovanaPoslovnaRepository poslovneRepository;

	  public String posaljiNalog(NalogZaPrenos n) {

		  System.out.println("Pravim nalog!");
		  
		  NalogRequest request = new NalogRequest();
//		  NalogZaPrenos n = new NalogZaPrenos();
//		  
//		  
//		  
//	    ObjectFactory factory = new ObjectFactory();
//	    
//	    n.setHitno("Da");
//		n.setPrimalacPoverilac("Poverilac1");
//		n.setDuznikNalogodavac("Duznzik1");
//		n.setSvrhaPlacanja("Svrha placanja 1");
//		
//		
//		PodaciOPlacanju pop = new PodaciOPlacanju();
//		pop.setIznos(new BigDecimal(250000.3));
////		pop.setDatumNaloga(new Date());
////		pop.setDatumValute(new Date());
//		pop.setOznakaValute("DIN");
//		
//		
//		TFinansijskiPodaci finDuznik = new TFinansijskiPodaci();
//		finDuznik.setBrojRacuna("123-7894546-97");
//		finDuznik.setModel("456789789");
//		finDuznik.setPozivNaBroj("4569879");
//		
//		
//		TFinansijskiPodaci finPoverilac = new TFinansijskiPodaci();
//		finPoverilac.setBrojRacuna("124-7894546-97");
//		finPoverilac.setModel("45669789");
//		finPoverilac.setPozivNaBroj("4562279");
//		
//		
//		pop.setFinansijskiPodaciDuznik(finDuznik);
//		pop.setFinansijskiPodaciPoverilac(finPoverilac);
//		
//		n.setPodaciOPlacanju(pop);
		
		request.setNalog(n);
		
		System.out.println("Saljem nalog!");
		
		String[] parts =  n.getPodaciOPlacanju().getFinansijskiPodaciPoverilac().getBrojRacuna().split("-");
		String prveTri = parts[0];
		RegistrovanaPoslovna registrovana = poslovneRepository.findRegistrovanaPoslovnaByPrveTri(prveTri);
		
		if(registrovana == null){
			return "Nije registrovana banka!";
		}
		
		int port = registrovana.getPort();
		
		
		webServiceTemplate.setDefaultUri("http://localhost:"+port+"/ws_poslovne/nalog");
		webServiceTemplate.setMarshaller(jaxb2Marshaller("korenski.soap.nalozi_model"));
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller("korenski.soap.nalozi_model"));
		   
		
		
	   NalogResponse nalog;
		try {
			nalog = (NalogResponse) webServiceTemplate.marshalSendAndReceive(request);
		} catch (Exception e) {
			return "Neuspesno uspostavljanje veze!";
		}

	   System.out.println("Stigao odgovor!");
	   
	    return nalog.getText();
	  }
	  
	  private Jaxb2Marshaller jaxb2Marshaller(String path) {

		    Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		    jaxb2Marshaller.setContextPath(path);
		    return jaxb2Marshaller;
	  }
}
