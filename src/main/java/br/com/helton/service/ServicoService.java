package br.com.helton.service;

import java.sql.SQLException;
import java.util.List;

import br.com.helton.DAO.ServicoDAO;
import br.com.helton.entity.Servico;

public class ServicoService {
    private ServicoDAO servicoDAO;

    public ServicoService(ServicoDAO servicoDAO) {
        this.servicoDAO = servicoDAO;
    }

    public void cadastrarServico(Servico servico){
        if (servico.getDescricao() == null || servico.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("Descrição do serviço é obrigatória.");
        }
        if (servico.getNome() == null || servico.getNome().isEmpty()) {
        	throw new IllegalArgumentException("Descrição do serviço é obrigatória.");
        }
        if (servico.getValor() <= 0) {
            throw new IllegalArgumentException("Valor do serviço deve ser maior que zero.");
        }

        servicoDAO.cadastrar(servico);
    }

    public List<Servico> listarServicos(){
        return servicoDAO.listar();
    }
    
    public Servico listaUmServico(Long id) {
    	return servicoDAO.listaUmServico(id);
    }

    public void atualizarServico(Servico servico){
        if (servico.getDescricao() == null || servico.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("Descrição do serviço é obrigatória.");
        }
        if (servico.getNome() == null || servico.getNome().isEmpty()) {
        	throw new IllegalArgumentException("Descrição do serviço é obrigatória.");
        }
        if (servico.getValor() <= 0) {
            throw new IllegalArgumentException("Valor do serviço deve ser maior que zero.");
        }

        servicoDAO.atualizar(servico);
    }

    public void removerServico(int id){
        servicoDAO.remover(id);
    }

    public List<Servico> buscarServicosPorVeiculo(int veiculoId) throws SQLException {
        return servicoDAO.buscarPorVeiculo(veiculoId);
    }
}

