package model;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.Statement;




public class UserModelDS  {

	private static DataSource ds;
	private static final String TABLE_CLIENTE = "cliente";
	private static final String TABLE_VENDITORE = "venditore";
	private static final String TABLE_AMMINISTRATORE = "amministratore";
	private static final String TABLE_PRODOTTO="prodotto";
	private static final String TABLE_CATEGORIA="categoria";
	private static final String TABLE_NEGOZIO="negozio";
	private static final String TABLE_FATTURA="fattura";
	private static final String TABLE_RIFERIMENTO="riferimento";
	
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			
			System.out.println(envCtx.INITIAL_CONTEXT_FACTORY);

			ds = (DataSource) envCtx.lookup("jdbc/emmeshop_db");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	//cerca login cliente
	public synchronized UserBean checkLoginClient(String username,String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ClienteBean bean = new ClienteBean();

		String selectSQL = "SELECT * FROM " + UserModelDS.TABLE_CLIENTE + " WHERE username = ? AND password = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
			    bean.setCap(rs.getString("cap"));
			    bean.setCitta(rs.getString("citt‡"));
			    bean.setVia(rs.getString("via"));
			}

		} 
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		

		if(bean.getUsername().trim().equalsIgnoreCase("") || bean==null)
			return null;
		else
			return bean;
	}
	
	//cerca venditore
	public synchronized UserBean checkLoginSeller(String username,String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UserBean bean = new VenditoreBean();

		String selectSQL = "SELECT username,password FROM " + UserModelDS.TABLE_VENDITORE + " WHERE username = ? AND password = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
			}

		} 
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		

		if(bean.getUsername().trim().equalsIgnoreCase("") || bean==null)
			return null;
		else
			return bean;
	}
	
	//cerca login amministratore
	public synchronized UserBean checkLoginAdmin(String username,String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UserBean bean = new AmministratoreBean();

		String selectSQL = "SELECT username,password FROM " + UserModelDS.TABLE_AMMINISTRATORE + " WHERE username = ? AND password = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
			}

		} 
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		

		if(bean.getUsername().trim().equalsIgnoreCase("") || bean==null)
			return null;
		else
			return bean;
	}
	
	//aggiungi  Cliente
	public synchronized void  addCliente(ClienteBean cliente ) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String insertClienteSql = "INSERT INTO " +UserModelDS.TABLE_CLIENTE+ 
				"(username,password,nome,cognome,email,sesso,telefono,via,citt‡,cap)" 
				+"VALUES(?,?,?,?,?,?,?,?,?,?)"; 


		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertClienteSql);
			preparedStatement.setString(1,cliente.getUsername());
			preparedStatement.setString(2,cliente.getPassword());
			preparedStatement.setString(3,cliente.getNome());
			preparedStatement.setString(4,cliente.getCognome());
			preparedStatement.setString(5,cliente.getEmail());
			preparedStatement.setString(6,cliente.getSesso());
			preparedStatement.setString(7,cliente.getTelefono());
			preparedStatement.setString(8,cliente.getVia());
			preparedStatement.setString(9,cliente.getCitta());
			preparedStatement.setString(10,cliente.getCap());
			

			preparedStatement.executeUpdate();

			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}
	
	//Venditore
	public synchronized void addVenditore(VenditoreBean venditore)throws SQLException{

		Connection connection=null;
		PreparedStatement preparedStatement = null;

		String insertVenditore="INSERT INTO "+UserModelDS.TABLE_VENDITORE
				+"(username,password,nome,cognome,email,sesso,telefono,via,citt‡,cap)"
				+"VALUES(?,?,?,?,?,?,?,?,?,?)";

		try {	
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertVenditore);

			preparedStatement.setString(1,venditore.getUsername());
			preparedStatement.setString(2,venditore.getPassword());
			preparedStatement.setString(3,venditore.getNome());
			preparedStatement.setString(4,venditore.getCognome());
			preparedStatement.setString(5,venditore.getEmail());
			preparedStatement.setString(6,venditore.getSesso());
			preparedStatement.setString(7,venditore.getTelefono());
			preparedStatement.setString(8,venditore.getVia());
			preparedStatement.setString(9,venditore.getCitta());
			preparedStatement.setString(10,venditore.getCap());

			preparedStatement.execute();
			connection.commit();

		}finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			}finally {
				if(connection!=null)
					connection.close();
			}
		}
	}

	
	//Amministratore 
	public synchronized void addAmministratore(AmministratoreBean amministratore) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertAmministratore = "INSERT INTO " + UserModelDS.TABLE_AMMINISTRATORE
				+ "(username,password,nome,cognome,email,sesso,telefono)"
				+"VALUES(?,?,?,?,?,?,?)";

		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertAmministratore);
			
			preparedStatement.setString(1,amministratore.getUsername());
			preparedStatement.setString(2,amministratore.getPassword());
			preparedStatement.setString(3,amministratore.getNome());
			preparedStatement.setString(4,amministratore.getCognome());
			preparedStatement.setString(5,amministratore.getEmail());
			preparedStatement.setString(6,amministratore.getSesso());
			preparedStatement.setString(7,amministratore.getTelefono());

			preparedStatement.execute();
			
			connection.commit();

		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
	}
	
	
	/* Aggiunta di un prodotto nella tabella Prodotto anche nella tabellaCategoria */
	public synchronized ProdottoBean addProdotto(ProdottoBean prodotto) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertProdotto = "INSERT INTO " + UserModelDS.TABLE_PRODOTTO
				               +" (Nome_Negozio,Nome_Categoria,iva,path,prezzo,nome,qta,sconto,descrizione)"
				               +" VALUES(?,?,?,?,?,?,?,?,?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertProdotto,Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1,prodotto.getNomeNegozio());
			preparedStatement.setString(2,prodotto.getNomeCategoria());
			preparedStatement.setInt(3,prodotto.getIva());
			preparedStatement.setString(4,prodotto.getPath());
			preparedStatement.setFloat(5,prodotto.getPrezzo());
			preparedStatement.setString(6,prodotto.getNome());
			preparedStatement.setInt(7,prodotto.getQuantita());
			preparedStatement.setInt(8,prodotto.getSconto());
			preparedStatement.setString(9,prodotto.getDescrizione());
			
			
			/* controlliamo se il negozio ha quella particolare categoria di prodotto
			 * se isCategoria ritorna false allora non c'Ë l'ha e 
			 *  quindi aggiungiamo la categoria a quel negozio nella tabella Categoria 
			 *  con il metodo addCategoria */
			/*if(!isCategoria(prodotto.getNomeNegozio(),prodotto.getNomeCategoria())) {
				/* creiamo il bean categoria */
				/*CategoriaBean categ = new CategoriaBean();
				categ.setNomeNegozio(prodotto.getNomeNegozio());
				categ.setNomeCategoria(prodotto.getNomeCategoria());
				categ.setDescrizione(prodotto.getDescrizione());*/
				/*Aggiungiamo il bean categoria  alla tabella categoria  */
			    /*addCategoria(categ);
			}*/
			
			preparedStatement.execute();
			int last_inserted_id=0;
			ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next())
            	 last_inserted_id = rs.getInt(1);
            
            prodotto.setIdProdotto( last_inserted_id);
			connection.commit();

		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		
		return prodotto;
		
	}
	
	/*aggiunta nella tabella Categoria */
	public synchronized void addCategoria(CategoriaBean categoria) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertCateg ="INSERT INTO " + UserModelDS.TABLE_CATEGORIA
				               +"(nomeNeg,nomeCategoria,descrizione,path)"
				               +"VALUES(?,?,?,?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertCateg);
			
			preparedStatement.setString(1,categoria.getNomeNegozio());
			preparedStatement.setString(2,categoria.getNomeCategoria());

			preparedStatement.setString(3,categoria.getDescrizione());
			preparedStatement.setString(4,categoria.getPath());
			preparedStatement.execute();
			
			
			
			connection.commit();

		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		
	}
	
	//se la categoria esiste nel negozio
	public synchronized boolean isCategoria(String nomeNegozio,String nomeCategoria) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String isCateg ="SELECT nomeNeg,nomeCategoria" + UserModelDS.TABLE_CATEGORIA
	               +"where nomeNeg=? and nomeCategoria=?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(isCateg);
			
			preparedStatement.setString(1,nomeNegozio);
			preparedStatement.setString(2,nomeCategoria);
			ResultSet rs=preparedStatement.executeQuery();
			
			
			/*se Ë presente il nome del negozio e il nome del categoria retun true
			 * altrimenti false  */
			if(rs.next()) return true;
			else return false;

		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		
	}
	
	
	
	/* Metodo per ottenere tutti i prodotti di un particolare negozio 
	 * 
	 * return null se  il negozio non ha nessun prodotto altrimenti un arrayList<Prodotto>   
	 * */
	public  synchronized  ArrayList<ProdottoBean> getAllProductNegozio(String nomeNegozio) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayProdotti c = new ArrayProdotti();
		
		String selectProdotti = "SELECT* " +UserModelDS.TABLE_PRODOTTO 
				               +" where Nome_Negozio=?";
		
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectProdotti);
			
			preparedStatement.setString(1,nomeNegozio);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("IdProdotto");
				String nomeNeg = rs.getString("Nome_Negozio");
				String nomeCateg = rs.getString("Nome_Categgoria");
				int iva = rs.getInt("iva");
				float prezzo = rs.getFloat("prezzo");
				String nome = rs.getString("nome");
				int qta = rs.getInt("qta");
				int sconto = rs.getInt("sconto");
				String descrizione = rs.getString("descrizione");
				String path = rs.getString("path");
				
				ProdottoBean p = new ProdottoBean(id,nomeNeg,nomeCateg,iva,prezzo,nome,path,qta,sconto,descrizione);
				
				
				c.add(p);
			}
			

		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
			
		}
		
		if(c.getArrayProd().size()==0) return null;
		
		return c.getArrayProd();
	}
	
	//cancella prodotto
