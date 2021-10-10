package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import com.mysql.jdbc.Statement;

import beans.Estoque;

public class EstoqueDao extends BaseDao {


	public Estoque getEstoqueViaId(int id) {

		Estoque estoque = null;
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			pstm = conn.prepareStatement("select * from estoque where id=?");
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				estoque = criaEstoque(rs);
			}
		} catch (SQLException e) {
			estoque = null;
		}
		return estoque;
	}

	public Estoque criaEstoque(ResultSet rs) throws SQLException {
		Estoque estoque = new Estoque();
		estoque.setDescricao(rs.getString("descricao"));
		estoque.setId(rs.getInt("id"));
		estoque.setQuantidade(rs.getDouble("quantidade"));
		estoque.setPrecounit(rs.getDouble("precounit"));

		return estoque;
	}

	public boolean salvarEstoque(Estoque estoque) {
		boolean retorno = false;
		String sql = "";
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			if (estoque.getId() == 0) {
				sql = "insert into estoque (descricao, precounit, quantidade) ";
				sql += "values (?, ?, ?)";
				pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			} else {
				sql = "update estoque set descricao=?, precounit=?, quantidade=? ";
				sql += " where id = ?";
				pstm = conn.prepareStatement(sql);
			}
			pstm.setString(1, estoque.getDescricao());
			pstm.setDouble(2, estoque.getPrecounit());
			pstm.setDouble(3, estoque.getQuantidade());
			if (estoque.getId() != 0) {
				pstm.setInt(4, estoque.getId());
			}
			int idAux = pstm.executeUpdate();
			if (idAux == 0)
				return false;
			if (estoque.getId() == 0) {
				int idInserir = getGeneratedId(pstm);
				estoque.setId(idInserir);
			}
			retorno = true;
		} catch (SQLException e) {
			retorno = false;
		}
		return retorno;
	}

	public int getGeneratedId(PreparedStatement stm) throws SQLException {
		ResultSet rs = stm.getGeneratedKeys();
		if (rs.next()) {
			int id = rs.getInt(1);
			return id;
		}
		return 0;
	}

	public boolean excluir(int id) {
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			String sql = "delete from estoque where id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			int conta = pstm.executeUpdate();
			boolean retorno = conta > 0;
			return retorno;
		} catch (SQLException e) {
			return false;
		}
	}

	public Estoque getEstoquePorId(int id) {
		Estoque estoque = new Estoque();
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			String sql = "select * from estoque where id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				estoque = criaEstoque(rs);
			}
		} catch (SQLException e) {
			estoque = null;
		}
		return estoque;
	}

	public List<Estoque> getEstoqueViaNome(String nome) {
		ArrayList<Estoque> estoques = new ArrayList<>();
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			String sql = "select * from estoque where nome = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nome);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				estoques.add(criaEstoque(rs));
			}
		}
		catch (SQLException e) {
			estoques = null;
		}
		return estoques;
	}

	public List<Estoque> getTodos() {
		ArrayList<Estoque> estoques = new ArrayList<>();
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			String sql = "select * from estoque";
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				estoques.add(criaEstoque(rs));
			}
		}
		catch (SQLException e) {
			estoques = null;
		}
		return estoques;
	}

}
