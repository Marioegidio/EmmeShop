package model;

import java.util.ArrayList;

public class ArrayFattura {
	
	
	public ArrayFattura() {
		listaFattura = new ArrayList<FatturaBean>();
	}

	public void add(FatturaBean f) {
		listaFattura.add(f);
	}

	public  ArrayList<FatturaBean> getAllFatture(){
		return listaFattura;
	}

	private ArrayList<FatturaBean> listaFattura;

	}
