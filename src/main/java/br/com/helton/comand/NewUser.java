package br.com.helton.comand;


import br.com.helton.entity.Cliente;
import br.com.helton.service.ClienteServico;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NewUser implements Command {

	private static final String PAGINACADASTRO = "/WEB-INF/cliente/cadastrarCliente.jsp";
	private static final String LISTAUSUARIOS = "http://localhost:8080/UsuarioCRUD/app?acao=ListUser";

	ClienteServico clienteServico;
	
	public NewUser(ClienteServico clienteServico) {
		this.clienteServico = clienteServico;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		var nome = request.getParameter("nome");
		var email = request.getParameter("email");
		var celular = request.getParameter("celular");
		
		if(nome != null && email != null && celular != null) {
			var newUser = new Cliente(nome,email,celular);
			clienteServico.cadastrarCliente(newUser);
			return LISTAUSUARIOS;
		}
		
		return PAGINACADASTRO;
	}

}
