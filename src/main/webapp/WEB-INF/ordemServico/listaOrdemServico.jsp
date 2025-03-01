<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,br.com.helton.entity.*" %>
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

	<form method="get" action="app">
	    <input type="hidden" name="acao" value="listaOrderServico">
	    <input type="hidden" name="id" id="clienteSelecionadoId" value="">
	
	    <label for="clienteId">Cliente:</label>
	    <select name="clienteId" id="clienteId" onchange="atualizarClienteId()">
	        <option value="">Todos os clientes</option>
	        <% 
	        List<Cliente> clienteList = (List<Cliente>) request.getAttribute("clienteList");
	        for (Cliente cliente : clienteList) { 
	        %>
	            <option value="<%= cliente.getId() %>"><%= cliente.getNome() %></option>
	        <% } %>
	    </select>
	
	    <button type="submit">Filtrar</button>
	</form>

	<script>
	    function atualizarClienteId() {
	        var clienteId = document.getElementById("clienteId").value;
	        document.getElementById("clienteSelecionadoId").value = clienteId;
	    }
	</script>

    <table border="1" width="80%">
        <tr>
            <th>ID</th>
            <th>Cliente</th>
            <th>Veículo</th>
            <th>Serviço</th>
            <th>Descontos</th>
            <th>Valor Final</th>
            <th>Ação</th>
        </tr>

        <% 
        List<OrdemServico> servicoList = (List<OrdemServico>) request.getAttribute("servicoCliente");
        if (servicoList != null) {
            for (OrdemServico servico : servicoList) { 
        %>
            <tr>
                <td><%= servico.getId() %></td>
                <td><%= servico.getCliente().getNome() %></td>
                <td><%= servico.getVeiculo().getModelo() + " " + servico.getVeiculo().getAno() %></td>
                <td><%= servico.getServico().getNome() %></td>
                <td><%= servico.getDesconto() %></td>
                <td><%= servico.getValorFinal() %></td>
                <td><a href="/UsuarioCRUD/app?acao=deleteOrderServico&id=<%= servico.getId() %>">Remover</a></td>
            </tr>
        <% 
            } 
        } else { 
        %>
            <tr><td colspan="7">Nenhum serviço encontrado.</td></tr>
        <% } %>
    </table>
</body>
</html>

