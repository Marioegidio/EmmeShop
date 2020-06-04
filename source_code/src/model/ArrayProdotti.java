package model;

import java.util.ArrayList;

public class ArrayProdotti {
	
	public ArrayProdotti() {
		listaProdotti = new ArrayList<ProdottoBean>();		
	}
	
	public void add(ProdottoBean p) {
		listaProdotti.add(p);
	}
	
	public ArrayList<ProdottoBean> getArrayProd() {
		return listaProdotti;
	}
	
 	ArrayList<ProdottoBean> listaProdotti;

	@Override
	public String toString() {
		return "ArrayProdotti [listaProdotti=" + listaProdotti + "]";
	}
	
	public ProdottoBean findProduct(ProdottoBean nuovo){
		for(ProdottoBean presente : listaProdotti) {
			if( presente.getNomeNegozio().equals(nuovo.getNomeNegozio())   && presente.getNomeCategoria().equals(nuovo.getNomeCategoria())  &&  presente.getIdProdotto()==nuovo.getIdProdotto()){
				return presente;
			}
		}
		return null;
	}
 	
 	
}
