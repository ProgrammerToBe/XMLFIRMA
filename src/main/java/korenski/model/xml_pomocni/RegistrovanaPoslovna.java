package korenski.model.xml_pomocni;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RegistrovanaPoslovna {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String prveTri;
	
	@Column(unique=true, nullable=false)
	private int port;

	
	
	
	public RegistrovanaPoslovna() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegistrovanaPoslovna(Long id, String prveTri, int port) {
		super();
		this.id = id;
		this.prveTri = prveTri;
		this.port = port;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrveTri() {
		return prveTri;
	}

	public void setPrveTri(String prveTri) {
		this.prveTri = prveTri;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
	
	
}
