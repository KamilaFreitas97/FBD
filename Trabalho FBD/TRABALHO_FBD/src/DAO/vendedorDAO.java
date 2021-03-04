package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.vendedorPOJO;
import JDBC.ConnectionFactory;

public class vendedorDAO {
	private Connection connection;
	
	public vendedorDAO() {
		
	}
	
	public boolean addVendedor(vendedorPOJO v) {
		String sql = "INSERT INTO Vendedor(cpf, nome, sexo, senha, salario) VALUES(?, ?, ?, ?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			stmt.setString(1, v.getCPF());
			stmt.setString(2, v.getNome());
			stmt.setString(3, String.valueOf(v.getSexo()));
			stmt.setString(4, v.getSenha());
			stmt.setFloat(5, v.getSalario());
			
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
	
	public boolean deleteVendedor(String cpf, String senha) {
		String sql = "DELETE FROM Vendedor WHERE cpf LIKE ? AND senha LIKE ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.setString(2, senha);
			
			ResultSet rs = stmt.executeQuery();
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
	
	public ArrayList<vendedorPOJO> getListaVendedores() {
		String sql = "SELECT * FROM Vendedor";
		ArrayList<vendedorPOJO> listaVendedores = new ArrayList<vendedorPOJO>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			while(rs.next()) {
				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				char sexo = rs.getString("sexo").charAt(0);
				float salario = rs.getFloat("salario");
				String senha = rs.getString("senha");
				
				vendedorPOJO v = new vendedorPOJO(cpf, nome, sexo, senha, salario);
				
				listaVendedores.add(v);
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
		
		return listaVendedores;
	}
	
	public vendedorPOJO getVendedorByCPF(String cpf, String senha) {
		String sql = "SELECT * FROM Vendedor WHERE cpf LIKE ? AND senha LIKE ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.setString(2, senha);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			vendedorPOJO v = new vendedorPOJO(cpf, rs.getString("nome"), rs.getString("sexo").charAt(0), rs.getString("senha"), rs.getFloat("salario"));
			stmt.close();
			
			return v;
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
