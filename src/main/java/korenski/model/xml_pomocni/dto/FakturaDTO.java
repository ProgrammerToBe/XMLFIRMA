package korenski.model.xml_pomocni.dto;

import java.util.Date;

public class FakturaDTO {
	
	private int brojFakture;
	
	private int pibDobavljaca;
	private int pibKupca;
	
	private String oznakaValute;
	
	private double vrednostRobe;
	private double vrednostUsluge;
	
	private double ukupanRabat;
	private double ukupanPorez;
	
	private String racunZaUplatu;
	
	private Date datumFakture;
	private Date datumValute;
	public FakturaDTO(int brojFakture, int pibDobavljaca, int pibKupca, String oznakaValute, double vrednostRobe,
			double vrednostUsluge, double ukupanRabat, double ukupanPorez, String racunZaUplatu, Date datumFakture,
			Date datumValute) {
		super();
		this.brojFakture = brojFakture;
		this.pibDobavljaca = pibDobavljaca;
		this.pibKupca = pibKupca;
		this.oznakaValute = oznakaValute;
		this.vrednostRobe = vrednostRobe;
		this.vrednostUsluge = vrednostUsluge;
		this.ukupanRabat = ukupanRabat;
		this.ukupanPorez = ukupanPorez;
		this.racunZaUplatu = racunZaUplatu;
		this.datumFakture = datumFakture;
		this.datumValute = datumValute;
	}
	public FakturaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getBrojFakture() {
		return brojFakture;
	}
	public void setBrojFakture(int brojFakture) {
		this.brojFakture = brojFakture;
	}
	public int getPibDobavljaca() {
		return pibDobavljaca;
	}
	public void setPibDobavljaca(int pibDobavljaca) {
		this.pibDobavljaca = pibDobavljaca;
	}
	public int getPibKupca() {
		return pibKupca;
	}
	public void setPibKupca(int pibKupca) {
		this.pibKupca = pibKupca;
	}
	public String getOznakaValute() {
		return oznakaValute;
	}
	public void setOznakaValute(String oznakaValute) {
		this.oznakaValute = oznakaValute;
	}
	public double getVrednostRobe() {
		return vrednostRobe;
	}
	public void setVrednostRobe(double vrednostRobe) {
		this.vrednostRobe = vrednostRobe;
	}
	public double getVrednostUsluge() {
		return vrednostUsluge;
	}
	public void setVrednostUsluge(double vrednostUsluge) {
		this.vrednostUsluge = vrednostUsluge;
	}
	public double getUkupanRabat() {
		return ukupanRabat;
	}
	public void setUkupanRabat(double ukupanRabat) {
		this.ukupanRabat = ukupanRabat;
	}
	public double getUkupanPorez() {
		return ukupanPorez;
	}
	public void setUkupanPorez(double ukupanPorez) {
		this.ukupanPorez = ukupanPorez;
	}
	public String getRacunZaUplatu() {
		return racunZaUplatu;
	}
	public void setRacunZaUplatu(String racunZaUplatu) {
		this.racunZaUplatu = racunZaUplatu;
	}
	public Date getDatumFakture() {
		return datumFakture;
	}
	public void setDatumFakture(Date datumFakture) {
		this.datumFakture = datumFakture;
	}
	public Date getDatumValute() {
		return datumValute;
	}
	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}
	
	
	

}
