<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="javax.servlet.*,model.*"
    pageEncoding="UTF-8"
    session="true"
%>
<%!	
	String loggatoLog;
	String usernameLog; 
	String tipoLog;  
	UserBean utenteLog;
	String requestURI;
%>	
<%
	synchronized(session) {
		session = request.getSession();
		
		loggatoLog=(String)session.getAttribute("venditore-loggato");
		if(loggatoLog != null && loggatoLog.equals("s") ){
			utenteLog=(VenditoreBean)session.getAttribute("utente");
			usernameLog=utenteLog.getUsername();
			tipoLog="Venditore";
			//response.sendRedirect("./WEB-INF/login-result/index-venditore.jsp");
		}
		else{
			requestURI = request.getRequestURI(); 
			if( !requestURI.equals("/emmeShop/index.jsp"))
				response.sendRedirect("http://localhost:8080/emmeShop/index.jsp");
		}
	}	
			
	%>