package br.com.helton.comand;

import java.util.List;

import br.com.helton.entity.Servico;
import br.com.helton.service.ServicoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteServico implements Command {

	private String pagina = "/WEB-INF/servico/listaServico.jsp";

	private ServicoService servicoService;
	
    public DeleteServico (ServicoService servicoService) {
        this.servicoService = servicoService;
    }


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		this.servicoService.removerServico(id);
		List<Servico> servico= this.servicoService.listarServicos();
		request.setAttribute("servicoList", servico);
		return pagina;
	}
	
}
