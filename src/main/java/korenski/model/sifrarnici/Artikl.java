package korenski.model.sifrarnici;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Artikl {
	@Id
	@GeneratedValue
	private Long id;
	private String naziv;
	private String jedinicaMjere;
	private double jedinicnaCjena;
	
	public Artikl() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setJedinicaMjere(String jedinicaMjere){
		this.jedinicaMjere=jedinicaMjere;
	}
	public double getJedinicnaCjena() {
		return jedinicnaCjena;
	}
	public void setJedinicnaCjena(double jedinicnaCjena) {
		this.jedinicnaCjena = jedinicnaCjena;
	}
	
	

}
