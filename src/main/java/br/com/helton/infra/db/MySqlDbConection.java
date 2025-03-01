package br.com.helton.infra.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDbConection implements DbConection{

	public Connection getConnection() {
	    Connection con = null;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mecanica", "root", "1608");
	        
	        System.out.println("Conexão estabelecida com sucesso.");
	        
	    } catch (ClassNotFoundException e) {
	        System.out.println("Driver JDBC não encontrado.");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.out.println("Falha ao conectar ao banco de dados.");
	        e.printStackTrace();
	    }
	    
	    return con;
	}
	
	public void close(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
