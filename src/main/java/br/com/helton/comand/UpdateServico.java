package br.com.helton.comand;

import br.com.helton.entity.Servico;
import br.com.helton.service.ServicoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateServico implements Command{
	private String pagina = "http://localhost:8080/UsuarioCRUD/app?acao=listaServico";
	
	private ServicoService servicoService;
	
    public UpdateServico(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        Double valor = Double.parseDouble(request.getParameter("valor"));
        
        Servico servico = new Servico();
        servico.setId(id);
        servico.setNome(nome);
        servico.setDescricao(descricao);
        servico.setValor(valor);
        
        
        servicoService.atualizarServico(servico);
        return pagina;
	}
    
    

}
