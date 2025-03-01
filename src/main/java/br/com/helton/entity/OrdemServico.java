package br.com.helton.entity;

import java.io.Serializable;
import java.util.Date;

public class OrdemServico implements Serializable{

 
	private static final long serialVersionUID = 1L;
	private Long id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Servico servico;
    private String data;
    private Double valorFinal;
    private Double desconto;

    public OrdemServico() {}

    public OrdemServico(Cliente cliente, Veiculo veiculo, Servico servico, String data, Double valorFinal, Double desconto) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.servico = servico;
        this.data = data;
        this.valorFinal = valorFinal;
        this.desconto = desconto;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

    
}
