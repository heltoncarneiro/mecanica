package br.com.helton.service;

import java.sql.SQLException;
import java.util.List;

import br.com.helton.DAO.VeiculoDAO;
import br.com.helton.entity.Veiculo;

public class VeiculoService {
    private VeiculoDAO veiculoDAO;

    public VeiculoService(VeiculoDAO veiculoDAO) {
        this.veiculoDAO = veiculoDAO;
    }

    public void cadastrarVeiculo(Veiculo veiculo){
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo é obrigatória.");
        }
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) {
            throw new IllegalArgumentException("Modelo do veículo é obrigatório.");
        }
        if (veiculo.getModelo().equalsIgnoreCase("Peugeot")) {
            throw new IllegalArgumentException("Não trabalhamos com os carros da Peugeot");
        }
        if (veiculo.getAno() <= 0) {
            throw new IllegalArgumentException("Ano do veículo é obrigatório e deve ser maior que zero.");
        }
        veiculoDAO.cadastrar(veiculo);
    }

    public List<Veiculo> listarVeiculos(){
        return veiculoDAO.listar();
    }

    public void atualizarVeiculo(Veiculo veiculo){
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo é obrigatória.");
        }
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) {
            throw new IllegalArgumentException("Modelo do veículo é obrigatório.");
        }
        if (veiculo.getAno() <= 0) {
            throw new IllegalArgumentException("Ano do veículo é obrigatório e deve ser maior que zero.");
        }

        veiculoDAO.atualizar(veiculo);
    }

    public void removerVeiculo(Long id){
        veiculoDAO.remover(id);
    }

    public List<Veiculo> buscarVeiculosPorCliente(int clienteId) throws SQLException {
        return veiculoDAO.buscarPorCliente(clienteId);
    }
    
    public Veiculo buscarVeiculoPorId(Long id){
    	return  veiculoDAO.buscarVeiculoPorId(id);
    }
   
}
