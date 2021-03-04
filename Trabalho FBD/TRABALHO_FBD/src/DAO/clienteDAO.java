package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.clientePOJO;
import JDBC.ConnectionFactory;

public class clienteDAO {
	private Connection connection;
	
	public clienteDAO() {
		
	}
	
	public boolean addCliente(clientePOJO c) {
		String sql = "INSERT INTO Cliente(cpf, nome, sexo) VALUES(?, ?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, c.getCPF());
			stmt.setString(2, c.getNome());
			stmt.setString(3, String.valueOf(c.getSexo()));
			
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
	
	public boolean deleteCliente(String cpf) {
		String sql = "DELETE FROM Cliente WHERE cpf LIKE ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			
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
	
	public ArrayList<clientePOJO> getClienteLista() {
		String sql = "SELECT * FROM Cliente";
		ArrayList<clientePOJO> clientes = new ArrayList<clientePOJO>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				char sexo = rs.getString("sexo").charAt(0);
				
				clientePOJO cp = new clientePOJO(cpf, nome, sexo);
				
				clientes.add(cp);
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
		
		return clientes;
	}
	
	public clientePOJO getClienteByCPF(String cpf) {
		String sql = "SELECT * FROM Cliente WHERE cpf LIKE ?";
		this.connection = new ConnectionFactory().getConnection();
		clientePOJO cp = null;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				cp = new clientePOJO(cpf, rs.getString("nome"), rs.getString("sexo").charAt(0));
			
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
		
		return cp;
	}
	
	public float getSaldo(clientePOJO c) {
		String sql = "SELECT calcularSaldo(?) AS soma;";
		this.connection = new ConnectionFactory().getConnection();
		float v = 0;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, c.getCPF());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				v += rs.getFloat("soma");
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
		
		return v;
	}
}