public  synchronized boolean deleteProduct (int id ) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;

		String deleteSQL = "DELETE FROM prodotto WHERE IdProdotto = ? ";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1,id);

			result = preparedStatement.executeUpdate();

			
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}
	







public  synchronized void deleteCategory(String nomeNegozio, String nomeCategoria ) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	

	String deleteSQL = "DELETE FROM categoria  WHERE nomeNeg = ? AND nomeCategoria = ? ";
	//System.out.println(nomeNegozio+" "+nomeCategoria);
	try {
		
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(deleteSQL);
		preparedStatement.setString(1,nomeNegozio);
		preparedStatement.setString(2,nomeCategoria);

		preparedStatement.executeUpdate();

			connection.commit();

	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
}











public  synchronized void deleteShop(String nomeNegozio ) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	System.out.println("\n "+ nomeNegozio +" \n");
	

	String deleteSQL = "DELETE FROM negozio  WHERE nome = ? ";
	//System.out.println(nomeNegozio+" "+nomeCategoria);
	try {
		
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(deleteSQL);
		preparedStatement.setString(1,nomeNegozio);

		 preparedStatement.executeUpdate();

			connection.commit();

	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
}















public  synchronized void deleteCliente(String username) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	String deleteSQL = "DELETE FROM cliente  WHERE username = ? ";
	//System.out.println(nomeNegozio+" "+nomeCategoria);
	try {
		
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(deleteSQL);
		preparedStatement.setString(1,username);

		 preparedStatement.executeUpdate();

			connection.commit();

	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
}







public  synchronized void deleteVenditore(String username) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	String deleteSQL = "DELETE FROM venditore  WHERE username = ? ";
	//System.out.println(nomeNegozio+" "+nomeCategoria);
	try {
		
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(deleteSQL);
		preparedStatement.setString(1,username);

		 preparedStatement.executeUpdate();

			connection.commit();

	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
}









public synchronized ArrayFattura getArrayFattura(String username) throws SQLException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	ArrayFattura fatture = null;
	ArrayRiferimento riferimenti = null;
	
	String viewOrdineCliente = " SELECT * from fattura  WHERE username_Cliente = ? ORDER BY numeroFattura DESC ";
	
	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(viewOrdineCliente);
		preparedStatement.setString(1,username);
		
		ResultSet result =  preparedStatement.executeQuery();
		
		fatture=new ArrayFattura();
		
		while(result.next()) {
			
			FatturaBean fattura=new FatturaBean();
			
			fattura.setNumFattura (result.getInt("numeroFattura"));
			fattura.setUsername_Cliente(result.getString("username_Cliente"));
			fattura.setDataOrdine(result.getString("dataOrdine"));
			fattura.setCapDestinazione(result.getString("capDestinazione"));
			fattura.setCittaDestinazione(result.getString("citt‡Destinazione"));
			fattura.setViaDestinazione(result.getString("viaDestinazione"));
			
			
					PreparedStatement preparedStatementRiferimento = null;
					
					String viewRiferimentoCliente = "SELECT * from riferimento  WHERE numero_Fattura = ? ";
					
					try {
						preparedStatementRiferimento = connection.prepareStatement(viewRiferimentoCliente);
						preparedStatementRiferimento.setInt(1,fattura.getNumFattura());
						ResultSet rs =  preparedStatementRiferimento.executeQuery();
						riferimenti=new ArrayRiferimento();
						while(rs.next()) {
							
							RiferimentoBean riferimento=new RiferimentoBean();
							
							riferimento.setId_prodotto(rs.getInt("ID_Prodotto"));
							riferimento.setNumero_Fattura(rs.getInt("numero_Fattura"));
							riferimento.setNome_Negozio(rs.getString("Nome_Negozio"));
							riferimento.setNome_Categoria(rs.getString("Nome_Categoria"));
							riferimento.setNote(rs.getString("nota"));
							riferimento.setQtaOrdinata(rs.getInt("QtaOrdinata"));
							riferimento.setSconto(rs.getInt("sconto"));
							riferimento.setPrezzoUnitario(rs.getFloat("prezzoUnitario"));
							riferimento.setIva(rs.getInt("IVA"));
							
							riferimenti.add(riferimento);
						}
					} 
					finally {
								if (preparedStatementRiferimento != null)
									preparedStatementRiferimento.close();
					}
					
					
			if(riferimenti != null)fattura.setListaRiferimenti(riferimenti);		
			fatture.add(fattura);
		}
		
		
			
	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
	return fatture;
	
	
	
}

















public synchronized FatturaBean getFatturaByUser(String username,int numFattura) throws SQLException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	FatturaBean fattura = null;
	ArrayRiferimento riferimenti = null;
	
	String viewOrdineCliente = " SELECT * from fattura  WHERE username_Cliente = ? AND numeroFattura = ? ORDER BY numeroFattura DESC ";
	
	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(viewOrdineCliente);
		preparedStatement.setString(1,username);
		preparedStatement.setInt(2,numFattura);
		
		ResultSet result =  preparedStatement.executeQuery();
		
		fattura=new FatturaBean();
		
		while(result.next()) {
			
			fattura=new FatturaBean();
			
			fattura.setNumFattura (result.getInt("numeroFattura"));
			fattura.setUsername_Cliente(result.getString("username_Cliente"));
			fattura.setDataOrdine(result.getString("dataOrdine"));
			fattura.setCapDestinazione(result.getString("capDestinazione"));
			fattura.setCittaDestinazione(result.getString("citt‡Destinazione"));
			fattura.setViaDestinazione(result.getString("viaDestinazione"));
			
			
					PreparedStatement preparedStatementRiferimento = null;
					
					String viewRiferimentoCliente = "SELECT * from riferimento  WHERE numero_Fattura = ? ";
					
					try {
						preparedStatementRiferimento = connection.prepareStatement(viewRiferimentoCliente);
						preparedStatementRiferimento.setInt(1,fattura.getNumFattura());
						ResultSet rs =  preparedStatementRiferimento.executeQuery();
						riferimenti=new ArrayRiferimento();
						while(rs.next()) {
							
							RiferimentoBean riferimento=new RiferimentoBean();
							
							riferimento.setId_prodotto(rs.getInt("ID_Prodotto"));
							riferimento.setNumero_Fattura(rs.getInt("numero_Fattura"));
							riferimento.setNome_Negozio(rs.getString("Nome_Negozio"));
							riferimento.setNome_Categoria(rs.getString("Nome_Categoria"));
							riferimento.setNote(rs.getString("nota"));
							riferimento.setQtaOrdinata(rs.getInt("QtaOrdinata"));
							riferimento.setSconto(rs.getInt("sconto"));
							riferimento.setPrezzoUnitario(rs.getFloat("prezzoUnitario"));
							riferimento.setIva(rs.getInt("IVA"));
							
							riferimenti.add(riferimento);
						}
					} 
					finally {
								if (preparedStatementRiferimento != null)
									preparedStatementRiferimento.close();
					}
					
					
			if(riferimenti != null)fattura.setListaRiferimenti(riferimenti);		
			
		}
		
		
			
	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
	return fattura;
	
	
	
}



































