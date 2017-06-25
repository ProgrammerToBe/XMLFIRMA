package korenski.controller.firma_to_firma;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import korenski.model.dto.ArtiklDTO;
import korenski.model.xml_pomocni.RegistrovanaFirma;
import korenski.model.xml_pomocni.dto.FakturaDTO;
import korenski.model.xml_pomocni.dto.Odgovor;
import korenski.repository.soap.FakturaRepository;
import korenski.repository.xml_pomocni.RegistrovanaFirmaRepository;
import korenski.soap.fakture_model.Faktura;
import korenski.soap.fakture_model.Faktura.Stavke;
import korenski.soap.fakture_model.StavkaFakture;
import korenski.soap.fakture_model.TFirma;
import korenski.soap.klijenti.PoslovnaNalogKlijent;
import korenski.soap.nalozi_model.NalogZaPrenos;
import korenski.soap.nalozi_model.NalogZaPrenos.PodaciOPlacanju;
import korenski.soap.nalozi_model.TFinansijskiPodaci;

@Controller
public class KomunikacijaFirmiKontroler {
	
	@Autowired
	RegistrovanaFirmaRepository firmaRepository;
	@Autowired
	PoslovnaNalogKlijent poslovnaNalogKlijent;
	
	@Autowired
	FakturaRepository fakturaRepository;
	
