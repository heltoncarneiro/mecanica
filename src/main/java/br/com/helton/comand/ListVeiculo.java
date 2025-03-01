package br.com.helton.comand;

import java.sql.SQLException;

import br.com.helton.service.VeiculoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListVeiculo implements Command{
	
	private String pagina = "/WEB-INF/veiculo/listaVeiculo.jsp";
	
	private VeiculoService veiculoService;
	
    public ListVeiculo(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		var veiculo = veiculoService.listarVeiculos();
		request.setAttribute("veiculoList", veiculo);
		return pagina;
	}

}
