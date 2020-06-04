package model;

public class ProdottoBean {
	

	private int idProdotto;
	private String nomeNegozio;
	private String nomeCategoria;
	private int iva;
	private float prezzo;
	private String nome;
	private int quantita;
	private int sconto;
	private String descrizione;
	private String path;
	private int qtaOrdinata;
	private String note;
	
	public ProdottoBean() {
		this.idProdotto=-1;
		this.nomeNegozio="";
		this.nomeCategoria="";
		this.iva=0;
		this.prezzo=0;
		this.nome="";
		this.quantita=0;
		this.sconto=0;
		this.descrizione="";
		this.path="";
	}
	
	public ProdottoBean(int idProdotto, String nomeNegozio, String nomeCategoria, int iva, float prezzo, String nome,String path,
			int quantita, int sconto, String descrizione) {
		this.idProdotto = idProdotto;
		this.nomeNegozio = nomeNegozio;
		this.nomeCategoria = nomeCategoria;
		this.iva = iva;
		this.prezzo = prezzo;
		this.nome = nome;
		this.quantita = quantita;
		this.sconto = sconto;
		this.descrizione = descrizione;
		this.path = path;
	}
	
	
	public int getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getNomeNegozio() {
		return nomeNegozio;
	}
	public void setNomeNegozio(String nomeNegozio) {
		this.nomeNegozio = nomeNegozio;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public int getIva() {
		return iva;
	}
	public void setIva(int iva) {
		this.iva = iva;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public int getQuantitaOrdinata() {
		return qtaOrdinata;
	}
	public void setQuantitaOrdinata(int qtaOrdinata) {
		this.qtaOrdinata = qtaOrdinata;
	}
	public int getSconto() {
		return sconto;
	}
	public void setSconto(int sconto) {
		this.sconto = sconto;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "ProdottoBean [idProdotto=" + idProdotto + ", nomeNegozio=" + nomeNegozio + ", nomeCategoria="
				+ nomeCategoria + ", iva=" + iva + ", prezzo=" + prezzo + ", nome=" + nome + ", quantita=" + quantita
				+ ", sconto=" + sconto + ", descrizione=" + descrizione + ", path=" + path + ", qtaOrdinata="
				+ qtaOrdinata + ", note=" + note + "]";
	}
	
	
	
	


}

