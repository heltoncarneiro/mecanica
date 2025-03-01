package br.com.helton.comand;

import br.com.helton.entity.Veiculo;
import br.com.helton.service.VeiculoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateVeiculo implements Command{
		private String pagina = "http://localhost:8080/UsuarioCRUD/app?acao=listaVeiculos";
		
		private VeiculoService veiculoService;
		
	    public UpdateVeiculo(VeiculoService veiculoService) {
	        this.veiculoService = veiculoService;
	    }

		@Override
		public String execute(HttpServletRequest request, HttpServletResponse response){
			int id = Integer.parseInt(request.getParameter("id"));
            String modelo = request.getParameter("modelo");
            String placa = request.getParameter("placa");
            int ano = Integer.parseInt(request.getParameter("ano"));
            String tipo = (request.getParameter("tipo"));
            int clienteId = Integer.parseInt(request.getParameter("cliente_id"));
            
            Veiculo veiculo = new Veiculo(id, modelo, placa, ano,tipo, clienteId);
            
            veiculoService.atualizarVeiculo(veiculo);
            return pagina;
		}
	    
	    
}
