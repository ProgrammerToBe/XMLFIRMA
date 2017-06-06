package korenski.soap.klijenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import korenski.soap.nalozi_model.ObjectFactory;

@Component
public class PoslovnaIzvestajKlijent {

	 @Autowired
	  private WebServiceTemplate webServiceTemplate;

	  public String posaljiZahtevZaIzvestaj() {

		  System.out.println("Pravim zahtev!");
		  
//		  IzvestajRequest request = new IzvestajRequest();
//		  request.setZahtev("Zahtev broj 1.1");
//		  
//		  
//		  ObjectFactory factory = new ObjectFactory();
//	   
//		
//		  System.out.println("Saljem zahtev za izvestaj!");
//		webServiceTemplate.setDefaultUri("http://localhost:8080/ws_poslovne/izvestaj");
//		webServiceTemplate.setMarshaller(jaxb2Marshaller("korenski.soap.izvestaji_model"));
//		webServiceTemplate.setUnmarshaller(jaxb2Marshaller("korenski.soap.izvestaji_model"));
//		   
//		
//		
//	   IzvestajResponse nalog = (IzvestajResponse) webServiceTemplate.marshalSendAndReceive(request);
//
//	   System.out.println("Stigao odgovor!");
//	   
//	    return nalog.getOdgovor();
		  return null;
	  }
	  
	  private Jaxb2Marshaller jaxb2Marshaller(String path) {

		    Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		    jaxb2Marshaller.setContextPath(path);
		    return jaxb2Marshaller;
	  }
}
