package model;

public class RegistrazioneBean {
	
	public RegistrazioneBean() {
		this.nomeNegozio="";
		this.UsernameCliente="";
	}
	
	public String getNomeNegozio() {
		return nomeNegozio;
	}
	public void setNomeNegozio(String nomeNegozio) {
		this.nomeNegozio = nomeNegozio;
	}
	public String getUsernameCliente() {
		return UsernameCliente;
	}
	public void setUsernameCliente(String usernameCliente) {
		UsernameCliente = usernameCliente;
	}





	String nomeNegozio;
	String UsernameCliente;
}