public synchronized ArrayRiferimento getArrayRiferimento(String nomeNegozio) throws SQLException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	
	ArrayRiferimento riferimenti = null;
	
	String viewOrdineCliente = " SELECT * from riferimento,fattura  WHERE numeroFattura=numero_Fattura AND Nome_Negozio = ?  ORDER BY dataOrdine DESC";
	
	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(viewOrdineCliente);
		preparedStatement.setString(1,nomeNegozio);
		
		ResultSet rs =  preparedStatement.executeQuery();
		
		riferimenti=new ArrayRiferimento();
		
		
		while(rs.next()) {
			
			RiferimentoBean riferimento=new RiferimentoBean();
			
			riferimento.setId_prodotto(rs.getInt("ID_Prodotto"));
			riferimento.setNumero_Fattura(rs.getInt("numero_Fattura"));
			riferimento.setNome_Negozio(rs.getString("Nome_Negozio"));
			riferimento.setNome_Categoria(rs.getString("Nome_Categoria"));
			riferimento.setNote(rs.getString("nota"));
			riferimento.setQtaOrdinata(rs.getInt("QtaOrdinata"));
			riferimento.setSconto(rs.getInt("sconto"));
			riferimento.setPrezzoUnitario(rs.getFloat("prezzoUnitario"));
			riferimento.setIva(rs.getInt("IVA"));
			
			FatturaBean fattura=new FatturaBean();
			fattura.setNumFattura (rs.getInt("numeroFattura"));
			fattura.setUsername_Cliente(rs.getString("username_Cliente"));
			fattura.setDataOrdine(rs.getString("dataOrdine"));
			fattura.setCapDestinazione(rs.getString("capDestinazione"));
			fattura.setCittaDestinazione(rs.getString("citt‡Destinazione"));
			fattura.setViaDestinazione(rs.getString("viaDestinazione"));
			
			riferimento.setFatturaRiferimento(fattura);
			
			riferimenti.add(riferimento);
		}
					
					
					
			
		} 
		finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
	return riferimenti;
	
	
	
}












public synchronized ArrayRiferimento getArrayRiferimentoClienti(String nomeNegozio) throws SQLException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	
	ArrayRiferimento riferimenti = null;
	
	String viewOrdineCliente = " SELECT * from riferimento,fattura  WHERE numeroFattura=numero_Fattura AND Nome_Negozio = ?  ORDER BY username_Cliente  ASC,dataOrdine DESC";
	
	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(viewOrdineCliente);
		preparedStatement.setString(1,nomeNegozio);
		
		ResultSet rs =  preparedStatement.executeQuery();
		
		riferimenti=new ArrayRiferimento();
		
		
		while(rs.next()) {
			
			RiferimentoBean riferimento=new RiferimentoBean();
			
			riferimento.setId_prodotto(rs.getInt("ID_Prodotto"));
			riferimento.setNumero_Fattura(rs.getInt("numero_Fattura"));
			riferimento.setNome_Negozio(rs.getString("Nome_Negozio"));
			riferimento.setNome_Categoria(rs.getString("Nome_Categoria"));
			riferimento.setNote(rs.getString("nota"));
			riferimento.setQtaOrdinata(rs.getInt("QtaOrdinata"));
			riferimento.setSconto(rs.getInt("sconto"));
			riferimento.setPrezzoUnitario(rs.getFloat("prezzoUnitario"));
			riferimento.setIva(rs.getInt("IVA"));
			
			FatturaBean fattura=new FatturaBean();
			fattura.setNumFattura (rs.getInt("numeroFattura"));
			fattura.setUsername_Cliente(rs.getString("username_Cliente"));
			fattura.setDataOrdine(rs.getString("dataOrdine"));
			fattura.setCapDestinazione(rs.getString("capDestinazione"));
			fattura.setCittaDestinazione(rs.getString("citt‡Destinazione"));
			fattura.setViaDestinazione(rs.getString("viaDestinazione"));
			
			riferimento.setFatturaRiferimento(fattura);
			
			riferimenti.add(riferimento);
		}
					
					
					
			
		} 
		finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
	return riferimenti;
	
	
	
}

















