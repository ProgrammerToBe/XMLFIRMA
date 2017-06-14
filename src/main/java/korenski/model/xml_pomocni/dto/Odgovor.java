package korenski.model.xml_pomocni.dto;

public class Odgovor {
	
	public enum CODE { OK, NOTOK }
	
	private CODE code;
	private String text;
	
	public Odgovor(CODE code, String text) {
		super();
		this.code = code;
		this.text = text;
	}
	
	public Odgovor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CODE getCode() {
		return code;
	}
	public void setCode(CODE code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	

}
