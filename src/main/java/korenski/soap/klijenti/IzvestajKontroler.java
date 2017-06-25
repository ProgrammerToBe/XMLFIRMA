package korenski.soap.klijenti;

import java.math.BigInteger;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import korenski.repository.soap.IzvodRepository;
import korenski.repository.soap.PresekRepository;
import korenski.soap.izvestaji_model.Presek;
import korenski.soap.izvestaji_model.ZahtevZaIzvod;
import korenski.soap.klijenti.dto.IzvodDTO;

@Controller
public class IzvestajKontroler {
	
	@Autowired
	PoslovnaNalogKlijent poslovnaNalogKlijent;
	
	@Autowired
	PoslovnaIzvestajKlijent poslovnaIzvestajKlijent;
	
	@Autowired
	IzvodRepository izvodRepository;
	
	@Autowired
	PresekRepository presekRepository;
	
	@RequestMapping(
			value = "/special/posaljiZahtevZaIzvestaj",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Presek> PosaljiZahtevZaIzvestaj(@RequestBody IzvodDTO dto,  @Context HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("SALJEM zahtev za izvestaj!");
		
		System.out.println("Pravim zahtev!");
		
		ZahtevZaIzvod zahtev = new ZahtevZaIzvod();
		zahtev.setBrojRacuna(dto.getBrojRacuna());
		
		zahtev.setDatum(dto.getDatum());
		
		zahtev.setRedniBrojPreseka(new BigInteger(dto.getBrojPreseka()));
		
		Presek odg = poslovnaIzvestajKlijent.posaljiZahtevZaIzvestaj(zahtev);
			
		izvodRepository.save(zahtev);
		presekRepository.save(odg);
		
		System.out.println("Stigao odgovor!");
		
		System.out.println("Odgovor "+odg.getBrojPromenaNaTeret());
		
		return new ResponseEntity<Presek>( odg, HttpStatus.OK);
	}

}
