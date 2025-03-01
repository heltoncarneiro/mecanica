package br.com.helton.entity;

import java.sql.Date;

public class Servico {
    private int id;
    private String descricao;
    private String nome;
    private Date data;
    private Long veiculoId;
    private double valor;

    public Servico(int id, String nome,String descricao, Date data, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.nome = nome;
        this.data = data;
        this.valor = valor;
    }

    public Servico() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId2) {
        this.veiculoId = veiculoId2;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Servico [id=" + id + ", descricao=" + descricao + ", data=" + data + ", veiculoId=" + veiculoId + ", valor=" + valor + "]";
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}