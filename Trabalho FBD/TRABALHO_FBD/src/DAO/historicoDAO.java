package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.historicoPOJO;
import JDBC.ConnectionFactory;

public class historicoDAO {
	private Connection connection;
	
	public historicoDAO() {
		
	}
	
	public boolean addHistorico(historicoPOJO hist) {
		String sql = "INSERT INTO Historico(cpf_cliente, placa_carro, data_devolvida, dias_ficados, cpf_vendedor, preco_final) VALUES(?, ?, ?, ?, ?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, hist.getCpf_cliente());
			stmt.setString(2, hist.getPlaca_carro());
			stmt.setDate(3, hist.getData_entrega());
			stmt.setInt(4, hist.getDias_ficados());
			stmt.setString(5, hist.getCpf_vendedor());
			stmt.setFloat(6, hist.getPreco_final());
			
			int qtd_RowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtd_RowsAffected > 0) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public ArrayList<historicoPOJO> getListaHistorico() {
		String sql = "SELECT * FROM Historico";
		ArrayList<historicoPOJO> histP = new ArrayList<historicoPOJO>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String cpf_cliente = rs.getString("cpf_cliente");
				String placa_carro = rs.getString("placa_carro");
				Date data_entrega = rs.getDate("data_devolvida");
				int dias_ficados = rs.getInt("dias_ficados");
				String cpf_vendedor = rs.getString("cpf_vendedor");
				float preco_final = rs.getFloat("preco_final");
				
				historicoPOJO hist = new historicoPOJO(cpf_cliente, placa_carro, data_entrega, dias_ficados, cpf_vendedor, preco_final);
				histP.add(hist);
			}
			stmt.close();
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return histP;
	}
	
	public ArrayList<historicoPOJO> getRegistroByCPF(String cpf) {
		String sql = "SELECT * FROM Historico WHERE cpf_cliente LIKE ?";
		ArrayList<historicoPOJO> histP = new ArrayList<historicoPOJO>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String cpf_cliente = rs.getString("cpf_cliente");
				String placa_carro = rs.getString("placa_carro");
				Date data_entrega = rs.getDate("data_devolvida");
				int dias_ficados = rs.getInt("dias_ficados");
				String cpf_vendedor = rs.getString("cpf_vendedor");
				float preco_final = rs.getFloat("preco_final");
				
				historicoPOJO hist = new historicoPOJO(cpf_cliente, placa_carro, data_entrega, dias_ficados, cpf_vendedor, preco_final);
				histP.add(hist);
			}
			stmt.close();
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return histP;
	}
	
	public boolean updatePrecoFinal(String cpf, String placa, Date date, float novoPreco) {
		String sql = "UPDATE Historico SET preco_final = ? WHERE cpf_cliente LIKE ? AND placa_carro LIKE ? AND data_devolvida = ?;";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setFloat(1, novoPreco);
			stmt.setString(2, cpf);
			stmt.setString(3, placa);
			stmt.setDate(4, date);
			
			int qtd_RowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtd_RowsAffected > 0) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
}
