package korenski.controller.firma_to_firma;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import korenski.model.dto.FakturaDTO;
import korenski.model.sifrarnici.Artikl;
import korenski.repository.sifrarnici.ArtiklRepository;
import korenski.repository.soap.FakturaRepository;
import korenski.repository.soap.NalogRepository;
import korenski.soap.fakture_model.Faktura;
import korenski.soap.klijenti.PoslovnaNalogKlijent;
import korenski.soap.nalozi_model.NalogZaPrenos;
import korenski.soap.nalozi_model.NalogZaPrenos.PodaciOPlacanju;
import korenski.soap.nalozi_model.TFinansijskiPodaci;

@Controller
public class FaktureController {

	@Autowired
	FakturaRepository fakturaRepository;
	@Autowired
	PoslovnaNalogKlijent poslovnaNalogKlijent;
	@Autowired
	ArtiklRepository artiklRepository;
	@Autowired
	NalogRepository nalogRepository;

	@RequestMapping(value = "/fakture", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Faktura>> findAllFakture() {
		// return
		return new ResponseEntity<Set<Faktura>>(fakturaRepository.findByTipTrueAndZatvorenaFalse(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/artikli", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Artikl>> findAllArtikl(){
		return new ResponseEntity<Set<Artikl>>(artiklRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/platiFaktura", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FakturaDTO> plati(@RequestBody FakturaDTO fakturaDTO) {
		System.out.println("Primljena faktura svrha uplate: " + fakturaDTO.getSvrhaPlacanja());
		System.out.println("Broj stavki " + fakturaDTO.getFaktura().getStavke().getStavkaFakture().size());
		NalogZaPrenos nzp = napraviNalogZaFakturu(fakturaDTO);
		
		String odgovor = poslovnaNalogKlijent.posaljiNalog(nzp);
		
		nalogRepository.save(nzp);
		
		Faktura faktura = fakturaRepository.findOne(fakturaDTO.getFaktura().getId());
		
		
		faktura.setZatvorena(true);
		
		fakturaRepository.save(faktura);
		
		return null;
	}

	private NalogZaPrenos napraviNalogZaFakturu(FakturaDTO faktura) {

		NalogZaPrenos nalog = new NalogZaPrenos();
		nalog.setIDPoruke(new Date().toString() + faktura.getFaktura().getKupac().getPIB() + faktura.getFaktura().getDobavljac().getPIB());
		// if(faktura.getFaktura().getIznosZaUplatu().compareTo(new
		// BigDecimal(250000)) == 1){
		// nalog.setHitno("Da");
		// }else{
		// nalog.setHitno("Ne");
		// }
		//
		if (faktura.isHitno()) {
			nalog.setHitno("Da");
		} else {
			nalog.setHitno("Ne");
		}
		PodaciOPlacanju pop = new PodaciOPlacanju();
		pop.setDatumValute(faktura.getFaktura().getDatumValute());
		pop.setDatumNaloga(faktura.getFaktura().getDatumFakture());

		TFinansijskiPodaci finDuznik = new TFinansijskiPodaci();
		finDuznik.setBrojRacuna(faktura.getRacunDuznika());
		finDuznik.setModel(faktura.getModelDuznika());
		finDuznik.setPozivNaBroj(faktura.getPozivNaBrojDuznika());

		TFinansijskiPodaci finPoverilac = new TFinansijskiPodaci();
		finPoverilac.setBrojRacuna(faktura.getFaktura().getUplataNaRacun());
		finPoverilac.setModel(faktura.getModelPoverioca());
		finPoverilac.setPozivNaBroj(faktura.getPozivNaBrojPoverioca());

		pop.setFinansijskiPodaciDuznik(finDuznik);
		pop.setFinansijskiPodaciPoverilac(finPoverilac);

		pop.setIznos(faktura.getFaktura().getIznosZaUplatu());
		pop.setOznakaValute(faktura.getFaktura().getOznakaValute());
		pop.setDatumNaloga(new Date());
		pop.setDatumValute(faktura.getFaktura().getDatumValute());

		nalog.setPodaciOPlacanju(pop);

		nalog.setSvrhaPlacanja(faktura.getSvrhaPlacanja());

		nalog.setDuznikNalogodavac(
				faktura.getFaktura().getKupac().getNaziv() + " " + faktura.getFaktura().getKupac().getPIB());

		nalog.setPrimalacPoverilac(
				faktura.getFaktura().getDobavljac().getNaziv() + " " + faktura.getFaktura().getDobavljac().getPIB());

		return nalog;

	}

}