public synchronized String getPathByID(int id) throws SQLException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	
	String viewOrdineCliente = " SELECT path from prodotto  WHERE IdProdotto = ?";
	String path="";
	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(viewOrdineCliente);
		preparedStatement.setInt(1,id);
		
		ResultSet result =  preparedStatement.executeQuery();
		
		
		while(result.next()) {
			path=result.getString("path");
		}
		
		
			
	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
	return path;
	
	
	
}

















	
	
	
	
	
	
	
	
	//serve per vedere se un venditore ha creato o no il negozio
	public  synchronized boolean isUsernameNegozio (String usernameVenditore) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String isUsernameVend ="SELECT usernameVenditore" + UserModelDS.TABLE_NEGOZIO
	               +"where usernameVenditore=?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(isUsernameVend);
			
			preparedStatement.setString(1,usernameVenditore);
			ResultSet rs=preparedStatement.executeQuery();
			
			
			/*se Ë presente il nome del negozio e il nome del categoria retun true
			 * altrimenti false  */
			if(rs.next()) return true;
			else return false;

		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		
	}
	
	//negozio registrazione
		public synchronized void addNegozio(NegozioBean negozio) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String insertNegozio = "INSERT INTO " + UserModelDS.TABLE_NEGOZIO
					+ "(nome,usernameVenditore,design,colore,Piva,dataIscrizione,descrizione,via,citt‡,cap,logo)"
					+"VALUES(?,?,?,?,?,?,?,?,?,?,?)";

			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertNegozio);
				
				preparedStatement.setString(1,negozio.getNomeNegozio());
				preparedStatement.setString(2,negozio.getUsernameVenditore());
				preparedStatement.setString(3,negozio.getDesign());
				preparedStatement.setString(4,negozio.getColore());
				preparedStatement.setString(5,negozio.getPartitaIva());
				preparedStatement.setString(6,negozio.getDataIscrizione());
				preparedStatement.setString(7, negozio.getDescrizione());
				preparedStatement.setString(8, negozio.getVia());
				preparedStatement.setString(9, negozio.getCitta());
				preparedStatement.setString(10,negozio.getCap());
				preparedStatement.setString(11,negozio.getLogo());

				preparedStatement.execute();
				
				connection.commit();

			} finally {
				try {
					if(preparedStatement!=null)
						preparedStatement.close();
				} finally {
					if(connection!=null)
						connection.close();
				}
			}
		}	
		
		// recupero di tutti i prodotti per ordinamento.
		public synchronized ArrayProdotti doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			ArrayProdotti lista = new ArrayProdotti();
			
			String selectSQL = "SELECT * FROM " + UserModelDS.TABLE_PRODOTTO;
		
			if (order != null && !order.equals("")) {
				selectSQL += " ORDER BY " + order;
			}
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
		
				ResultSet rs = preparedStatement.executeQuery();
		
				while (rs.next()) {
					ProdottoBean prodotto = new ProdottoBean();
		
					prodotto.setIdProdotto(rs.getInt("idProdotto"));
					prodotto.setNomeNegozio(rs.getString("Nome_Negozio"));
					prodotto.setNomeCategoria(rs.getString("Nome_Categoria"));
					prodotto.setIva(rs.getInt("iva"));
					prodotto.setPrezzo(rs.getFloat("prezzo"));
					prodotto.setNome(rs.getString("nome"));
					prodotto.setQuantita(rs.getInt("qta"));
					prodotto.setSconto(rs.getInt("sconto"));
					prodotto.setDescrizione(rs.getString("descrizione"));
					prodotto.setPath(rs.getString("path"));
					lista.add(prodotto);
					
				}
		
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return lista;
		}
		
		
		//modifica prezzo prodotto
		public synchronized boolean updatePrezzo(String nomeNegozio,int idProdotto,String prezzo) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateProdotto = "UPDATE" + UserModelDS.TABLE_PRODOTTO 
					                 +"SET prezzo=?"
					                 +"where Nome_Negozio = ? and idProdotto= ?";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateProdotto);
				preparedStatement.setString(1,prezzo);
				preparedStatement.setString(2,nomeNegozio);
				preparedStatement.setInt(3,idProdotto);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
				
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
				return flag;
		}
		
		public synchronized boolean updateQuantita(String nomeNegozio,String nomeCategoria,int idProdotto,int qta) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateQuantita = "UPDATE " + UserModelDS.TABLE_PRODOTTO 
					                 +" SET qta=?"
					                 +" where Nome_Negozio = ? and Nome_Categoria = ? and idProdotto= ?";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateQuantita);
				preparedStatement.setInt(1,qta);
				preparedStatement.setString(2,nomeNegozio);
				preparedStatement.setString(3,nomeCategoria);
				preparedStatement.setInt(4,idProdotto);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
				
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
				return flag;
		}
		
		public synchronized boolean updateSconto(String nomeNegozio,int idProdotto,String sconto) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateSconto = "UPDATE" + UserModelDS.TABLE_PRODOTTO 
					                 +"SET sconto=?"
					                 +"where Nome_Negozio = ? and idProdotto= ?";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateSconto);
				preparedStatement.setString(1,sconto);
				preparedStatement.setString(2,nomeNegozio);
				preparedStatement.setInt(3,idProdotto);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
				
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return flag;
				
		}
		
		
		public synchronized boolean updateIva(String nomeNegozio,int idProdotto,String iva) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateIva = "UPDATE" + UserModelDS.TABLE_PRODOTTO 
					                 +"SET iva=?"
					                 +"where Nome_Negozio = ? and idProdotto= ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateIva);
				preparedStatement.setString(1,iva);
				preparedStatement.setString(2,nomeNegozio);
				preparedStatement.setInt(3,idProdotto);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
				
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
				return flag;
		}
		
		
		public synchronized boolean updateDescrizione(String nomeNegozio,int idProdotto,String descrizione) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateDescrizione = "UPDATE" + UserModelDS.TABLE_PRODOTTO 
					                 +"SET descrizione=?"
					                 +"where Nome_Negozio = ? and  idProdotto= ?";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateDescrizione);
				preparedStatement.setString(1,descrizione);
				preparedStatement.setString(2,nomeNegozio);
				preparedStatement.setInt(3,idProdotto);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
				
				flag=true;	
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
				return flag;
		}
		
		public synchronized boolean updatePasswordCliente(String passwordNew,String username) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updatePasswordCliente = "UPDATE" + UserModelDS.TABLE_CLIENTE 
					                 +"SET password=?"
					                 +"where username = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updatePasswordCliente);
				preparedStatement.setString(1,passwordNew);
				preparedStatement.setString(2,username);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
				
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return flag;	
		}
		
		
		public synchronized boolean updateEmailCliente(String emailNew,String username) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateEmailCliente = "UPDATE" + UserModelDS.TABLE_CLIENTE 
					                 +"SET email=?"
					                 +"where username = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateEmailCliente);
				preparedStatement.setString(1,emailNew);
				preparedStatement.setString(2,username);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
		
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
				return flag;
		}
		
		
		public synchronized boolean updateCittaCliente(String cittaNew,String username) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateCittaCliente = "UPDATE" + UserModelDS.TABLE_CLIENTE 
					                 +"SET citta=?"
					                 +"where username = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateCittaCliente);
				preparedStatement.setString(1,cittaNew);
				preparedStatement.setString(2,username);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
				
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
				return flag;
		}
		
		
		public synchronized boolean updateViaCliente(String viaNew,String username) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateViaCliente = "UPDATE" + UserModelDS.TABLE_CLIENTE 
					                 +"SET via=?"
					                 +"where username = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateViaCliente);
				preparedStatement.setString(1,viaNew);
				preparedStatement.setString(2,username);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
		
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
				return flag;
		}
		
		
		public synchronized boolean updateCapCliente(String capNew,String username) throws SQLException {
		
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateCapCliente = "UPDATE" + UserModelDS.TABLE_CLIENTE 
					                 +"SET cap=?"
					                 +"where username = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateCapCliente);
				preparedStatement.setString(1,capNew);
				preparedStatement.setString(2,username);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
		
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		return flag;
	}
		
		
		public synchronized boolean updateTelefonoCliente(String telefonoNew,String username) throws SQLException {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateTelefonoCliente = "UPDATE" + UserModelDS.TABLE_CLIENTE 
					                 +"SET telefono=?"
					                 +"where username = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateTelefonoCliente);
				preparedStatement.setString(1,telefonoNew);
				preparedStatement.setString(2,username);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
		
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		return flag;
	}
		
		
		public synchronized boolean updatePasswordVenditore(String passwordNew,String username) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updatePasswordVenditore = "UPDATE" + UserModelDS.TABLE_VENDITORE
					                 +"SET password=?"
					                 +"where username = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updatePasswordVenditore);
				preparedStatement.setString(1,passwordNew);
				preparedStatement.setString(2,username);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
		
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return flag;
		}
		
		
		public synchronized boolean updateEmailVenditore(String emailNew,String username) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateEmailVenditore = "UPDATE" + UserModelDS.TABLE_VENDITORE
					                 +"SET email=?"
					                 +"where username = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateEmailVenditore);
				preparedStatement.setString(1,emailNew);
				preparedStatement.setString(2,username);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
		
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return flag;
		}
		
		public synchronized boolean updateCitt‡Venditore(String cittaNew,String username) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateCittaVenditore = "UPDATE" + UserModelDS.TABLE_VENDITORE 
					                 +"SET citta=?"
					                 +"where username = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateCittaVenditore);
				preparedStatement.setString(1,cittaNew);
				preparedStatement.setString(2,username);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
		
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
				return flag;
		}
		
		public synchronized boolean updateViaVenditore(String viaNew,String username) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateViaVenditore = "UPDATE" + UserModelDS.TABLE_VENDITORE
					                 +"SET via=?"
					                 +"where username = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateViaVenditore);
				preparedStatement.setString(1,viaNew);
				preparedStatement.setString(2,username);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
		
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
				return flag;
		}
		
		public synchronized boolean updateCapVenditore(String capNew,String username) throws SQLException {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateCapVenditore = "UPDATE" + UserModelDS.TABLE_VENDITORE
					                 +"SET cap=?"
					                 +"where username = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateCapVenditore);
				preparedStatement.setString(1,capNew);
				preparedStatement.setString(2,username);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
		
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		return flag;
	}
		
	public synchronized boolean updateTelefonoVenditore(String telefonoNew,String username) throws SQLException {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String updateTelefonoVenditore = "UPDATE" + UserModelDS.TABLE_VENDITORE
					                 +"SET telefono=?"
					                 +"where username = ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(updateTelefonoVenditore);
				preparedStatement.setString(1,telefonoNew);
				preparedStatement.setString(2,username);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
		
				flag=true;
				}
		
			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		return flag;
	}

	/* nomeNegozio chiave nel datbase nella tabella negozio */
	   public synchronized boolean updateNomeNegozio(String nomeNegozioPrec,String nomeNegozioNew) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag=false;
		
		String update = "UPDATE" + UserModelDS.TABLE_NEGOZIO
				                 +"SET nome=?"
				                 +"where nome= ? ";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(update);
			preparedStatement.setString(1,nomeNegozioPrec);
			preparedStatement.setString(2,nomeNegozioNew);
			
			preparedStatement.executeUpdate();
			
			connection.commit();

			flag=true;
			}

		 finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return flag;
	   }
		
	   public synchronized boolean updateDesignNegozio(String design,String nomeNegozio) throws SQLException {
		
		   Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String update = "UPDATE" + UserModelDS.TABLE_NEGOZIO
					                 +"SET design= ?"
					                 +"where nome= ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(update);
				preparedStatement.setString(1,design);
				preparedStatement.setString(2,nomeNegozio);
				
				preparedStatement.executeUpdate();
				
				connection.commit();

				flag=true;
				}

			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			
		   return flag;
		}
	   
	   public synchronized boolean updateColoreNegozio(String colore,String nomeNegozio) throws SQLException {
		   
		   Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String update = "UPDATE" + UserModelDS.TABLE_NEGOZIO
					                 +"SET colore= ?"
					                 +"where nome= ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(update);
				preparedStatement.setString(1,colore);
				preparedStatement.setString(2,nomeNegozio);
				
				preparedStatement.executeUpdate();
				
				connection.commit();

				flag=true;
				}

			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			
		   return flag;
		   
	   }
	   
	   
	public synchronized boolean updatePartitaIvaNegozio(String partitaIva,String nomeNegozio) throws SQLException {
		   
		    Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String update = "UPDATE" + UserModelDS.TABLE_NEGOZIO
					                 +"SET P.iva= ?"
					                 +"where nome= ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(update);
				preparedStatement.setString(1,partitaIva);
				preparedStatement.setString(2,nomeNegozio);
				
				preparedStatement.executeUpdate();
				
				connection.commit();

				flag=true;
				}

			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			
		   return flag;
		   
	   }
	   
	   
	 public synchronized boolean updateDecrizioneNegozio(String descrizione,String nomeNegozio) throws SQLException {
		
		 Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String update = "UPDATE" + UserModelDS.TABLE_NEGOZIO
					                 +"SET descrizione= ?"
					                 +"where nome= ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(update);
				preparedStatement.setString(1,descrizione);
				preparedStatement.setString(2,nomeNegozio);
				
				preparedStatement.executeUpdate();
				
				connection.commit();

				flag=true;
				}

			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			
		return flag;
	}
	 
	 public synchronized boolean updateViaNegozio(String via,String nomeNegozio) throws SQLException {
		 Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String update = "UPDATE" + UserModelDS.TABLE_NEGOZIO
					                 +"SET via= ?"
					                 +"where nome= ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(update);
				preparedStatement.setString(1,via);
				preparedStatement.setString(2,nomeNegozio);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
				
				flag=true;
				}

			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		 
		 return flag;
	 }
	 
	 
	 public synchronized boolean updateCittaNegozio(String citta,String nomeNegozio) throws SQLException {
		 
		 Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String update = "UPDATE" + UserModelDS.TABLE_NEGOZIO
					                 +"SET citta= ?"
					                 +"where nome= ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(update);
				preparedStatement.setString(1,citta);
				preparedStatement.setString(2,nomeNegozio);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
				
				flag=true;
				}

			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		 
		 
		 return flag;
		 
	 }
	 
	 public synchronized boolean updateCapNegozio(String cap,String nomeNegozio) throws SQLException {

		    Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String update = "UPDATE" + UserModelDS.TABLE_NEGOZIO
					                 +"SET cap= ?"
					                 +"where nome= ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(update);
				preparedStatement.setString(1,cap);
				preparedStatement.setString(2,nomeNegozio);
				
				preparedStatement.executeUpdate();
				
				connection.commit();

				flag=true;
				}

			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		
			return flag;
	 }
	 
 public synchronized boolean updateLogoNegozio(String nomeNegozio,String logo) throws SQLException {
		 
		 Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String update = "UPDATE " + UserModelDS.TABLE_NEGOZIO
					                 +" SET logo= ? "
					                 +" where nome= ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(update);
				preparedStatement.setString(1,logo);
				preparedStatement.setString(2,nomeNegozio);
				
				preparedStatement.executeUpdate();
				
				connection.commit();

				flag=true;
				}

			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			 }
			return flag;
	 }
	
	 
 
 
 
 

 public synchronized boolean updatePathCategoria(String nomeNegozio,String nomeCategoria,String logo) throws SQLException {
	 
	 Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag=false;
		
		String update = "UPDATE " + UserModelDS.TABLE_CATEGORIA
				                 +" SET path= ? "
				                 +" where nomeNeg= ? AND nomeCategoria=? ";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(update);
			preparedStatement.setString(1,logo);
			preparedStatement.setString(2,nomeNegozio);
			preparedStatement.setString(3,nomeCategoria);
			
			preparedStatement.executeUpdate();
			
			connection.commit();

			flag=true;
			}

		 finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		 }
		return flag;
 }
 
 
 
 
 
 
 
 
 
 
 

 public synchronized boolean updatePathProdotto(String nomeNegozio,String nomeCategoria,int id,String logo) throws SQLException {
	 
	 Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag=false;
		
		
		String update = "UPDATE " + UserModelDS.TABLE_PRODOTTO
				                 +" SET path= ? "
				                 +" where Nome_Negozio= ? AND Nome_Categoria=? AND IdProdotto=?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(update);
			preparedStatement.setString(1,logo);
			preparedStatement.setString(2,nomeNegozio);
			preparedStatement.setString(3,nomeCategoria);
			preparedStatement.setInt(4,id);
			
			preparedStatement.executeUpdate();
			
			connection.commit();

			flag=true;
			}

		 finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		 }
		return flag;
 }
 
 
 
 
 

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
	 //aggiunta fattura
	 public synchronized void addFattura(FatturaBean fattura) throws SQLException {
		 
		 Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String insertFattura= "INSERT INTO " + UserModelDS.TABLE_FATTURA
					+ " ( username_Cliente, dataOrdine,viaDestinazione, citt‡Destinazione, capDestinazione) "
					+"VALUES(?,?,?,?,?)";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertFattura,Statement.RETURN_GENERATED_KEYS);
				
				preparedStatement.setString(1, fattura.getUsername_Cliente());
				preparedStatement.setString(2, fattura.getDataOrdine());
				preparedStatement.setString(3, fattura.getViaDestinazione());
				preparedStatement.setString(4, fattura.getCittaDestinazione());
				preparedStatement.setString(5, fattura.getCapDestinazione());

				preparedStatement.execute();
				
				int last_inserted_id=0;
				
				ResultSet rs = preparedStatement.getGeneratedKeys();
	            if(rs.next())
	            	 last_inserted_id = rs.getInt(1);
	            
	            fattura.setNumFattura( last_inserted_id);
				
	            connection.commit();

				}

			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			 }

	 }		
	 
	 
	 //aggiunta riferimento
	 public synchronized void addRiferimento(RiferimentoBean riferimento) throws SQLException {
		 
		 Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			String insertRiferimento= "INSERT INTO " + UserModelDS.TABLE_RIFERIMENTO
					+ "(ID_Prodotto, numero_Fattura, Nome_Negozio, Nome_Categoria, nota,QtaOrdinata,"
					+ "sconto, prezzoUnitario, IVA)"
					+"VALUES(?,?,?,?,?,?,?,?,?)";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertRiferimento);
				
				preparedStatement.setInt(1, riferimento.getId_prodotto());
				preparedStatement.setInt(2, riferimento.getNumero_Fattura());
				preparedStatement.setString(3, riferimento.getNome_Negozio());
				preparedStatement.setString(4, riferimento.getNome_Categoria());
				preparedStatement.setString(5, riferimento.getNote());
				preparedStatement.setInt(6, riferimento.getQtaOrdinata());
				preparedStatement.setInt(7, riferimento.getSconto());
				preparedStatement.setFloat(8, riferimento.getPrezzoUnitario());
				preparedStatement.setInt(9, riferimento.getIva());
				

				preparedStatement.execute();
				
				connection.commit();

				}

			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			 }
	 }

	//Fatture totali 
	public synchronized ArrayFattura fatturaTot() throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		FatturaBean bean=new FatturaBean();
		ArrayFattura lista=new ArrayFattura();
		String fatt ="SELECT *" + UserModelDS.TABLE_FATTURA;
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(fatt);
			
	
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next()) 
			{
				bean.setNumFattura(rs.getInt("numeroFattura"));
				bean.setNomeNegozio(rs.getString("nomeNegozio"));
				bean.setUsername_Cliente(rs.getString("username_Cliente"));
				bean.setDataOrdine(rs.getString("dataOrdine"));
				bean.setViaDestinazione(rs.getString("viaDestinazione"));
				bean.setCittaDestinazione(rs.getString("citt‡Destinazione"));
				bean.setCapDestinazione(rs.getString("capDestinazione"));
				
				lista.add(bean);
			}
			
	
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		return lista;
		
	}
	
	
	//tabella riferimento per numero fattura
	public synchronized ArrayRiferimento riferimentoTot(String numero_Fattura) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		RiferimentoBean bean=new RiferimentoBean();
		ArrayRiferimento lista=new ArrayRiferimento();
		String rif ="SELECT *" + UserModelDS.TABLE_RIFERIMENTO+"WHERE numero_Fattura=?";
		
		try {
	
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(rif);
			preparedStatement.setString(1, numero_Fattura);
	
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next()) 
			{
				bean.setId_prodotto(rs.getInt("ID_Prodotto"));
				bean.setNumero_Fattura(rs.getInt("numero_Fattura"));
				bean.setNome_Negozio(rs.getString("Nome_Negozio"));
				bean.setNome_Categoria(rs.getString("Nome_Categoria"));
				bean.setQtaOrdinata(rs.getInt("QtaOrdinata"));
				bean.setSconto(rs.getInt("Sconto"));
				bean.setPrezzoUnitario(rs.getFloat("prezzoUnitario"));
				bean.setIva(rs.getInt("IVA"));
			
				lista.add(bean);
			}
			
	
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		return lista;
	}
	
		//Fatture per utente 
		public synchronized ArrayFattura fatturaUtente(String username_Cliente) throws SQLException
		{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			FatturaBean bean=new FatturaBean();
			ArrayFattura lista=new ArrayFattura();
			String fatt ="SELECT *" + UserModelDS.TABLE_FATTURA+"WHERE username_Cliente=?";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(fatt);
				preparedStatement.setString(1, username_Cliente);
	
				ResultSet rs=preparedStatement.executeQuery();
				
				while(rs.next()) 
				{
					bean.setNumFattura(rs.getInt("numeroFattura"));
					bean.setNomeNegozio(rs.getString("nomeNegozio"));
					bean.setUsername_Cliente(rs.getString("username_Cliente"));
					bean.setDataOrdine(rs.getString("dataOrdine"));
					bean.setViaDestinazione(rs.getString("viaDestinazione"));
					bean.setCittaDestinazione(rs.getString("citt‡Destinazione"));
					bean.setCapDestinazione(rs.getString("capDestinazione"));
					
					lista.add(bean);
				}
				
	
			} finally {
				try {
					if(preparedStatement!=null)
						preparedStatement.close();
				} finally {
					if(connection!=null)
						connection.close();
				}
			}
			return lista;
			
		}
		
		
		//Fatture tra date Attenzione date ordine giorno mese anno tra apici. AAAA/MM/GG
		public synchronized ArrayFattura fatturaUtente(String dataInizio, String dataFine) throws SQLException
		{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			FatturaBean bean=new FatturaBean();
			ArrayFattura lista=new ArrayFattura();
			String fatt ="SELECT *" + UserModelDS.TABLE_FATTURA+"WHERE dataOrdine BETWEEN ? AND ?";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(fatt);
				preparedStatement.setString(1, dataInizio);
				preparedStatement.setString(2, dataFine);
	
				ResultSet rs=preparedStatement.executeQuery();
				
				while(rs.next()) 
				{
					bean.setNumFattura(rs.getInt("numeroFattura"));
					bean.setNomeNegozio(rs.getString("nomeNegozio"));
					bean.setUsername_Cliente(rs.getString("username_Cliente"));
					bean.setDataOrdine(rs.getString("dataOrdine"));
					bean.setViaDestinazione(rs.getString("viaDestinazione"));
					bean.setCittaDestinazione(rs.getString("citt‡Destinazione"));
					bean.setCapDestinazione(rs.getString("capDestinazione"));
					
					lista.add(bean);
				}
				
	
			} finally {
				try {
					if(preparedStatement!=null)
						preparedStatement.close();
				} finally {
					if(connection!=null)
						connection.close();
				}
			}
			return lista;
			
		}

		public synchronized Collection<NegozioBean> getAllNegozio() throws SQLException {
	    	  
	    	  Connection connection = null;
	     	  PreparedStatement preparedStatement = null;
	     	  
	  		  Collection<NegozioBean> listaNegozi = new LinkedList<NegozioBean>();
	  		  
	  		  String selectSQL = "SELECT * FROM " + UserModelDS.TABLE_NEGOZIO;
	  		  
	  		try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					NegozioBean negozioBean = new NegozioBean();

					negozioBean.setNomeNegozio(rs.getString("nome"));
					negozioBean.setUsernameVenditore(rs.getString("usernameVenditore"));
					negozioBean.setDesign(rs.getString("design"));
					negozioBean.setColore(rs.getString("colore"));
					negozioBean.setPartitaIva(rs.getString("Piva"));
					negozioBean.setDataIscrizione(rs.getString("dataIscrizione"));
					negozioBean.setVia(rs.getString("via"));
					negozioBean.setCitta(rs.getString("citt‡"));
					negozioBean.setCap(rs.getString("cap"));
					negozioBean.setLogo(rs.getString("logo"));
					
					
					listaNegozi.add(negozioBean);
					
					
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return listaNegozi;
		}
		
		
		

		public synchronized NegozioBean getNegozio(String usernameVenditore) throws SQLException {
	    	  
	    	  Connection connection = null;
	     	  PreparedStatement preparedStatement = null;
	     	  NegozioBean negozioBean = null;
	     	  
	  		  String selectSQL = "SELECT * FROM " + UserModelDS.TABLE_NEGOZIO+" WHERE usernameVenditore=?";
	  		  
	  		try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, usernameVenditore);
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					negozioBean=new NegozioBean();
					negozioBean.setNomeNegozio(rs.getString("nome"));
					negozioBean.setUsernameVenditore(rs.getString("usernameVenditore"));
					negozioBean.setDesign(rs.getString("design"));
					negozioBean.setColore(rs.getString("colore"));
					negozioBean.setPartitaIva(rs.getString("Piva"));
					negozioBean.setDataIscrizione(rs.getString("dataIscrizione"));
					negozioBean.setVia(rs.getString("via"));
					negozioBean.setCitta(rs.getString("citt‡"));
					negozioBean.setCap(rs.getString("cap"));
					negozioBean.setLogo(rs.getString("logo"));
					
					
					
					
					
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return negozioBean;
		}
		
		
		/*
		public synchronized NegozioBean getNegozioByName(String negozio) throws SQLException {
	    	  
	    	  Connection connection = null;
	     	  PreparedStatement preparedStatement = null;
	     	  NegozioBean negozioBean = null;
	     	  
	  		  String selectSQL = "SELECT * FROM " + UserModelDS.TABLE_NEGOZIO+" WHERE nome=?";
	  		  
	  		try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, negozio);
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					negozioBean=new NegozioBean();
					negozioBean.setNomeNegozio(rs.getString("nome"));
					negozioBean.setUsernameVenditore(rs.getString("usernameVenditore"));
					negozioBean.setDesign(rs.getString("design"));
					negozioBean.setColore(rs.getString("colore"));
					negozioBean.setPartitaIva(rs.getString("Piva"));
					negozioBean.setDataIscrizione(rs.getString("dataIscrizione"));
					negozioBean.setVia(rs.getString("via"));
					negozioBean.setCitta(rs.getString("citt‡"));
					negozioBean.setCap(rs.getString("cap"));
					negozioBean.setLogo(rs.getString("logo"));
					
					
					
					
					
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return negozioBean;
		}
		
		*/
		
		
		
		public synchronized Collection<CategoriaBean> getAllCategoryByShop(String nomeNegozio) throws SQLException {
	    	  
	    	  Connection connection = null;
	     	  PreparedStatement preparedStatement = null;
	     	  
	  		  Collection<CategoriaBean> listaCategorie = new LinkedList<CategoriaBean>();
	  		  
	  		  String selectSQL = "SELECT * FROM categoria WHERE nomeNeg=?";
	  		  
	  		try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1,nomeNegozio);
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					CategoriaBean categoriaBean = new CategoriaBean();

					categoriaBean.setNomeNegozio(rs.getString("nomeNeg"));
					categoriaBean.setNomeCategoria(rs.getString("nomeCategoria"));
					categoriaBean.setDescrizione(rs.getString("descrizione"));
					categoriaBean.setPath(rs.getString("path"));
					
					
					listaCategorie.add(categoriaBean);
					
					
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return listaCategorie;
		}
		
		
		
		
		
		
		public synchronized Collection<CategoriaBean> getAllCategoryBySeller(String venditore) throws SQLException {
	    	  
	    	  Connection connection = null;
	     	  PreparedStatement preparedStatement = null;
	     	  
	  		  Collection<CategoriaBean> listaCategorie = new LinkedList<CategoriaBean>();

	  		  String selectSQL = "SELECT categoria.* FROM categoria,negozio WHERE nomeNeg=nome AND usernameVenditore=?";
	  		  
	  		try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1,venditore);
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					CategoriaBean categoriaBean = new CategoriaBean();

					categoriaBean.setNomeNegozio(rs.getString("nomeNeg"));
					categoriaBean.setNomeCategoria(rs.getString("nomeCategoria"));
					categoriaBean.setDescrizione(rs.getString("descrizione"));
					categoriaBean.setPath(rs.getString("path"));
					
					
					listaCategorie.add(categoriaBean);
					
					
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return listaCategorie;
		}
		
		
		
		
		
		
		
		public synchronized Collection<ProdottoBean> getAllProductByShopCategory(String nomeNegozio,String nomeCategoria) throws SQLException {
	    	  
	    	  Connection connection = null;
	     	  PreparedStatement preparedStatement = null;
	     	  
	  		  Collection<ProdottoBean> listaProdotti = new LinkedList<ProdottoBean>();
	  		  
	  		  String selectSQL = "SELECT * FROM prodotto WHERE Nome_Negozio=? AND Nome_Categoria=?";
	  		  
	  		try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1,nomeNegozio);
				preparedStatement.setString(2,nomeCategoria);
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					ProdottoBean prodottoBean = new ProdottoBean();

					prodottoBean.setNomeNegozio(rs.getString("Nome_Negozio"));
					prodottoBean.setDescrizione(rs.getString("descrizione"));
					prodottoBean.setIdProdotto(rs.getInt("IdProdotto"));
					prodottoBean.setIva(rs.getInt("iva"));
					prodottoBean.setNome(rs.getString("nome"));
					prodottoBean.setNomeCategoria(rs.getString("Nome_Categoria"));
					prodottoBean.setPath(rs.getString("path"));
					prodottoBean.setPrezzo(rs.getFloat("prezzo"));
					prodottoBean.setQuantita(rs.getInt("qta"));
					prodottoBean.setSconto(rs.getInt("sconto"));
					
					listaProdotti.add(prodottoBean);
					
					
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return listaProdotti;
		}
		
		public synchronized ProdottoBean getProductByShopAndCategory(String nomeNegozio,String nomeCategoria,int idProdotto) throws SQLException {
	    	  
	    	  Connection connection = null;
	     	  PreparedStatement preparedStatement = null;
	     		  
	     	  ProdottoBean prodottoBean=null;
	  		  
	  		  String selectSQL = "SELECT * FROM prodotto WHERE Nome_Negozio=? AND Nome_Categoria=? AND idProdotto=?";
	  		  
	  		try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1,nomeNegozio);
				preparedStatement.setString(2,nomeCategoria);
				preparedStatement.setInt(3,idProdotto);
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					prodottoBean = new ProdottoBean();

					prodottoBean.setNomeNegozio(rs.getString("Nome_Negozio"));
					prodottoBean.setDescrizione(rs.getString("descrizione"));
					prodottoBean.setIdProdotto(rs.getInt("IdProdotto"));
					prodottoBean.setIva(rs.getInt("iva"));
					prodottoBean.setNome(rs.getString("nome"));
					prodottoBean.setNomeCategoria(rs.getString("Nome_Categoria"));
					prodottoBean.setPath(rs.getString("path"));
					prodottoBean.setPrezzo(rs.getFloat("prezzo"));
					prodottoBean.setQuantita(rs.getInt("qta"));
					prodottoBean.setSconto(rs.getInt("sconto"));
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return prodottoBean;
		}
		
		
		
		
		
		
		
		
		public synchronized Collection<ProdottoBean> getAllProductBySellerCategory(String venditore,String categoria) throws SQLException {
	    	  
	    	  Connection connection = null;
	     	  PreparedStatement preparedStatement = null;
	     	  
	  		  Collection<ProdottoBean> listaProdotti = new LinkedList<ProdottoBean>();
	  		  String selectSQL = "SELECT prodotto.* FROM prodotto,negozio WHERE Nome_Negozio=negozio.nome AND usernameVenditore=? AND Nome_Categoria=?";
	  		
	  		
	  		  
	  		try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1,venditore);
				preparedStatement.setString(2,categoria);
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					ProdottoBean prodottoBean = new ProdottoBean();

					prodottoBean.setNomeNegozio(rs.getString("Nome_Negozio"));
					prodottoBean.setDescrizione(rs.getString("descrizione"));
					prodottoBean.setIdProdotto(rs.getInt("IdProdotto"));
					prodottoBean.setIva(rs.getInt("iva"));
					prodottoBean.setNome(rs.getString("nome"));
					prodottoBean.setNomeCategoria(rs.getString("Nome_Categoria"));
					prodottoBean.setPath(rs.getString("path"));
					prodottoBean.setPrezzo(rs.getFloat("prezzo"));
					prodottoBean.setQuantita(rs.getInt("qta"));
					prodottoBean.setSconto(rs.getInt("sconto"));
					
					listaProdotti.add(prodottoBean);
					
					
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return listaProdotti;
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public synchronized Collection<ClienteBean> getAllCliente() throws SQLException {
			
			Connection connection = null;
	     	  PreparedStatement preparedStatement = null;
	     	  
	  		  Collection<ClienteBean> listaCliente = new LinkedList<ClienteBean>();
	  		  
	  		  String selectSQL = "SELECT * FROM " + UserModelDS.TABLE_CLIENTE;
	  		  
	  		try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					ClienteBean cliente = new ClienteBean();
                    
					cliente.setUsername(rs.getString("username"));
					cliente.setPassword(rs.getString("password"));
					cliente.setNome(rs.getString("nome"));
					cliente.setCognome(rs.getString("cognome"));
					cliente.setEmail(rs.getString("email"));
					cliente.setSesso(rs.getString("sesso"));
					cliente.setTelefono(rs.getString("telefono"));
					cliente.setVia(rs.getString("via"));
					cliente.setCitta(rs.getString("citt‡"));
					cliente.setCap(rs.getString("cap"));
				
					
					
					listaCliente.add(cliente);
					
					
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return listaCliente;
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		public synchronized CategoriaBean getCategoria(String nomeNegozio,String nomeCategoria) throws SQLException {

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			CategoriaBean bean=new CategoriaBean();
			
			String categ ="SELECT * FROM categoria"
		               +" WHERE nomeNeg=? AND nomeCategoria=? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(categ);
				
				preparedStatement.setString(1,nomeNegozio);
				preparedStatement.setString(2,nomeCategoria);
				ResultSet rs=preparedStatement.executeQuery();
				
				while(rs.next())
				{
				
					String negozio=rs.getString("nomeNeg");
					String categoria=rs.getString("nomeCategoria");
					String descrizione=rs.getString("descrizione");
					String path=rs.getString("path");
					bean.setNomeNegozio(negozio);
					bean.setNomeCategoria(categoria);
					bean.setDescrizione(descrizione);
					bean.setPath(path);
				}
				
				connection.commit();

			} finally {
				try {
					if(preparedStatement!=null)
						preparedStatement.close();
				} finally {
					if(connection!=null)
						connection.close();
				}
			}
			
			return bean;
			
		}













		public synchronized boolean updateDescrizioneCategoria(String nomeNegozio, String nomeCategoria, String descrizione) throws SQLException
		{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String update = "UPDATE " + UserModelDS.TABLE_CATEGORIA
					                 +" SET descrizione= ? "
					                 +" where nomeNeg= ? AND nomeCategoria=? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(update);
				preparedStatement.setString(1,descrizione);
				preparedStatement.setString(2,nomeNegozio);
				preparedStatement.setString(3,nomeCategoria);
				
				preparedStatement.executeUpdate();
				
				connection.commit();

				flag=true;
				}

			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			 }
			return flag;
			
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	public synchronized Collection<VenditoreBean> getAllVenditore() throws SQLException {
		
		Connection connection = null;
   	  PreparedStatement preparedStatement = null;
   	  
		  Collection<VenditoreBean> listaVenditore = new LinkedList<VenditoreBean>();
		  
		  String selectSQL = "SELECT * FROM " + UserModelDS.TABLE_VENDITORE;
		  
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				VenditoreBean venditore = new VenditoreBean();
              
				venditore.setUsername(rs.getString("username"));
				venditore.setPassword(rs.getString("password"));
				venditore.setNome(rs.getString("nome"));
				venditore.setCognome(rs.getString("cognome"));
				venditore.setEmail(rs.getString("email"));
				venditore.setSesso(rs.getString("sesso"));
				venditore.setTelefono(rs.getString("telefono"));
				venditore.setVia(rs.getString("via"));
				venditore.setCitta(rs.getString("citt‡"));
				venditore.setCap(rs.getString("cap"));
			
				
				
				listaVenditore.add(venditore);
				
				
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return listaVenditore;
			
		
     }
	
	
	
	

	public synchronized boolean updateQtaById(int idProdotto,int qta) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag=false;
		
		String updateProdotto = " UPDATE " + UserModelDS.TABLE_PRODOTTO 
				                 +" SET qta=? "
				                 +" where  IdProdotto= ? ";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateProdotto);
			preparedStatement.setFloat(1,qta);
			preparedStatement.setInt(2,idProdotto);
			
			preparedStatement.executeUpdate();
			
			connection.commit();
			
			flag=true;
			}
	
		 finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
			return flag;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public synchronized ProdottoBean getProductById(int idProdotto) throws SQLException {
  	  
  	  Connection connection = null;
   	  PreparedStatement preparedStatement = null;
   	  
   	  
   	  ProdottoBean prodottoBean=null;
		  
		  String selectSQL = "SELECT * FROM prodotto WHERE IdProdotto=?";
		  
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1,idProdotto);
			
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				prodottoBean = new ProdottoBean();

				prodottoBean.setNomeNegozio(rs.getString("Nome_Negozio"));
				prodottoBean.setDescrizione(rs.getString("descrizione"));
				prodottoBean.setIdProdotto(rs.getInt("IdProdotto"));
				prodottoBean.setIva(rs.getInt("iva"));
				prodottoBean.setNome(rs.getString("nome"));
				prodottoBean.setNomeCategoria(rs.getString("Nome_Categoria"));
				prodottoBean.setPath(rs.getString("path"));
				prodottoBean.setPrezzo(rs.getFloat("prezzo"));
				prodottoBean.setQuantita(rs.getInt("qta"));
				prodottoBean.setSconto(rs.getInt("sconto"));
			}

			connection.commit();
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return prodottoBean;
	}
	

