package korenski.model.dto;

import korenski.soap.fakture_model.Faktura;

public class FakturaDTO {
	private Faktura faktura;
	private String racunDuznika;
	private String modelDuznika;
	private String pozivNaBrojDuznika;
	private String racunPoverioca;
	private String svrhaPlacanja;
	private String modelPoverioca;
	private String pozivNaBrojPoverioca;
	private boolean hitno;
	public Faktura getFaktura() {
		return faktura;
	}
	public void setFaktura(Faktura faktura) {
		this.faktura = faktura;
	}
	public String getRacunDuznika() {
		return racunDuznika;
	}
	public void setRacunDuznika(String racunDuznika) {
		this.racunDuznika = racunDuznika;
	}
	public String getModelDuznika() {
		return modelDuznika;
	}
	public void setModelDuznika(String modelDuznika) {
		this.modelDuznika = modelDuznika;
	}
	public String getPozivNaBrojDuznika() {
		return pozivNaBrojDuznika;
	}
	public void setPozivNaBrojDuznika(String pozivNaBrojDuznika) {
		this.pozivNaBrojDuznika = pozivNaBrojDuznika;
	}
	public String getRacunPoverioca() {
		return racunPoverioca;
	}
	public void setRacunPoverioca(String racunPoverioca) {
		this.racunPoverioca = racunPoverioca;
	}
	public String getSvrhaPlacanja() {
		return svrhaPlacanja;
	}
	public void setSvrhaPlacanja(String svrhaPlacanja) {
		this.svrhaPlacanja = svrhaPlacanja;
	}
	public String getModelPoverioca() {
		return modelPoverioca;
	}
	public void setModelPoverioca(String modelPoverioca) {
		this.modelPoverioca = modelPoverioca;
	}
	
	public String getPozivNaBrojPoverioca() {
		return pozivNaBrojPoverioca;
	}
	public void setPozivNaBrojPoverioca(String pozivNaBrojPoverioca) {
		this.pozivNaBrojPoverioca = pozivNaBrojPoverioca;
	}
	public boolean isHitno() {
		return hitno;
	}
	public void setHitno(boolean hitno) {
		this.hitno = hitno;
	}
	
	

}
