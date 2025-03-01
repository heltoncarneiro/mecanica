package br.com.helton.comand;

import java.sql.SQLException;
import java.util.List;

import br.com.helton.entity.Cliente;
import br.com.helton.entity.OrdemServico;
import br.com.helton.service.ClienteServico;
import br.com.helton.service.OrdemServicoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListOrdemServicos implements Command{
	
	private String pagina = "/WEB-INF/ordemServico/listaOrdemServico.jsp";
	    
    private ClienteServico clienteService;
	private OrdemServicoService orderServicoService;
	
    public ListOrdemServicos(ClienteServico clienteService, OrdemServicoService orderServicoService) {
        this.clienteService = clienteService;
        this.orderServicoService  = orderServicoService;
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String idParam = request.getParameter("clienteId");

        List<Cliente> clienteList = clienteService.listarClientes();
        request.setAttribute("clienteList", clienteList);

        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            List<OrdemServico> servicoCliente = clienteService.listaServicoCliente(id);
            request.setAttribute("servicoCliente", servicoCliente);
        } else {
            List<OrdemServico> servicoList = orderServicoService.listarTodos();
            request.setAttribute("servicoCliente", servicoList);
        }

        return pagina;
    }

}
