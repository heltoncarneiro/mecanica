package br.com.helton.service;

import java.time.Year;
import java.util.List;

import br.com.helton.DAO.OrdemServicoDAO;
import br.com.helton.entity.Cliente;
import br.com.helton.entity.OrdemServico;
import br.com.helton.entity.Servico;
import br.com.helton.entity.Veiculo;

public class OrdemServicoService {
	
	private OrdemServicoDAO orderServicoDAO;

    public OrdemServicoService(OrdemServicoDAO orderServicoDAO) {
        this.orderServicoDAO = orderServicoDAO;
    }
    
    public void salvar(OrdemServico ordemServico) {
    	this.orderServicoDAO.salvar(ordemServico);
    }

    public OrdemServico criarOrdemServico(Cliente cliente, Veiculo veiculo, Servico servico) {
        OrdemServico ordem = new OrdemServico();
        ordem.setCliente(cliente);
        ordem.setVeiculo(veiculo);
        ordem.setServico(servico);
        
        double valorBase = servico.getValor();
        double desconto = calcularDesconto(cliente, servico, veiculo);
        double valorFinal = valorBase - desconto;
        
        ordem.setDesconto(desconto);
        ordem.setValorFinal(valorFinal);

        return ordem;
    }

    private double calcularDesconto(Cliente cliente, Servico servico, Veiculo veiculo) {
        double desconto = 0.0;
        int servicosRealizados = contarServicosCliente(cliente);
        
        // 10% de desconto para clientes novos
        if (servicosRealizados == 0) {
            desconto += servico.getValor() * 0.1; 
        }

        // 10% de desconto para clientes com 3+ serviços
        if (servicosRealizados >= 3) {
            desconto += servico.getValor() * 0.1; 
        }

        // 5% de desconto para caminhões
        if (veiculo.getTipo().equalsIgnoreCase("caminhao")) {
            desconto += servico.getValor() * 0.05; 
        }

        // 15% de desconto em troca de óleo para motos
        if (servico.getNome().equalsIgnoreCase("Troca de oleo") && veiculo.getTipo().equalsIgnoreCase("moto")) {
            desconto += servico.getValor() * 0.15; // 15% de desconto em troca de óleo para motos
        }
        
        // 8% de desconto para veículos com mais de 10 anos
        int anoAtual = Year.now().getValue();
        int idadeVeiculo = anoAtual - veiculo.getAno();
        if (idadeVeiculo > 10) {
            desconto += servico.getValor() * 0.08; // 8% de desconto para veículos com mais de 10 anos
        }
        
        // Limite de 30% no total de desconto
        if (desconto > servico.getValor() * 0.3) {
            desconto = servico.getValor() * 0.3; 
        }
        
        return desconto;
    }
    
    public int contarServicosCliente(Cliente cliente) {
        return this.orderServicoDAO.contarServicosCliente(cliente);
    }

	public List<OrdemServico> listarTodos() {
		return this.orderServicoDAO.listarTodos();
	}

	public void removerOrder(int id) {
		this.orderServicoDAO.remover(id);
		
	}
}