public synchronized boolean updateProdotto(ProdottoBean bean) throws SQLException {
		 
		 Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag=false;
			
			String update = "UPDATE " + UserModelDS.TABLE_PRODOTTO
					                 +" SET iva= ? ,path= ? ,prezzo= ? , nome= ?, qta= ? , sconto= ?, descrizione= ?  "
					                 +" where IdProdotto= ? ";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(update);
				
				preparedStatement.setInt(1,bean.getIva());
				preparedStatement.setString(2,bean.getPath());
				preparedStatement.setFloat(3,bean.getPrezzo());
				preparedStatement.setString(4,bean.getNome());
				preparedStatement.setInt(5,bean.getQuantita());
				preparedStatement.setInt(6,bean.getSconto());
				preparedStatement.setString(7,bean.getDescrizione());
				preparedStatement.setInt(8,bean.getIdProdotto());
				
				
				preparedStatement.executeUpdate();
				
				connection.commit();

				flag=true;
				}

			 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			 }
			return flag;
	 }
	







public synchronized boolean updateNegozio(NegozioBean bean) throws SQLException {
	 
	 Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag=false;
		
		String update = "UPDATE " + UserModelDS.TABLE_NEGOZIO
				                 +" SET design= ? ,colore= ? ,Piva= ? , dataIscrizione= ? , descrizione= ? , via= ? , citt‡= ? ,cap= ? , logo= ? "
				                 +" where nome= ? ";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(update);
			
			preparedStatement.setString(1,bean.getDesign());
			preparedStatement.setString(2,bean.getColore());
			preparedStatement.setString(3, bean.getPartitaIva());
			preparedStatement.setString(4,bean.getDataIscrizione());
			preparedStatement.setString(5,bean.getDescrizione());
			preparedStatement.setString(6,bean.getVia());
			preparedStatement.setString(7,bean.getCitta());
			preparedStatement.setString(8,bean.getCap());
			preparedStatement.setString(9,bean.getLogo());
			preparedStatement.setString(10, bean.getNomeNegozio());
			
			
			
			
			preparedStatement.executeUpdate();
			
			connection.commit();

			flag=true;
			}

		 finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		 }
		return flag;
}


	public synchronized NegozioBean getNegozioByName(String negozio) throws SQLException {
	    	  
	    	  Connection connection = null;
	     	  PreparedStatement preparedStatement = null;
	     	  NegozioBean bean = new NegozioBean();
	     	  
	  		  String selectSQL = "SELECT * FROM " + UserModelDS.TABLE_NEGOZIO+" WHERE nome=?";
	  		  
	  		try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, negozio);
				
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					
					bean.setNomeNegozio(rs.getString("nome"));
					bean.setUsernameVenditore(rs.getString("usernameVenditore"));
					bean.setDesign(rs.getString("design"));
					bean.setColore(rs.getString("colore"));
					bean.setPartitaIva(rs.getString("Piva"));
					bean.setDataIscrizione(rs.getString("dataIscrizione"));
					bean.setVia(rs.getString("via"));
					bean.setCitta(rs.getString("citt‡"));
					bean.setCap(rs.getString("cap"));
					bean.setLogo(rs.getString("logo"));
					bean.setDescrizione(rs.getString("descrizione"));
					
					
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return bean;
		}
		
		



