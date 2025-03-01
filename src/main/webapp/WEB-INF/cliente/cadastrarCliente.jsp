<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List,br.com.helton.entity.Cliente" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New User</title>
</head>
<body>

	<h1>Cadatre um novo Cliente</h1>
	
	<% Cliente user = (Cliente) request.getAttribute("user"); %>
	
	
	<form action="app" method="post">
	
		<input type="hidden" name="acao" value="<%=(user!=null? "AtualizarUser": "CadastroUser")%>">
		<input type="hidden" name="id" value="<%=(user!=null? user.getId() : "")%>">
		
	    Nome: <input name="nome" placeholder="nome" type="text" required value="<%=(user!=null? user.getNome(): "")%>"> 
	    <br>
	    Email: <input name="email" placeholder="email" type="email" required value="<%=(user!=null? user.getEmail(): "")%>"> 
	    <br>
	    Celular: <input name="celular" placeholder="celular" type="text" required value="<%=(user!=null? user.getCelular(): "")%>"> 
	    <br>
	    <button>Finalizar</button>
	</form>

</body>
</html>