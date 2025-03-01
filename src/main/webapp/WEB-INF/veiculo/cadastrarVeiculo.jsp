<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List,br.com.helton.entity.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Veículo</title>
</head>
<body>
	
	 <% 
        Veiculo veiculo = null;
        Object veiculoObj = request.getAttribute("veiculo");
        if (veiculoObj instanceof Veiculo) {
            veiculo = (Veiculo) veiculoObj;
        }
        
        List<Cliente> clientes = null;
        Object clientesObj = request.getAttribute("clientes");
        if (clientesObj instanceof List) {
            clientes = (List<Cliente>) clientesObj;
        }

        Cliente cliente = null;
        Object clienteObj = request.getAttribute("cliente");
        if (clienteObj instanceof Cliente) {
            cliente = (Cliente) clienteObj;
        }
    %>

    <h1><% if(veiculo == null) { %>Cadastrar um novo veículo<% } else { %>Atualizar veículo<% } %></h1>
    
    <form action="app" method="post">
        
        <input type="hidden" name="acao" value="<%=(veiculo != null ? "atualizarVeiculo" : "cadastrarVeiculo")%>">
        
        <input type="hidden" name="id" value="<%=(veiculo != null ? veiculo.getId() : "")%>">
        
        Modelo: <input name="modelo" placeholder="Modelo do veículo" type="text" required value="<%=(veiculo != null ? veiculo.getModelo() : "")%>"> 
        <br>
        
        Placa: <input name="placa" placeholder="Placa do veículo" type="text" required value="<%=(veiculo != null ? veiculo.getPlaca() : "")%>">
        <br>
        
        Ano: <input name="ano" placeholder="Ano do veículo" type="number" required value="<%=(veiculo != null ? veiculo.getAno() : "")%>">
        <br>
        
		Tipo: <label for="tipo">Tipo:</label>
		<select name="tipo" required>
		    <option value="caminhao" <%= (veiculo != null && "caminhao".equals(veiculo.getTipo()) ? "selected" : "") %>>Caminhão</option>
		    <option value="moto" <%= (veiculo != null && "moto".equals(veiculo.getTipo()) ? "selected" : "") %>>Moto</option>
		    <option value="carro" <%= (veiculo != null && "carro".equals(veiculo.getTipo()) ? "selected" : "") %>>Carro</option>
		</select>
		<br>

		<label for="cliente_id">Cliente:</label>
         <select name="cliente_id" id="cliente_id" required>
            <option value="">Selecione um Cliente</option>
            <% 
                if (cliente != null) {
            %>
                <option value="<%= cliente.getId() %>" selected><%= cliente.getNome() %></option>
            <% 
                } else if (clientes != null) {
                    for (Cliente cli : clientes) { 
            %>
                <option value="<%= cli.getId() %>"><%= cli.getNome() %></option>
            <% 
                }
                }
            %>
        </select>
		<br><br>
        
        <button>Finalizar</button>
    </form>

</body>
</html>
