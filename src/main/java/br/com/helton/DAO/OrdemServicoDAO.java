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

public class OrdemServicoDAO {

    private DbConection conn;

    public OrdemServicoDAO(DbConection conn) {
        this.conn = conn;
    }

    public OrdemServicoDAO() {
		// TODO Auto-generated constructor stub
	}

	public void salvar(OrdemServico ordem) {
        String sql = "INSERT INTO ordem_servico (id_cliente, id_veiculo, id_servico, valor_final, desconto) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = conn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, ordem.getCliente().getId());
            ps.setLong(2, ordem.getVeiculo().getId());
            ps.setLong(3, ordem.getServico().getId());
            ps.setDouble(4, ordem.getValorFinal());
            ps.setDouble(5, ordem.getDesconto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int contarServicosCliente(Cliente cliente) {
        String sql = "SELECT COUNT(*) FROM ordem_servico WHERE id_cliente = ?";
        int totalServicos = 0;

        try (Connection connection = conn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, cliente.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalServicos = rs.getInt(1); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalServicos;
    }

    public List<OrdemServico> listarTodos(){
        List<OrdemServico> ordensServico = new ArrayList<>();
        String sql = "SELECT os.id, os.data_criacao,os.desconto, os.valor_final, c.id AS cliente_id, c.nome AS cliente_nome, "
                + "v.id AS veiculo_id, v.tipo AS tipo, v.modelo AS veiculo_modelo, v.ano AS veiculo_ano, "
                + "s.id AS servico_id, s.nome AS servico_nome, s.descricao AS servico_descricao "
                + "FROM ordem_servico os "
                + "JOIN clientes c ON os.id_cliente = c.id "
                + "JOIN veiculo v ON os.id_veiculo = v.id "
                + "JOIN servico s ON os.id_servico = s.id;";

        try (Connection connection = conn.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                OrdemServico ordemServico = new OrdemServico();
                ordemServico.setId(rs.getLong("id"));
                ordemServico.setData(rs.getString("data_criacao"));
                ordemServico.setDesconto(rs.getDouble("desconto"));
                ordemServico.setValorFinal(rs.getDouble("valor_final"));

                Cliente cliente = new Cliente();
                cliente.setId(rs.getLong("cliente_id"));
                cliente.setNome(rs.getString("cliente_nome"));
                ordemServico.setCliente(cliente);

                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("veiculo_id"));
                veiculo.setModelo(rs.getString("veiculo_modelo"));
                veiculo.setAno(rs.getInt("veiculo_ano"));
                veiculo.setTipo(rs.getString("tipo"));
                ordemServico.setVeiculo(veiculo);

                Servico servico = new Servico();
                servico.setId(rs.getInt("servico_id"));
                servico.setNome(rs.getString("servico_nome"));
                servico.setDescricao(rs.getString("servico_descricao"));
                ordemServico.setServico(servico);

                ordensServico.add(ordemServico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordensServico;
    }

	public void remover(int id) {
		String sql = "DELETE FROM ordem_servico WHERE id = ?";
        try (Connection connection = conn.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
