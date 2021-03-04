package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.emprestimoPOJO;
import JDBC.ConnectionFactory;

// inserir, mostrar tudo

public class emprestimoDAO {
	private Connection connection;
	
	public emprestimoDAO() {
		
	}
	
	public boolean addEmprestimo(emprestimoPOJO empr) {
		String sql = "INSERT INTO Emprestimo(cpf_cliente, placa_carro, data_emprestimo, dias_escolhidos, cpf_vendedor, juros, preco_base, devolvido) VALUES (?, ?, ?, ?, ?, ?, ?, FALSE)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			stmt.setString(1, empr.getCpf_cliente());
			stmt.setString(2, empr.getPlaca_carro());
			stmt.setDate(3, empr.getData_emprestimo());
			stmt.setInt(4, empr.getDias_escolhidos());
			stmt.setString(5, empr.getCpf_vendedor());
			stmt.setFloat(6, empr.getJuros());
			stmt.setFloat(7,  empr.getPreco_base());
			
			rs.next();
			
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
	
	public ArrayList<emprestimoPOJO> getListaEmprestimos() {
		String sql = "SELECT * FROM Emprestimo";
		ArrayList<emprestimoPOJO> emprPOJO = new ArrayList<emprestimoPOJO>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			while(rs.next()) {
				String cpf_cliente = rs.getString("cpf_cliente");
				String placa_carro = rs.getString("placa_carro");
				Date data_emprestimo = rs.getDate("data_emprestimo");
				int dias_escolhidos = rs.getInt("dias_escolhidos");
				String cpf_vendedor = rs.getString("cpf_vendedor");
				float juros = rs.getFloat("juros");
				float preco_base = rs.getFloat("preco_base");
				boolean devolvido = rs.getBoolean("devolvido");
				
				emprestimoPOJO emprP = new emprestimoPOJO(cpf_cliente, placa_carro, data_emprestimo, dias_escolhidos, cpf_vendedor, juros, preco_base, devolvido);
				
				emprPOJO.add(emprP);
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
		
		return emprPOJO;
	}
	
	public ArrayList<emprestimoPOJO> getRegistroByCPF(String cpf) {
		String sql = "SELECT * FROM Emprestimo WHERE cpf_cliente LIKE ?";
		ArrayList<emprestimoPOJO> emprPOJO = new ArrayList<emprestimoPOJO>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String cpf_cliente = rs.getString("cpf_cliente");
				String placa_carro = rs.getString("placa_carro");
				Date data_emprestimo = rs.getDate("data_emprestimo");
				int dias_escolhidos = rs.getInt("dias_escolhidos");
				String cpf_vendedor = rs.getString("cpf_vendedor");
				float juros = rs.getFloat("juros");
				float preco_base = rs.getFloat("preco_base");
				boolean devolvido = rs.getBoolean("devolvido");
				
				emprestimoPOJO emprP = new emprestimoPOJO(cpf_cliente, placa_carro, data_emprestimo, dias_escolhidos, cpf_vendedor, juros, preco_base, devolvido);
				
				emprPOJO.add(emprP);
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
		return emprPOJO;
	}
	
	public emprestimoPOJO getEmprestimoByCPFPLACA(String cpf, String placa) {
		String sql = "SELECT * FROM Emprestimo WHERE cpf_cliente LIKE ? AND placa_carro LIKE ? AND devolvido = FALSE";
		this.connection = new ConnectionFactory().getConnection();
		emprestimoPOJO emprp = null;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.setString(2, placa);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			while(rs.next()) {
				String cpf_cliente = rs.getString("cpf_cliente");
				String placa_carro = rs.getString("placa_carro");
				Date data_emprestimo = rs.getDate("data_emprestimo");
				int dias_escolhidos = rs.getInt("dias_escolhidos");
				String cpf_vendedor = rs.getString("cpf_vendedor");
				float juros = rs.getFloat("juros");
				float preco_base = rs.getFloat("preco_base");
				boolean devolvido = rs.getBoolean("devolvido");
				
				emprp = new emprestimoPOJO(cpf_cliente, placa_carro, data_emprestimo, dias_escolhidos, cpf_vendedor, juros, preco_base, devolvido);
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
		
		return emprp;
	}
	
	public ArrayList<emprestimoPOJO> getEmprestimoNaoDevolvidos() {
		String sql = "SELECT * FROM Emprestimo WHERE devolvido = FALSE";
		this.connection = new ConnectionFactory().getConnection();
		ArrayList<emprestimoPOJO> list = new ArrayList<emprestimoPOJO>();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			while(rs.next()) {
				String cpf_cliente = rs.getString("cpf_cliente");
				String placa_carro = rs.getString("placa_carro");
				Date data_emprestimo = rs.getDate("data_emprestimo");
				int dias_escolhidos = rs.getInt("dias_escolhidos");
				String cpf_vendedor = rs.getString("cpf_vendedor");
				float juros = rs.getFloat("juros");
				float preco_base = rs.getFloat("preco_base");
				boolean devolvido = rs.getBoolean("devolvido");
				
				emprestimoPOJO emprp = new emprestimoPOJO(cpf_cliente, placa_carro, data_emprestimo, dias_escolhidos, cpf_vendedor, juros, preco_base, devolvido);
				list.add(emprp);
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
		
		return list;
	}
}
