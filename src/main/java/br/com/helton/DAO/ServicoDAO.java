package br.com.helton.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.helton.entity.Servico;
import br.com.helton.infra.db.DbConection;

public class ServicoDAO {
    private DbConection conn;

    public ServicoDAO(DbConection connection) {
        this.conn = connection;
    }
    
    // Método para cadastrar um serviço
    public void cadastrar(Servico servico){
        String sql = "INSERT INTO servico (nome,descricao, valor) VALUES (?, ?, ?)";
        Connection connection = conn.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getValor());
            stmt.executeUpdate();
        }catch (Exception e) {
			e.printStackTrace();
		}
    }

    // Método para listar todos os serviços
    public List<Servico> listar(){
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM servico";
        Connection connection = conn.getConnection();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Servico servico = new Servico(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDate("data"),
                        rs.getDouble("valor")
                );
                servicos.add(servico);
            }
        }catch (Exception e) {
        	e.printStackTrace();
		}
        return servicos;
    }
    
    public Servico listaUmServico(Long id) {
        String sql = "SELECT * FROM servico WHERE id = ?";
        Connection connection = conn.getConnection();
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Servico(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getDate("data"),
                            rs.getDouble("valor")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

 

    // Método para atualizar um serviço
    public void atualizar(Servico servico){
        String sql = "UPDATE servico SET nome = ?, descricao = ?, valor = ? WHERE id = ?";
        Connection connection = conn.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getValor());
            stmt.setInt(4, servico.getId());
            stmt.executeUpdate();
        }catch (Exception e) {
			e.printStackTrace();
		}
    }

    // Método para remover um serviço
    public void remover(int id){
        String sql = "DELETE FROM servico WHERE id = ?";
        Connection connection = conn.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }

    // Método para filtrar serviços por veiculoId
    public List<Servico> buscarPorVeiculo(int veiculoId){
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM servico WHERE veiculoId = ?";
        Connection connection = conn.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, veiculoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Servico servico = new Servico(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getDate("data"),
                            rs.getDouble("valor")
                    );
                    servicos.add(servico);
                }
            }catch (Exception e) {
				// TODO: handle exception
            	e.printStackTrace();
			}
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
        return servicos;
    }
}

