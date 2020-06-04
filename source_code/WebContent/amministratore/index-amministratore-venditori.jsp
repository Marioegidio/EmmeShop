<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="javax.servlet.*,model.*,java.util.*"
    pageEncoding="UTF-8"
    session="true"
%>

<%@include file="../WEB-INF/utilities/checkLogAmministratore.jsp" %>
<%!
	String negozio = "";
	String categoria = "";
%>


<%
	UserModelDS model=new UserModelDS();
	Collection<VenditoreBean>  venditori= model.getAllVenditore();
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
        <script type="text/javascript" src="../js/venditore.js"></script>
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
           <%@ include file="../WEB-INF/fragment/headeradmin.jsp" %>
                  
            <div id='mySidenav' class="sidenav">
              <ul >
                <li onclick="window.open('index-amministratore.jsp','_self')">
                    Negozi
                </li>
                <li  onclick="window.open('index-amministratore-clienti.jsp','_self')">
                    Clienti
                </li>
               
               
              </ul>    
            </div>
        </header>
        <div class="content">
                  
              
              <h2>Gestione Venditori</h2>
         			
         		
                  <div class="wrapper" id="#ContentWrapper">
                  
                  <%
                    	 
                    			
						 if (venditori != null && venditori.size() != 0) {
								Iterator<?> it = venditori.iterator();
								int count=0;
								while (it.hasNext()) {
									count++;
									VenditoreBean bean = (VenditoreBean) it.next();
				    %>
                   
                   
                   <div class="categorySeller">
                        <form action='' method='POST'>
                          <div class="img">
                            <img src="../images/seller.png">
                          </div>
                          <div class='nomeCategoria'>
                            <span >
                                <%= bean.getNome() %> 
                            </span>
                          </div>
                          <div class="mod">
                              <a href='modifica-venditore.jsp?username=<%= bean.getUsername() %>'><img src='../images/modify.png'></a>
                          </div>
                          <div class='del'>
                            <span>
                              <a href=''><img onclick="deleteVend('<%= bean.getUsername() %>')" style="cursor:pointer;" src='../images/delete.png'></a>
                            </span>
                          </div>
                          
                        </form>
                    </div>
                    
                    
                   
                    <% } }%>
                    
                    
                      
                      
                  </div>
                  
                             
                  
                  
                  
              </div>
        
        <%@ include file="../WEB-INF/fragment/footer.jsp" %>
		
		
		
		
	
   		
    </body>
</html>