	@RequestMapping(
			value = "/special/posaljiFakturu",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Odgovor> posaljiFakturu(@RequestBody FakturaDTO fakturaDTO, @Context HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("Saljem fakturu !");
		
		
		
		Faktura faktura = new Faktura();
		
		faktura.setBrojFakture(String.valueOf(fakturaDTO.getBrojFakture()));
		
//		GregorianCalendar c1 = new GregorianCalendar();
//		c1.setTime(fakturaDTO.getDatumFakture());
//		XMLGregorianCalendar datumF = DatatypeFactory.newInstance().newXMLGregorianCalendar(c1);
//		
//		
//		GregorianCalendar c2 = new GregorianCalendar();
//		c2.setTime(fakturaDTO.getDatumValute());
//		XMLGregorianCalendar datumV = DatatypeFactory.newInstance().newXMLGregorianCalendar(c2);
//		
		Date d = new Date();
		faktura.setIDPoruke(d.toString()+fakturaDTO.getPibKupca() + fakturaDTO.getPibDobavljaca());
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date datum = sdf.parse(fakturaDTO.getDatumFakture().toString());
		Date datumV = sdf.parse(fakturaDTO.getDatumValute().toString());
		
		String formattedTimeDatumFakture = output.format(datum);
		String formattedTimeDatumValute = output.format(datumV);
		
		
//		System.out.println(krajnji);
		
		Date datumFakture = sdf.parse(sdf.format(fakturaDTO.getDatumFakture()));
		Date datumValute = sdf.parse(sdf.format(fakturaDTO.getDatumValute()));
		
		
		faktura.setDatumFakture(datumFakture);
		faktura.setDatumValute(datumValute);
		
		
		double zaUplatu = fakturaDTO.getVrednostRobe()+fakturaDTO.getVrednostUsluge()-fakturaDTO.getUkupanRabat()-fakturaDTO.getUkupanPorez();
		
		faktura.setIznosZaUplatu(new BigDecimal(zaUplatu));
		faktura.setOznakaValute(fakturaDTO.getOznakaValute());
		
		faktura.setUkupanPorez(new BigDecimal(fakturaDTO.getUkupanPorez()));
		faktura.setUkupanRabat(new BigDecimal(fakturaDTO.getUkupanRabat()));
		
		faktura.setUkupnoRobaIUsluge(new BigDecimal(fakturaDTO.getVrednostRobe()+fakturaDTO.getVrednostUsluge()));
		
		faktura.setUplataNaRacun(fakturaDTO.getRacunZaUplatu());
		
		faktura.setVrednostRobe(new BigDecimal(fakturaDTO.getVrednostRobe()));
		faktura.setVrednostUsluga(new BigDecimal(fakturaDTO.getVrednostUsluge()));
		
		
	
		TFirma dobavljac = new TFirma();
		dobavljac.setAdresa("Moja adresa");
		dobavljac.setNaziv("Moj naziv");
		dobavljac.setPIB(String.valueOf(fakturaDTO.getPibDobavljaca()));
		
		RegistrovanaFirma regSar = firmaRepository.findRegistrovanaFirmaByPib(String.valueOf(fakturaDTO.getPibKupca()));
		
		TFirma kupac = new TFirma();
		kupac.setAdresa(regSar.getAdresa());
		kupac.setNaziv(regSar.getNaziv());
		kupac.setPIB(regSar.getPib());
		
		//faktura.setStavke();
		
		
		
		faktura.setKupac(kupac);
		faktura.setDobavljac(dobavljac);
		
		StavkaFakture stavka;
		Stavke stavke = new Stavke();
		
		double glavnaGlavnica = 0;
		double glavniRabat = 0;
		double glavniPorez = 0;
		
		int cnt  = 1;
		for(ArtiklDTO artikl : fakturaDTO.getArtikli()){
			stavka = new StavkaFakture();
			
			stavka.setJedinicaMere(artikl.getJedinicaMjere());
			stavka.setJedinicnaCena(new BigDecimal(artikl.getJedinicnaCjena()));
			stavka.setKolicina(new BigInteger(String.valueOf(artikl.getKolicina())));
			stavka.setNazivRobeIliUsluge(artikl.getNaziv());
			stavka.setProcenatRabata(new BigDecimal(artikl.getPopust()));
			stavka.setRedniBroj(new BigInteger(String.valueOf(cnt)));
			
			
			double ukupanPopust = artikl.getKolicina()*artikl.getJedinicnaCjena()*artikl.getPopust()/100;
			double ukupanPorez = (artikl.getKolicina()*artikl.getJedinicnaCjena()-ukupanPopust)*artikl.getPdv()/100 ;
			double glavnica = artikl.getKolicina()*artikl.getJedinicnaCjena();
			
			glavniRabat += ukupanPopust;
			glavniPorez += ukupanPorez;
			glavnaGlavnica += glavnica;
			
			stavka.setUkupanPorez(new BigDecimal(ukupanPorez));
			
			stavka.setIznosRabata(new BigDecimal(ukupanPopust));
			stavka.setUmanjenoZaRabat(new BigDecimal(glavnica-ukupanPopust));
			
			stavka.setVrednost(new BigDecimal(glavnica-ukupanPopust+ukupanPorez));
			
			
			stavke.getStavkaFakture().add(stavka);
			cnt++;
		}
		
		
		
		faktura.setStavke(stavke);
		
		
		faktura.setUkupnoRobaIUsluge(new BigDecimal(glavnaGlavnica));
		faktura.setUkupanRabat(new BigDecimal(glavniRabat));
		faktura.setUkupanPorez(new BigDecimal(glavniPorez));
		faktura.setIznosZaUplatu(new BigDecimal(glavnaGlavnica - glavniRabat + glavniPorez));
		
		RegistrovanaFirma rf = firmaRepository.findRegistrovanaFirmaByPib(faktura.getKupac().getPIB());
		
		if(rf == null){
			return new ResponseEntity<Odgovor>( new Odgovor(Odgovor.CODE.NOTOK, "Nema registrovane banke" ), HttpStatus.OK);
		}
		
		int port = rf.getPort();
		
		String uri = "http://localhost:"+port+"/special/primiFakturu";
		
		faktura.setTip(false);
		
		faktura.setDatumFakture(fakturaDTO.getDatumFakture());
		faktura.setDatumValute(fakturaDTO.getDatumValute());
		
		Gson gson = new Gson();
		
		String jsonInString = gson.toJson(faktura);
		
		int indexPocetkaF = jsonInString.indexOf("datumFakture");
		int indexOnogaStoMiTrebaF = indexPocetkaF+15;
		
		String datumFaktureString = jsonInString.substring(indexOnogaStoMiTrebaF, indexOnogaStoMiTrebaF+24);
		
		jsonInString.replaceAll(datumFaktureString, formattedTimeDatumFakture);
		jsonInString.replaceAll(fakturaDTO.getDatumValute().toString(), formattedTimeDatumValute);
		
		RestTemplate restTemplate = new RestTemplate();
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jsonInString, headers);
		
		
		ResponseEntity<String> odgovor = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
				System.out.println("Dobijen odgovor");
		if (odgovor.getStatusCode() == HttpStatus.OK) {
				  JSONObject vracenaJson = new JSONObject(odgovor.getBody());
				  System.out.println("Kod poruke je OK");
				  //Faktura vracenaFaktura = gson.fromJson(vracenaJson.toString(), Faktura.class);
				  Odgovor vraceniOdgovor = gson.fromJson(vracenaJson.toString(), Odgovor.class);
				  
				  return new ResponseEntity<Odgovor>( vraceniOdgovor, HttpStatus.OK);
		} else  {
				  System.out.println("Kod poruke nije OK");
		}
		
		fakturaRepository.save(faktura);
		
		
		return new ResponseEntity<Odgovor>( new Odgovor(Odgovor.CODE.OK, "Prosledjena faktura" ), HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/special/primiFakturu",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Odgovor primiFakturu(@RequestBody Faktura faktura, @Context HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
	    System.out.println("Primljena faktura za"+ faktura.getKupac().getPIB());
	    System.out.println("Pravim nalog!");
//	    NalogZaPrenos nzp = napraviNalogZaFakturu(faktura);
//		
//	    
//	    String odgovor = poslovnaNalogKlijent.posaljiNalog(nzp);
//	    
	    faktura.setId(null);
	    faktura.setTip(true);
		fakturaRepository.save(faktura);
		
	    
		return new Odgovor(Odgovor.CODE.OK, "Primljena faktura");
	}
	
	@RequestMapping(
			value = "/special/getPartners",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RegistrovanaFirma>> nadjiSaradnike(@Context HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		return new ResponseEntity<Collection<RegistrovanaFirma>>(firmaRepository.findAll(), HttpStatus.OK);
	}
	
	private NalogZaPrenos napraviNalogZaFakturu(Faktura faktura){
		
		NalogZaPrenos nalog = new NalogZaPrenos();
		if(faktura.getIznosZaUplatu().compareTo(new BigDecimal(250000)) == 1){
			nalog.setHitno("Da");
		}else{
			nalog.setHitno("Ne");
		}
		
		PodaciOPlacanju pop = new PodaciOPlacanju();
		pop.setDatumValute(faktura.getDatumValute());
		pop.setDatumNaloga(faktura.getDatumFakture());
		
		TFinansijskiPodaci finDuznik = new TFinansijskiPodaci();
		finDuznik.setBrojRacuna("123-1726533787929-93");
		finDuznik.setModel("97");
		finDuznik.setPozivNaBroj(faktura.getBrojFakture());
		
		TFinansijskiPodaci finPoverilac = new TFinansijskiPodaci();
		finPoverilac.setBrojRacuna(faktura.getUplataNaRacun());
		finPoverilac.setModel("97");
		finPoverilac.setPozivNaBroj(faktura.getBrojFakture());
		
		
		pop.setFinansijskiPodaciDuznik(finDuznik);
		pop.setFinansijskiPodaciPoverilac(finPoverilac);
		
		pop.setIznos(faktura.getIznosZaUplatu());
		pop.setOznakaValute(faktura.getOznakaValute());
		
		
		nalog.setPodaciOPlacanju(pop);
		
		nalog.setSvrhaPlacanja("Placanje fakture!");
		
		
		nalog.setDuznikNalogodavac(faktura.getKupac().getNaziv()+" "+faktura.getKupac().getPIB());
		
		nalog.setPrimalacPoverilac(faktura.getDobavljac().getNaziv()+" "+faktura.getDobavljac().getPIB());
		
		return nalog;
		
	}

}
