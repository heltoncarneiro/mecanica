package br.com.helton.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.helton.entity.Veiculo;
import br.com.helton.infra.db.DbConection;

public class VeiculoDAO {
    private DbConection conn;

    public VeiculoDAO(DbConection conn) {
        this.conn = conn;
    }
    

    // Método para cadastrar um veículo
    public void cadastrar(Veiculo veiculo){
        String sql = "INSERT INTO veiculo (placa, modelo, ano, tipo, cliente_id) VALUES (?,?,?,?,?)";
        Connection connection = conn.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());            
            stmt.setString(4, veiculo.getTipo());
            stmt.setInt(5, veiculo.getClienteId());
            stmt.executeUpdate();
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
    }

    // Método para listar todos os veículos
    public List<Veiculo> listar() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculo";
        Connection connection = conn.getConnection();
        try (Statement stmt =connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                        rs.getInt("id"),
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getInt("ano"),
                        rs.getString("tipo"),
                        rs.getInt("cliente_id")
                );
                veiculos.add(veiculo);
            }
        }catch (Exception e) {
        	e.printStackTrace();
		}
        return veiculos;
    }

    // Método para atualizar um veículo
    public void atualizar(Veiculo veiculo){
        String sql = "UPDATE veiculo SET placa = ?, modelo = ?, ano = ?, tipo = ?, cliente_id = ? WHERE id = ?";
        Connection connection = conn.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setString(4, veiculo.getTipo());
            stmt.setInt(5, veiculo.getClienteId());
            stmt.setInt(6, veiculo.getId());
            stmt.executeUpdate();
        }catch (Exception e) {
        	e.printStackTrace(); 
       }
    }

    // Método para remover um veículo
    public void remover(Long id) {
        String sql = "DELETE FROM veiculo WHERE id = ?";
        Connection connection = conn.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }catch (Exception e) {
        	e.printStackTrace();
		}
    }

    // Método para filtrar veículos por clienteId
    public List<Veiculo> buscarPorCliente(int clienteId){
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculo WHERE cliente_id = ?";
        Connection connection = conn.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Veiculo veiculo = new Veiculo(
                            rs.getInt("id"),
                            rs.getString("placa"),
                            rs.getString("modelo"),
                            rs.getInt("ano"),
                            rs.getString("tipo"),
                            rs.getInt("cliente_id")
                    );
                    veiculos.add(veiculo);
                }
            }catch (Exception e) {
				// TODO: handle exception
            	e.printStackTrace();
			}
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
        return veiculos;
    }
    
    public Veiculo buscarVeiculoPorId(Long id) {
        Veiculo veiculo = null;
        String sql = "SELECT * FROM veiculo WHERE id = ?";
        Connection connection = conn.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    veiculo = new Veiculo();
                    veiculo.setId(rs.getInt("id"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setAno(rs.getInt("ano"));
                    veiculo.setTipo(rs.getString("tipo"));
                    veiculo.setClienteId(rs.getInt("cliente_id"));
                }
            }
        }catch (Exception e) {
			e.printStackTrace();
		}

        return veiculo;
    }
}
