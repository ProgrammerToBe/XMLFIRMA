package korenski.soap.klijenti.dto;

import java.util.Date;

public class IzvodDTO {
	private Date datum;
	private String brojRacuna;
	private String brojPreseka;
	
	public IzvodDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public String getBrojPreseka() {
		return brojPreseka;
	}

	public void setBrojPreseka(String brojPreseka) {
		this.brojPreseka = brojPreseka;
	}
}
