package br.com.helton.comand;

import br.com.helton.entity.Servico;
import br.com.helton.service.ServicoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServico implements Command {
    
    private static String pagina = "http://localhost:8080/UsuarioCRUD/app?acao=listaServico";
    
    private ServicoService servicoService;
    
    public RegisterServico(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String descricao = request.getParameter("descricao");
        String nome = request.getParameter("nome");
        String precoStr = request.getParameter("valor");
        double preco = Double.parseDouble(precoStr);
        
        Servico servico = new Servico();
        servico.setDescricao(descricao);
        servico.setNome(nome);
        servico.setValor(preco);

        try {
            servicoService.cadastrarServico(servico); 
        } catch (Exception e) {
            e.printStackTrace();  
            request.setAttribute("mensagemErro", "Erro ao cadastrar serviço: " + e.getMessage());
        }
        return pagina;
    }
}
