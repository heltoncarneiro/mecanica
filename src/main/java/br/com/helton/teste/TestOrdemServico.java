package br.com.helton.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Year;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import br.com.helton.DAO.OrdemServicoDAO;
import br.com.helton.entity.Cliente;
import br.com.helton.entity.OrdemServico;
import br.com.helton.entity.Servico;
import br.com.helton.entity.Veiculo;
import br.com.helton.service.OrdemServicoService;

public class TestOrdemServico {

		@Mock
		public OrdemServicoDAO ordemServicoDAO;

	    @InjectMocks
	    public OrdemServicoService ordemServicoService;

	    @Before
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testCriarOrdemServicoAplicandoDescontoParaVeiculoAntigo() {
	        Cliente cliente = new Cliente();
	        Veiculo veiculo = new Veiculo();
	        veiculo.setTipo("fusca");
	        veiculo.setAno(Year.now().getValue() - 11);
	        Servico servico = new Servico();
	        servico.setNome("trocar_freios");
	        servico.setValor(100.0);
	        
	        when(ordemServicoService.contarServicosCliente(cliente)).thenReturn(1);
	        OrdemServico ordem = ordemServicoService.criarOrdemServico(cliente, veiculo, servico);

	        assertEquals(92.0, ordem.getValorFinal()); // 8% de desconto para veículo com mais de 10 anos
	    }
	    
	    @Test
	    public void testCriarOrdemServicoComDiscontoParaTrocaDeOleoDeMoto() {
	        Cliente cliente = new Cliente();
	        Veiculo veiculo = new Veiculo();
	        veiculo.setTipo("moto");
	        veiculo.setAno(Year.now().getValue());
	        Servico servico = new Servico();
	        servico.setNome("Troca de oleo");
	        servico.setValor(120.0);

	        when(ordemServicoService.contarServicosCliente(cliente)).thenReturn(1);
	        OrdemServico ordem = ordemServicoService.criarOrdemServico(cliente, veiculo, servico);

	        assertEquals(102, ordem.getValorFinal()); // 15% de desconto para motos nesse exato serviço
	    }
	    
	    @Test
	    public void testCriarOrdemServicoComDescontoParaCaminhao() {
	        Cliente cliente = new Cliente();
	        Veiculo veiculo = new Veiculo();
	        veiculo.setTipo("caminhao");
	        veiculo.setAno(Year.now().getValue());
	        Servico servico = new Servico();
	        servico.setNome("Manutenção geral na cabine");
	        servico.setValor(200.0);

	        when(ordemServicoService.contarServicosCliente(cliente)).thenReturn(1);
	        OrdemServico ordem = ordemServicoService.criarOrdemServico(cliente, veiculo, servico);

	        assertEquals(190.0, ordem.getValorFinal()); // Esperado: 5% de desconto em um serviço de 200.0, valor final = 190.0
	    }
	    
	    @Test
	    public void testCriarOrdemServicoComDescontoParaClienteFiel() {
	        Cliente cliente = new Cliente();
	        Veiculo veiculo = new Veiculo();
	        veiculo.setTipo("carro");
	        veiculo.setAno(Year.now().getValue());
	        Servico servico = new Servico();
	        servico.setNome("Revisão geral");
	        servico.setValor(150.0);

	        // Simula que o cliente já fez 3 serviços
	        when(ordemServicoService.contarServicosCliente(cliente)).thenReturn(3);

	        OrdemServico ordem = ordemServicoService.criarOrdemServico(cliente, veiculo, servico);

	        // Esperado: 10% de desconto em um serviço de 150.0, valor final = 135.0
	        assertEquals(135.0, ordem.getValorFinal());
	    }
	    
	    @Test
	    public void testDescontoBoasVindasClienteNovo() {
	        Cliente cliente = new Cliente();
	        Veiculo veiculo = new Veiculo();
	        veiculo.setTipo("carro");
	        veiculo.setAno(Year.now().getValue());
	        Servico servico = new Servico();
	        servico.setNome("Revisão geral");
	        servico.setValor(100.0);
	        
	        // Cliente com 0 serviços realizados (cliente novo)
	        when(this.ordemServicoDAO.contarServicosCliente(cliente)).thenReturn(0);

	        OrdemServico ordem = ordemServicoService.criarOrdemServico(cliente, veiculo, servico);

	        assertEquals(90.0, ordem.getValorFinal()); // 10% de desconto para cliente novo
	    }

	    @Test
	    public void testLimiteDescontoCliente() {
	        Cliente cliente = new Cliente();
	        Veiculo veiculo = new Veiculo();
	        
	        Servico servico = new Servico();
	        servico.setValor(200.0);
	        
	        // Veículo com mais de 10 anos, recebe 8% de desconto
	        veiculo.setAno(2007);
	        
	        // Troca de óleo para moto, recebe 15% de desconto
	        veiculo.setTipo("moto");
	        servico.setNome("Troca de oleo");
	        
	        // Cliente novo, que já recebe 10% de desconto
	        when(this.ordemServicoDAO.contarServicosCliente(cliente)).thenReturn(0);
	        
	        OrdemServico ordem = ordemServicoService.criarOrdemServico(cliente, veiculo, servico);

	        // O total de descontos seria 10% + 8% + 15% = 33%. Mas o limite é 30%, então o desconto final é 60 (30% de 200)
	        assertEquals(140.0, ordem.getValorFinal());
	    }
}
