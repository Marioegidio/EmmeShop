<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="javax.servlet.*,model.*,java.util.*"
    pageEncoding="UTF-8"
    session="true"
%>

<%@include file="../WEB-INF/utilities/checkLogCliente.jsp" %>
<%!UserModelDS model=new UserModelDS(); %>
<%
    ArrayFattura fatture=null;
	utenteLog=(ClienteBean)session.getAttribute("utente");
	
	if(utenteLog!=null && utenteLog.getUsername()!=null )
		fatture= model.getArrayFattura(utenteLog.getUsername());
%>
<!DOCTYPE html>
<html>
    <head>
    
    	
    
    
        <meta charset='utf-8'>
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <title>EmmeShop</title>
        <link rel="icon" href="../images/favicon.ico" />
        <meta name='viewport' content='width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0'>
        <link rel='stylesheet' type='text/css' href='../css/table.css'>
        <link rel='stylesheet' type='text/css' href='../css/sidenav-nav_top/stile-nav_top.css'>
        <link rel='stylesheet' type='text/css' href='../css/sidenav-nav_top/sidenav-nav_top.css'>
        <link rel='stylesheet' type='text/css' href='../css/header.css'>
        <link rel='stylesheet' type='text/css' href='../css/content.css'>
        <link rel='stylesheet' type='text/css' href='../css/footer.css'>
        <link rel='stylesheet' type='text/css' href='../css/scrollbar.css'>
        <link rel='stylesheet' type='text/css' href='../css/slideshow.css'>
        <link rel='stylesheet' type='text/css' href='../css/translate.css'>
        <link rel='stylesheet' type='text/css' href='../css/step.css'>
        <link rel='stylesheet' type='text/css' href='../css/popup.css'>
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript" src="../js/nav/menu-nav_top.js"></script>
        <script type="text/javascript" src="../js/top.js"></script>
        <script type="text/javascript" src="../js/scrollToDiv.js"></script>
        <script type="text/javascript" src="../js/translate.js"></script>
        <script type="text/javascript" src="../js/check-chrome.js"></script>
        <script type="text/javascript" src="../js/cart.js"></script>
        <script type="text/javascript" src="http://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    </head>
    <body onresize='resize()'  id='home' >
      
      <div id="alertBox"><br>
       <center> 
        <label id="msg"></label><p>
            <input value="ok" id="btnA" type="button" >
       </center>
      </div>
      
    <div id="back">
    </div>
      
      
      
        <header>
           <%@ include file="../WEB-INF/fragment/headernegozio.jsp" %>
                  
            <div id='mySidenav' class="sidenav">
              <ul >
                <li onclick="window.open('index-cliente.jsp','_self')">
                    Negozi
                </li>
                <li><a href='../Logout' style='padding:0;text-decoration:none;color:#141414;'>Disconnetti</a>
                 
                </li>
               
              </ul>    
            </div>
        </header>
        <div class="content">
                  
              
         			
         		 
                  <br><center><h2>Elenco Ordini Effettuati</h2></center><br>
                   
                            
                            
                            
                            <%
                            	if(fatture!=null){
                            %>		
                            		<center><caption></caption>
				                   
				                            
                            
		                            <%		
				                            for(FatturaBean f : fatture.getAllFatture()){ 
				                    %>
				                     <table style='max-width:80%;'>
				                    		<thead>
				                              <tr>
				                                <th >Numero Ordine</th>
				                                <th>Via Destinazione</th>
				                                <th>Città Destinazione</th>
				                                <th>Cap Destinazione</th>
				                                <th>Data Ordine</th>
				                                <th></th>
				                              </tr>
				                            </thead>
				                            <tbody>
				                              <tr>
				                                  <td data-label="Numero Ordine"><br><%=f.getNumFattura() %></td>
					                              <td data-label="Via Destinazione"><br><%=f.getViaDestinazione() %></td>
					                              <td data-label="Città Destinazione"><br><%=f.getCittaDestinazione() %></td>
					                              <td data-label="Cap Destinazione"><br><%=f.getCapDestinazione() %></td>
					                              <td data-label="Data Ordine"><br><%= f.getDataOrdine() %></td>
					                              <td data-label="AZ"><br><img src="../images/print.png" style="cursor:pointer;width:40px;" onclick="window.open('stampa.jsp?fattura=<%= f.getNumFattura() %>','_self')"></td>
					                            
				                              </tr>
			                               </tbody>
			                               </table><br>
			                               <table style='max-width:70%;'>
				                           <thead>
				                              <tr>
				                                <th>Prodotto</th>
				                                <th>Negozio</th>
				                                <th>Categoria</th>
				                                <th>Nota</th>
				                                <th>Qta</th>
				                                <th>Sconto</th>
				                                <th>PrezzoUnitario</th>
				                                
				                              </tr>
				                            </thead>
				                            <tbody>
				                            <%		
				                            	for(RiferimentoBean r: f.getListaProdotti().getAllRiferimento()){ 
				                    		%>
				                    				<tr>
				                            		 <td data-label="Prodotto"><img  height="45px" src="../<%= model.getPathByID(r.getId_prodotto()) %>"></td>
				                            		 <td data-label="Negozio"><br><%= r.getNome_Negozio() %></td>
				                            		 <td data-label="Categoria"><br><%= r.getNome_Categoria() %></td>
				                            		 <td data-label="Nota"><br><%= r.getNote() %></td>
				                            		 <td data-label="Qta"><br><%= r.getQtaOrdinata() %></td>
				                            		 <td data-label="Sconto"><br><%= r.getSconto() %>%</td>
				                            		 <td data-label="PrezzoUnitario"><br><%= r.getPrezzoUnitario() %>€</td>
				                            		</tr>
					                        <%} %>
				                            </tbody>
				                            	  </table><br><br><br><br><br><br>
				                            
		                              		<%}%>
			                          </center>
                              <%}
                            	else{%>
                              		<br><h3>Non hai ancora effettuato ordini!</h3>
                              <%}%>
                           
                             
                  
                  
                  
              </div>
        
        <%@ include file="../WEB-INF/fragment/footer.jsp" %>
		
		
    </body>
</html>