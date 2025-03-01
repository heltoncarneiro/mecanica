<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,br.com.helton.entity.Cliente,br.com.helton.entity.Veiculo,br.com.helton.entity.Servico" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Ordem de Serviço</title>
</head>
<body>

<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
    List<Veiculo> veiculos = (List<Veiculo>) request.getAttribute("veiculos");
    List<Servico> servicos = (List<Servico>) request.getAttribute("servicos");
%>

<h2>Nova Ordem de Serviço</h2>
<form action="app" method="post">

 <input type="hidden" name="acao" value="cadastrarPedido">
    
    Cliente:
    <select name="id_cliente" required>
        <option value="">Selecione um cliente</option>
        <% for (Cliente cliente : clientes) { %>
            <option value="<%= cliente.getId() %>"><%= cliente.getNome() %></option>
        <% } %>
    </select>
    <br><br>
    
    Veículo:
    <select name="id_veiculo" required>
        <option value="">Selecione um veículo</option>
        <% for (Veiculo veiculo : veiculos) { %>
            <option value="<%= veiculo.getId() %>"><%= veiculo.getModelo() %> - <%= veiculo.getAno() %></option>
        <% } %>
    </select>
    <br><br>
    
    Serviço:
    <select name="id_servico" required>
        <option value="">Selecione um serviço</option>
        <% for (Servico servico : servicos) { %>
            <option value="<%= servico.getId() %>"><%= servico.getNome() %></option>
        <% } %>
    </select>
    <br><br>

    <button type="submit">Cadastrar Pedido</button>
</form>

</body>
</html>
