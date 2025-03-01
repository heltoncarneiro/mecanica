<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.ArrayList, java.util.List,br.com.helton.entity.Cliente" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Usuário cadastrado com sucesso!!!</h1>
	
	<div>
		<a href="http://localhost:8080/UsuarioCRUD/">Inicio</a>
	</div>
	<br><br>
	
	<% List<Cliente> userList = (ArrayList<Cliente>) request.getAttribute("userList"); %>
	
	<table style="border-collapse: collapse; width: 60%;">
		<tr>
			<td style="border: 1px solid black;">ID</td>
        	<td style="border: 1px solid black;">NOME</td>
        	<td style="border: 1px solid black;">EMAIL</td>
        	<td style="border: 1px solid black;">CELULAR</td>
        	<td style="border: 1px solid black;" colspan="2">AÇÃO</td>
		</tr>
		
		<% 
		for(Cliente user:userList){
		%>
		
		<tr>
			<td style="border: 1px solid black;"><%= user.getId() %></td>
			<td style="border: 1px solid black;"><%= user.getNome() %></td>
			<td style="border: 1px solid black;"><%= user.getEmail() %></td>
			<td style="border: 1px solid black;"><%= user.getCelular() %></td>
			<td><a href="/UsuarioCRUD/app?acao=GetUser&id=<%=user.getId()%>">editar</a></td>
			<td><a href="/UsuarioCRUD/app?acao=DeleteUser&id=<%=user.getId()%>">remover</a></td>
		</tr>
		
		<%} %>
		
	</table>
</body>
</html>