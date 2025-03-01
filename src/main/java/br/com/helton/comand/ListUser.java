package br.com.helton.comand;

import java.util.List;

import br.com.helton.entity.Cliente;
import br.com.helton.service.ClienteServico;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListUser implements Command{
	
	String paginaSucesso = "/WEB-INF/cliente/listaCliente.jsp";
	
	ClienteServico clienteServico;
	
	public ListUser(ClienteServico clienteServico) {
		this.clienteServico = clienteServico;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<Cliente> users = this.clienteServico.listarClientes();
		
		request.setAttribute("userList", users);
		
		return paginaSucesso;
		
	}
	
	
}
