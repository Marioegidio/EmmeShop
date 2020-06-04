package model;

import java.util.ArrayList;

public class ArrayRiferimento {
	public ArrayRiferimento(){
		listaRiferimento=new ArrayList<RiferimentoBean>();
	}
	
	public void add(RiferimentoBean f) {
		listaRiferimento.add(f);
}
	
	public ArrayList<RiferimentoBean> getAllRiferimento(){
		return listaRiferimento;
	}
	
	private ArrayList<RiferimentoBean> listaRiferimento;
}
