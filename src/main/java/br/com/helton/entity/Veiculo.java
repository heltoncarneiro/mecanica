package br.com.helton.entity;

public class Veiculo {
    private int id;
    private String placa;
    private String modelo;
    private int ano;
    private String tipo;
    private int clienteId; 

    public Veiculo(int id, String placa, String modelo, int ano, String tipo, int clienteId) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.tipo = tipo;
        this.clienteId = clienteId;
    }

    public Veiculo() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public String toString() {
        return "Veiculo [id=" + id + ", placa=" + placa + ", modelo=" + modelo + ", ano=" + ano + ", clienteId=" + clienteId + "]";
    }

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}

