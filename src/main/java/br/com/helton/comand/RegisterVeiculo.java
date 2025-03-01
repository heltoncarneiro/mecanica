package br.com.helton.comand;

import br.com.helton.entity.Veiculo;
import br.com.helton.service.VeiculoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterVeiculo implements Command{
	
	private static String pagina = "http://localhost:8080/UsuarioCRUD/app?acao=listaVeiculos";
	
	private VeiculoService veiculoService;
	
    public RegisterVeiculo(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)  {
		String modelo = request.getParameter("modelo");
        String placa = request.getParameter("placa");
        String anoStr = request.getParameter("ano");
        String tipo= request.getParameter("tipo");
        String clienteIdStr = request.getParameter("cliente_id");
		
		int ano = Integer.parseInt(anoStr);
        int clienteId = Integer.parseInt(clienteIdStr);

        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(modelo);
        veiculo.setPlaca(placa);
        veiculo.setAno(ano);
        veiculo.setClienteId(clienteId);
        veiculo.setTipo(tipo);

        try {
        	veiculoService.cadastrarVeiculo(veiculo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return pagina;
	}


}
