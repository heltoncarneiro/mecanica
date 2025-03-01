<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List,br.com.helton.entity.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listagem de Veículos</title>
</head>
<body>
    <h1>Veículos Cadastrados</h1>
    
    <div>
		<a href="http://localhost:8080/UsuarioCRUD/">Inicio</a>
	</div>
	<br><br>
    
    <% List<Veiculo> veiculoList = (ArrayList<Veiculo>) request.getAttribute("veiculoList"); %>
    
    <table style="border-collapse: collapse; width: 80%;">
        <tr>
            <td style="border: 1px solid black;">ID</td>
            <td style="border: 1px solid black;">MODELO</td>
            <td style="border: 1px solid black;">PLACA</td>
            <td style="border: 1px solid black;">ANO</td>
            <td style="border: 1px solid black;">TIPO</td>
            <td style="border: 1px solid black;">CLIENTE ID</td>
            <td style="border: 1px solid black;" colspan="2">AÇÃO</td>
        </tr>
        
        <% 
        for (Veiculo veiculo : veiculoList) {
        %>
        
        <tr>
            <td style="border: 1px solid black;"><%= veiculo.getId() %></td>
            <td style="border: 1px solid black;"><%= veiculo.getModelo() %></td>
            <td style="border: 1px solid black;"><%= veiculo.getPlaca() %></td>
            <td style="border: 1px solid black;"><%= veiculo.getAno() %></td>
            <td style="border: 1px solid black;"><%= veiculo.getTipo() %></td>
            <td style="border: 1px solid black;"><%= veiculo.getClienteId() %></td>
            <td><a href="/UsuarioCRUD/app?acao=listaUmVeiculo&id=<%= veiculo.getId() %>">editar</a></td>
            <td><a href="/UsuarioCRUD/app?acao=deleteVeiculo&id=<%= veiculo.getId() %>">remover</a></td>
        </tr>
        
        <% } %>
        
    </table>
</body>
</html>
