package br.com.helton.comand;

import br.com.helton.entity.Cliente;
import br.com.helton.entity.OrdemServico;
import br.com.helton.entity.Servico;
import br.com.helton.entity.Veiculo;
import br.com.helton.service.ClienteServico;
import br.com.helton.service.OrdemServicoService;
import br.com.helton.service.ServicoService;
import br.com.helton.service.VeiculoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterOrdemServico implements Command {
	
	private String pagina = "http://localhost:8080/UsuarioCRUD/app?acao=listaOrderServico";
	
	private VeiculoService veiculoService;
	private ServicoService servicoService;
	private ClienteServico clienteService;
	private OrdemServicoService orderServicoService;
	
    public RegisterOrdemServico(OrdemServicoService orderServicoService,VeiculoService veiculoService, ServicoService servicoService, ClienteServico clienteService) {
        this.veiculoService = veiculoService;
        this.servicoService = servicoService;
        this.clienteService = clienteService;
        this.orderServicoService = orderServicoService;
    }

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response){
		String id_veiculo = request.getParameter("id_veiculo");
		String id_cliente = request.getParameter("id_cliente");
		String id_servico = request.getParameter("id_servico");
		
		Cliente cliente = clienteService.buscarClientePorId(Long.parseLong(id_cliente));
		Veiculo veiculo = veiculoService.buscarVeiculoPorId(Long.parseLong(id_veiculo));
		Servico servico = servicoService.listaUmServico(Long.parseLong(id_servico));
		
		OrdemServico order = orderServicoService.criarOrdemServico(cliente, veiculo, servico);
		orderServicoService.salvar(order);
		return pagina;
	}

}
