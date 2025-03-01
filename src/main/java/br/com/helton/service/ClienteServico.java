package br.com.helton.service;

import java.util.List;

import br.com.helton.DAO.ClienteDAO;
import br.com.helton.entity.Cliente;
import br.com.helton.entity.OrdemServico;

public class ClienteServico {
	
	 	private ClienteDAO clienteDAO;

	    public ClienteServico(ClienteDAO clienteDAO) {
	        this.clienteDAO = clienteDAO;
	    }

	    public void cadastrarCliente(Cliente cliente){
	        validarDadosCliente(cliente);
	        verificarEmailDuplicado(cliente.getEmail());
	        
	        clienteDAO.salvar(cliente);
	    }

	    public void atualizarCliente(Cliente cliente){
	        if (cliente.getId() == null) {
	            throw new IllegalArgumentException("ID do cliente não pode ser nulo para atualização.");
	        }
	        
	        validarDadosCliente(cliente);
	        verificarEmailDuplicadoParaAtualizacao(cliente);
	        
	        clienteDAO.atualizar(cliente);
	    }

	    public void excluirCliente(Long clienteId){
	        clienteDAO.delete(clienteId);
	    }

	    public List<Cliente> listarClientes() {
	        return clienteDAO.listarTodos();
	    }

	    public Cliente buscarClientePorId(Long clienteId) {
	    	var cliente = clienteDAO.buscarPorId(clienteId);
	        if(cliente ==  null) {
	        	throw new IllegalArgumentException("O não cliente foi encontrado.");
	        }
	        return cliente;
	        		
	    }

	    private void validarDadosCliente(Cliente cliente){
	        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
	            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
	        }
	        
	        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
	            throw new IllegalArgumentException("O e-mail do cliente é obrigatório.");
	        }
	        
	        if (cliente.getCelular() == null || cliente.getCelular().isBlank()) {
	            throw new IllegalArgumentException("O celular do cliente é obrigatório.");
	        }
	    }

	    private void verificarEmailDuplicado(String email) {
	        if (clienteDAO.emailJaExiste(email)) {
	        	throw new IllegalArgumentException("Email já esta em uso.");
	        }
	    }
	    
	    private void verificarEmailDuplicadoParaAtualizacao(Cliente cliente){
	        Cliente clienteExistente = clienteDAO.buscarPorEmail(cliente.getEmail());
	        
	        if (clienteExistente != null && !clienteExistente.getId().equals(cliente.getId())) {
	        	throw new IllegalArgumentException("Email já esta em uso.");
	        }
	    }

		public List<OrdemServico> listaServicoCliente(int id) {
			return this.clienteDAO.listaServicoCliente(id);
		}

}