public synchronized ClienteBean getClient(String username) throws SQLException {
	  
	  Connection connection = null;
 	  PreparedStatement preparedStatement = null;
 	  
 	  
 	 ClienteBean bean=new ClienteBean();
		  
		  String selectSQL = "SELECT * FROM cliente WHERE username=?";
		  
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				

				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setSesso(rs.getString("sesso"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setVia(rs.getString("via"));
				bean.setCitta(rs.getString("citt‡"));
				bean.setCap(rs.getString("cap"));
				
			}

			connection.commit();
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}


public synchronized boolean updateCliente(ClienteBean bean) throws SQLException {
	 
	 Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag=false;
		
		String update = "UPDATE " + UserModelDS.TABLE_CLIENTE
				                 +" SET password= ?, email= ? , sesso= ? , telefono= ? ,via= ? , citt‡= ? ,cap= ?"
				                 +" where username= ? ";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(update);
			
			preparedStatement.setString(1, bean.getPassword());
			preparedStatement.setString(2, bean.getEmail());
			preparedStatement.setString(3, bean.getSesso());
			preparedStatement.setString(4, bean.getTelefono());
			preparedStatement.setString(5, bean.getVia());
			preparedStatement.setString(6, bean.getCitta());
			preparedStatement.setString(7, bean.getCap());
			preparedStatement.setString(8, bean.getUsername());
			
			
			
			
			preparedStatement.executeUpdate();
			
			connection.commit();

			flag=true;
			}

		 finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		 }
		return flag;
}



