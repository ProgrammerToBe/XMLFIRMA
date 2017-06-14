package korenski.model.xml_pomocni;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RegistrovanaFirma {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String naziv;
	
	@Column(unique=true, nullable=false)
	private String adresa;
	
	@Column(unique=true, nullable=false)
	private String pib;
	
	@Column(unique=true, nullable=false)
	private int port;

	public RegistrovanaFirma() {
		super();
		
	}
	
	public RegistrovanaFirma(Long id, String naziv, String adresa, String pib, int port) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.pib = pib;
		this.port = port;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}



	public String getNaziv() {
		return naziv;
	}



	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}



	public String getAdresa() {
		return adresa;
	}



	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	
	
	
}
