package br.com.helton.comand;

import java.sql.SQLException;

import br.com.helton.entity.Cliente;
import br.com.helton.entity.Veiculo;
import br.com.helton.service.ClienteServico;
import br.com.helton.service.VeiculoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetVeiculo implements Command {

	private String pagina = "/WEB-INF/veiculo/cadastrarVeiculo.jsp";
	
	private VeiculoService veiculoService;
	private ClienteServico clienteService;
	
    public GetVeiculo(VeiculoService veiculoService, ClienteServico clienteService) {
        this.veiculoService = veiculoService;
        this.clienteService = clienteService;
    }

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		Veiculo veiculo = veiculoService.buscarVeiculoPorId(Long.parseLong(id));
		Cliente clienteAtual = clienteService.buscarClientePorId((long) veiculo.getClienteId());
		request.setAttribute("veiculo", veiculo);
		request.setAttribute("cliente", clienteAtual);
		return pagina;
	}
}
