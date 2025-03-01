package br.com.helton.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.helton.entity.Cliente;
import br.com.helton.entity.OrdemServico;
import br.com.helton.entity.Servico;
import br.com.helton.entity.Veiculo;
import br.com.helton.infra.db.DbConection;

public class ClienteDAO implements DAO<Cliente> {
    
    private static String sqlSelect = "SELECT * FROM clientes";
    private static String sqlSelectByID = "SELECT * FROM clientes WHERE id=?";
    private static String sqlInsert = "INSERT INTO clientes(nome,email,celular) VALUES(?,?,?)";
    private static String sqlUpdate = "UPDATE clientes SET nome=?,email=?,celular=? WHERE id=?";
    private static String sqlDelete = "DELETE FROM clientes WHERE id=?";
    private static String sqlSelectByEmail = "SELECT * FROM clientes WHERE email=?";
    private static String sqlEmailExists = "SELECT COUNT(*) FROM clientes WHERE email=?";

    private DbConection conn;

    public ClienteDAO(DbConection conn) {
        this.conn = conn;
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> ListUserDb = new ArrayList<>();
        try (Connection connection = conn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlSelect);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente user = new Cliente(rs.getString("nome"), rs.getString("email"), rs.getString("celular"));
                user.setId(rs.getLong("id"));
                ListUserDb.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListUserDb;
    }

    @Override
    public void salvar(Cliente cliente) {
        try (Connection connection = conn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getCelular());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente listarUm(long id) {
        Cliente user = null;
        try (Connection connection = conn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlSelectByID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new Cliente(rs.getString("nome"), rs.getString("email"), rs.getString("celular"));
                user.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void atualizar(Cliente cliente) {
        try (Connection connection = conn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlUpdate)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getCelular());
            ps.setLong(4, cliente.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = conn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlDelete)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retorna cliente por ID
    public Cliente buscarPorId(Long id) {
        return listarUm(id);
    }

    // Verifica se o email já existe
    public boolean emailJaExiste(String email) {
        try (Connection connection = conn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlEmailExists)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se o email já existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retorna o cliente pelo email
    public Cliente buscarPorEmail(String email) {
        Cliente cliente = null;
        try (Connection connection = conn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlSelectByEmail)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(rs.getString("nome"), rs.getString("email"), rs.getString("celular"));
                cliente.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public List<OrdemServico> listaServicoCliente(int clienteId) {
        List<OrdemServico> ordemServicos = new ArrayList<>();
        String query = """
                SELECT os.id, os.data_criacao, os.desconto, os.valor_final,
                       c.id AS cliente_id, c.nome AS cliente_nome,
                       v.id AS veiculo_id, v.tipo AS tipo, v.modelo AS veiculo_modelo, v.ano AS veiculo_ano,
                       s.id AS servico_id, s.nome AS servico_nome, s.descricao AS servico_descricao
                FROM ordem_servico os 
                JOIN clientes c ON os.id_cliente = c.id
                JOIN veiculo v ON os.id_veiculo = v.id
                JOIN servico s ON os.id_servico = s.id
                WHERE os.id_cliente = ?
                """;

        try (Connection connection = conn.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, clienteId);  // Substitui o ID do cliente na query
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Instancia e preenche os objetos associados
                Cliente cliente = new Cliente();
                cliente.setId(rs.getLong("cliente_id"));
                cliente.setNome(rs.getString("cliente_nome"));

                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("veiculo_id"));
                veiculo.setTipo(rs.getString("tipo"));
                veiculo.setModelo(rs.getString("veiculo_modelo"));
                veiculo.setAno(rs.getInt("veiculo_ano"));

                Servico servico = new Servico();
                servico.setId(rs.getInt("servico_id"));
                servico.setNome(rs.getString("servico_nome"));
                servico.setDescricao(rs.getString("servico_descricao"));

                OrdemServico ordemServico = new OrdemServico();
                ordemServico.setId(rs.getLong("id"));
                ordemServico.setData(rs.getString("data_criacao"));
                ordemServico.setDesconto(rs.getDouble("desconto"));
                ordemServico.setValorFinal(rs.getDouble("valor_final"));
                ordemServico.setCliente(cliente);
                ordemServico.setVeiculo(veiculo);
                ordemServico.setServico(servico);

                ordemServicos.add(ordemServico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordemServicos;
    }
}
