package model;

public class AmministratoreBean extends UserBean{
	private static final long serialVersionUID = 1L;

	private String nome;
	private String cognome;
	private String email;
	private String sesso;
	private String telefono;
	
	public AmministratoreBean()
	{
		super();
		this.nome = "";
		this.cognome = "";
		this.email = "";
		this.sesso = "";
		this.telefono = "";
	}
	
	public AmministratoreBean(String username, String password, String nome, String cognome, String email, String sesso,
			String telefono) 
	{
		super(username,password);
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.sesso = sesso;
		this.telefono = telefono;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
