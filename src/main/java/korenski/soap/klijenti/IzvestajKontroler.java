package korenski.soap.klijenti;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import korenski.soap.izvestaji_model.ZahtevZaIzvod;

@Controller
public class IzvestajKontroler {
	
	@Autowired
	PoslovnaNalogKlijent poslovnaNalogKlijent;
	
	@Autowired
	PoslovnaIzvestajKlijent poslovnaIzvestajKlijent;
	
	@RequestMapping(
			value = "/special/posaljiZahtevZaIzvestaj/{datum}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> PosaljiZahtevZaIzvestaj(@PathVariable("datum") Date datum,  @Context HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("SALJEM zahtev za izvestaj!");
		
		System.out.println("Pravim zahtev!");
		
		ZahtevZaIzvod zahtev = new ZahtevZaIzvod();
		zahtev.setBrojRacuna("123-7894546-97");
		
		GregorianCalendar c1 = new GregorianCalendar();
		c1.setTime(datum);
		XMLGregorianCalendar datumI = DatatypeFactory.newInstance().newXMLGregorianCalendar(c1);
		
		zahtev.setDatum(datumI);
		
		String odg = poslovnaIzvestajKlijent.posaljiZahtevZaIzvestaj(zahtev);
				
		System.out.println("Stigao odgovor!");
		
		System.out.println("Odgovor "+odg);
		
		return new ResponseEntity<String>( "Sve ok", HttpStatus.OK);
	}

}
