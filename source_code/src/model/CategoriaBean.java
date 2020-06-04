package model;

public class CategoriaBean {
	
	private String nomeNegozio;
	private String nomeCategoria;
	private String descrizione;
	private String path;
	
	public CategoriaBean()
	{
		this.nomeNegozio="";
		this.nomeCategoria="";
		this.descrizione="";
		this.path="";
	}
	public CategoriaBean(String nomeNegozio, String nomeCategoria,String path, String descrizione)
	{
		this.nomeNegozio=nomeNegozio;
		this.nomeCategoria=nomeCategoria;
		this.descrizione=descrizione;
		this.path=path;
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
