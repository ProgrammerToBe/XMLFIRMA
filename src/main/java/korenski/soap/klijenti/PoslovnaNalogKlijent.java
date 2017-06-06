package korenski.soap.klijenti;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

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
//	 @Autowired
//	 private Jaxb2Marshaller jaxb2Marshaller;

	  public String posaljiNalog() {

		  System.out.println("Pravim nalog!");
		  
		  NalogRequest request = new NalogRequest();
		  NalogZaPrenos n = new NalogZaPrenos();
		  
		  
		  
	    ObjectFactory factory = new ObjectFactory();
	    
	    n.setHitno("Da");
		n.setPodaciODuzniku("Duznik1");
		n.setPodaciOPoveriocu("Poverilac");
		n.setSvrhaPlacanja("Svrha placanja 1");
		
		PodaciOPlacanju pop = new PodaciOPlacanju();
		pop.setIznos(new BigDecimal(100));
		pop.setSifraPlacanja("888");
		pop.setValuta("DIN");
		
		
		TFinansijskiPodaci fin = new TFinansijskiPodaci();
		fin.setBrojRacuna("123-7894546-97");
		fin.setModel("456789789");
		fin.setPozivNaBroj("4569879");
		
		
		pop.setFinansijskiPodaciDuznik(fin);
		
		n.setPodaciOPlacanju(pop);
		
		request.setNalog(n);
		
		System.out.println("Saljem nalog!");
		
		webServiceTemplate.setDefaultUri("http://localhost:8080/ws_poslovne/nalog");
		webServiceTemplate.setMarshaller(jaxb2Marshaller("korenski.soap.nalozi_model"));
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller("korenski.soap.nalozi_model"));
		   
		
		
	   NalogResponse nalog = (NalogResponse) webServiceTemplate.marshalSendAndReceive(request);

	   System.out.println("Stigao odgovor!");
	   
	    return nalog.getOdgovor();
	  }
	  
	  private Jaxb2Marshaller jaxb2Marshaller(String path) {

		    Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		    jaxb2Marshaller.setContextPath(path);
		    return jaxb2Marshaller;
	  }
}
