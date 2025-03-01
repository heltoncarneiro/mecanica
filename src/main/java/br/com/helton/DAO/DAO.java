package br.com.helton.DAO;

import java.util.List;

import br.com.helton.entity.Cliente;

public interface DAO<T> {
	
	void salvar(T t);
	List<T> listarTodos();
	Object listarUm(long id);
	void atualizar(Cliente usuario);
	void delete(Long id);

}
