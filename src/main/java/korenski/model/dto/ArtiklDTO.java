package korenski.model.dto;

import korenski.model.sifrarnici.Artikl;

public class ArtiklDTO {
	//private Artikl artikl;
	private String naziv;
	private String jedinicaMjere;
	private double jedinicnaCjena;
	private double popust;
	private double pdv;
	private int kolicina;
	public ArtiklDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getJedinicaMjere() {
		return jedinicaMjere;
	}

	public void setJedinicaMjere(String jedinicaMjere) {
		this.jedinicaMjere = jedinicaMjere;
	}

	public double getJedinicnaCjena() {
		return jedinicnaCjena;
	}

	public void setJedinicnaCjena(double jedinicnaCjena) {
		this.jedinicnaCjena = jedinicnaCjena;
	}

	public double getPopust() {
		return popust;
	}
	public void setPopust(double popust) {
		this.popust = popust;
	}
	public double getPdv() {
		return pdv;
	}
	public void setPdv(double pdv) {
		this.pdv = pdv;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
	
	

}
