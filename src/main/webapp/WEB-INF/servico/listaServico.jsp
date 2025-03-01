<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List,br.com.helton.entity.Servico" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listagem de Serviços</title>
</head>
<body>
    <h1>Serviços Cadastrados</h1>
    
    <div>
		<a href="http://localhost:8080/UsuarioCRUD/">Inicio</a>
	</div>
	<br><br>
    
    
    <% List<Servico> servicoList = (ArrayList<Servico>) request.getAttribute("servicoList"); %>
    
    <table style="border-collapse: collapse; width: 80%;">
        <tr>
            <td style="border: 1px solid black;">ID</td>
            <td style="border: 1px solid black;">NOME</td>
            <td style="border: 1px solid black;">DESCRIÇÃO</td>
            <td style="border: 1px solid black;">VALOR</td>
            <td style="border: 1px solid black;" colspan="2">AÇÃO</td>
        </tr>
        
        <% 
        for (Servico servico : servicoList) {
        %>
        
        <tr>
            <td style="border: 1px solid black;"><%= servico.getId() %></td>
            <td style="border: 1px solid black;"><%= servico.getNome() %></td>
            <td style="border: 1px solid black;"><%= servico.getDescricao() %></td>
            <td style="border: 1px solid black;"><%= servico.getValor() %></td>
            <td><a href="/UsuarioCRUD/app?acao=listaUmServico&id=<%= servico.getId() %>">editar</a></td>
            <td><a href="/UsuarioCRUD/app?acao=deleteServico&id=<%= servico.getId() %>">remover</a></td>
        </tr>
        
        <% } %>
        
    </table>
</body>
</html>