public synchronized VenditoreBean getVenditore(String username) throws SQLException {
	  
	  Connection connection = null;
	  PreparedStatement preparedStatement = null;
	  
	  
	 VenditoreBean bean=new VenditoreBean();
		  
		  String selectSQL = "SELECT * FROM venditore WHERE username=?";
		  
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setSesso(rs.getString("sesso"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setVia(rs.getString("via"));
				bean.setCitta(rs.getString("citt‡"));
				bean.setCap(rs.getString("cap"));
				
				
			}

			connection.commit();
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}

public synchronized boolean updateVenditore(VenditoreBean bean) throws SQLException {
	 
	 Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag=false;
		
		String update = "UPDATE " + UserModelDS.TABLE_VENDITORE
				                 +" SET password= ?, email= ? , sesso= ? , telefono= ? ,via= ? , citt‡= ? ,cap= ?"
				                 +" where username= ? ";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(update);
			
			preparedStatement.setString(1, bean.getPassword());
			preparedStatement.setString(2, bean.getEmail());
			preparedStatement.setString(3, bean.getSesso());
			preparedStatement.setString(4, bean.getTelefono());
			preparedStatement.setString(5, bean.getVia());
			preparedStatement.setString(6, bean.getCitta());
			preparedStatement.setString(7, bean.getCap());
			preparedStatement.setString(8, bean.getUsername());
			
			
			
			
			preparedStatement.executeUpdate();
			
			connection.commit();

			flag=true;
			}

		 finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		 }
		return flag;
}










