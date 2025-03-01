package br.com.helton.entity;

public class Cliente {
	
	private Long id;
	private String nome;
	private String email;
	private String celular;
	
	
	public Cliente() {}

	public Cliente(String nome, String email,String celular) {
		this.nome = nome;
		this.email = email;
		this.celular = celular;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	
	
	
}
