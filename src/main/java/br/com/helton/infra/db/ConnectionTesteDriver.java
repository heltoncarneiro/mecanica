package br.com.helton.infra.db;

public class ConnectionTesteDriver {
	
	public static void main(String[] args) {
		MySqlDbConection con = new MySqlDbConection();
		System.out.println(con.getConnection()!=null? "Conecta": "Falhou");
	}
	
	
}