public synchronized ArrayRiferimento getArrayRiferimentoDataInizioFine(String dataInizio, String dataFine, String nome) throws SQLException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	
	ArrayRiferimento riferimenti = null;
	
	String viewOrdineDate=" SELECT * from riferimento,fattura  WHERE numeroFattura=numero_Fattura AND Nome_Negozio = ? AND dataOrdine between ? and ?  ORDER BY dataOrdine DESC";
	
	
	
	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(viewOrdineDate);
		
		preparedStatement.setString(1, nome);
		preparedStatement.setString(2, dataInizio);
		preparedStatement.setString(3, dataFine);
		
		ResultSet rs =  preparedStatement.executeQuery();
		
		riferimenti=new ArrayRiferimento();
		
		
		while(rs.next()) {
			
			RiferimentoBean riferimento=new RiferimentoBean();
			
			riferimento.setId_prodotto(rs.getInt("ID_Prodotto"));
			riferimento.setNumero_Fattura(rs.getInt("numero_Fattura"));
			riferimento.setNome_Negozio(rs.getString("Nome_Negozio"));
			riferimento.setNome_Categoria(rs.getString("Nome_Categoria"));
			riferimento.setNote(rs.getString("nota"));
			riferimento.setQtaOrdinata(rs.getInt("QtaOrdinata"));
			riferimento.setSconto(rs.getInt("sconto"));
			riferimento.setPrezzoUnitario(rs.getFloat("prezzoUnitario"));
			riferimento.setIva(rs.getInt("IVA"));
			
			FatturaBean fattura=new FatturaBean();
			fattura.setNumFattura (rs.getInt("numeroFattura"));
			fattura.setUsername_Cliente(rs.getString("username_Cliente"));
			fattura.setDataOrdine(rs.getString("dataOrdine"));
			fattura.setCapDestinazione(rs.getString("capDestinazione"));
			fattura.setCittaDestinazione(rs.getString("citt‡Destinazione"));
			fattura.setViaDestinazione(rs.getString("viaDestinazione"));
			
			riferimento.setFatturaRiferimento(fattura);
			
			riferimenti.add(riferimento);
		}
					
					
					
			
		} 
		finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
	return riferimenti;
	
	
	
}


public synchronized ArrayRiferimento getArrayRiferimentoPerCliente(String client, String nome) throws SQLException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	
	ArrayRiferimento riferimenti = null;
	
	String viewOrdinePerClient=" SELECT * from riferimento,fattura  WHERE numeroFattura=numero_Fattura AND username_Cliente= ? AND Nome_Negozio = ?  ORDER BY dataOrdine DESC";
	
	
	
	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(viewOrdinePerClient);
		preparedStatement.setString(1, client);
		preparedStatement.setString(2, nome);
		
		ResultSet rs =  preparedStatement.executeQuery();
		
		riferimenti=new ArrayRiferimento();
		
		
		while(rs.next()) {
			
			RiferimentoBean riferimento=new RiferimentoBean();
			
			riferimento.setId_prodotto(rs.getInt("ID_Prodotto"));
			riferimento.setNumero_Fattura(rs.getInt("numero_Fattura"));
			riferimento.setNome_Negozio(rs.getString("Nome_Negozio"));
			riferimento.setNome_Categoria(rs.getString("Nome_Categoria"));
			riferimento.setNote(rs.getString("nota"));
			riferimento.setQtaOrdinata(rs.getInt("QtaOrdinata"));
			riferimento.setSconto(rs.getInt("sconto"));
			riferimento.setPrezzoUnitario(rs.getFloat("prezzoUnitario"));
			riferimento.setIva(rs.getInt("IVA"));
			
			FatturaBean fattura=new FatturaBean();
			fattura.setNumFattura (rs.getInt("numeroFattura"));
			fattura.setUsername_Cliente(rs.getString("username_Cliente"));
			fattura.setDataOrdine(rs.getString("dataOrdine"));
			fattura.setCapDestinazione(rs.getString("capDestinazione"));
			fattura.setCittaDestinazione(rs.getString("citt‡Destinazione"));
			fattura.setViaDestinazione(rs.getString("viaDestinazione"));
			
			riferimento.setFatturaRiferimento(fattura);
			
			riferimenti.add(riferimento);
			
		}
					
					
					
			
		} 
		finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
	return riferimenti;
	
	
	
}







}
		
		
		
		
		
		
		

	                              
		

	
		
		
	
    
	
