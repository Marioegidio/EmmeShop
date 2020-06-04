<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="javax.servlet.*,model.*,java.util.*,java.text.*"
    pageEncoding="UTF-8"
    session="true"
%>

<%@include file="../WEB-INF/utilities/checkLogCliente.jsp" %>

<%
	UserModelDS model=new UserModelDS();
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
               <li onclick="window.open('ordini.jsp','_self')">
                    I miei Ordini
                </li>
                <li onclick="window.open('index-cliente.jsp','_self')">
                    Negozi
                </li>
                <li><a href='../Logout' style='padding:0;text-decoration:none;color:#141414;'>Disconnetti</a>
                 
                </li>
               
              </ul>    
            </div>
        </header>
        <div class="content">
                  
              
         			
         		  
                  <br><br><center><h2>Carrello</h2></center><br>
                   
                            
                            
                            
                            <%
                            	if(cart!=null){
                            %>		
                            		<center><caption></caption>
				                    <table style='max-width:80%;'>
				                            <thead>
				                              <tr>
				                                <th>Prodotto</th>
				                                <th>Prezzo</th>
				                                <th>Quantità</th>
				                                <th>Sconto</th>
				                                <th>Iva</th>
				                                <th>Totale</th>
				                                <th>Note</th>
				                                <th>Rimuovi</th>
				                              </tr>
				                            </thead>
				                            <tbody>
                            
		                            <%		
		                            		float totale=0,totPagare=0,totIva=0;
				                            int count=0;
				                            for(ProdottoBean p : cart.getArrayProd()){ 
				                            	count++;
				                            	//totale=(p.getPrezzo()*p.getQuantitaOrdinata())-(p.getPrezzo() * p.getQuantitaOrdinata() * p.getSconto()/100) +(p.getPrezzo() * p.getQuantitaOrdinata() * p.getIva()/100) ;
				                            	totIva =((p.getPrezzo()*p.getQuantitaOrdinata())+(p.getPrezzo()*p.getQuantitaOrdinata()*p.getIva()/100));
				                            	totale=totIva-(totIva*p.getSconto()/100);
				                            	totPagare+=totale;
				                    %>
				                              <tr class="prod<%= count %>">
				                                <td data-label="Prodotto"><img width='90px' src='../<%=p.getPath() %>'><br><%=p.getNome() %></td>
				                                <td data-label="Prezzo"><%=p.getPrezzo() %>€</td>
				                                <td data-label="Quantità" >
				                                	<img src='../images/meno.png' onclick="minusProduct('<%= p.getNomeNegozio() %>','<%= p.getNomeCategoria() %>',<%= p.getIdProdotto() %>,<%=p.getQuantitaOrdinata()%>,'<%= count %>')" style='width:0.5em;cursor:pointer;'> 
				                                	<span class='cartQta<%=count%>'><%=p.getQuantitaOrdinata() %> </span>
				                                	<img src='../images/plus.png'  onclick="addProduct('<%= p.getNomeNegozio() %>','<%= p.getNomeCategoria() %>',<%= p.getIdProdotto() %>,<%=p.getQuantita()%>,<%= count %>)" style='width:0.5em;cursor:pointer;'></td>
				                                <td data-label="Sconto" ><%=p.getSconto() %>%<br></td>
				                                <td data-label="Iva" ><%=p.getIva() %>%<br></td>
				                                <td data-label="Totale" class='prodTotale<%=count%>'><%=new DecimalFormat("##.##").format(totale)%>€<br></td>
				                                <td data-label="Note"><%=p.getNote() %><br></td>
				                                <td data-label="Rimuovi"><img style='cursor:pointer;' width='40px' onclick="removeCartProduct('<%= p.getNomeNegozio() %>','<%= p.getNomeCategoria() %>',<%= p.getIdProdotto() %>,<%= count %>)" src='../images/delete.png'></td>
				                              </tr>
		                              		<%}%>
		                              		<tr>
		                              		 <td></td>
		                              		 <td></td>
		                              		 <td></td>
		                              		 <td></td>
		                              		 <td>Tot.</td>
		                              		 <td><%= new DecimalFormat("##.##").format(totPagare) %>€</td>
		                              		 <td></td>
		                              		 <td></td>
			                               </tbody>
			                          </table>
			                          
			                          <br><br><br>
			                          
			                          <h2 style="display:inline;cursor:pointer;"  onclick="confirm()">
			                          	<span style="vertical-align:middle;">Conferma Ordine </span><img style="vertical-align:middle;"width="40px" src='../images/confirm.png'>
			                          </h2>
			                          </center>
			                          
			                          
                              <%}
                            	else{%>
                              		<br><h3>Non ci sono prodotti nel carrello!</h3>
                              <%}%>
                           
                             
                  
                  
                  
              </div>
        
        <%@ include file="../WEB-INF/fragment/footer.jsp" %>
		
		
    </body>
</html>