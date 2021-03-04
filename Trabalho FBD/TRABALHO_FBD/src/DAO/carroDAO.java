package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.carroPOJO;
import JDBC.ConnectionFactory;

public class carroDAO {
	private Connection connection;
	
	public carroDAO() {
		
	}
	
	public boolean addCarro(carroPOJO c) {
		String sql = "INSERT INTO CARRO(placa, modelo, preco) VALUES (?, ?, ?);";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, c.getPlaca());
			stmt.setString(2, c.getModelo());
			stmt.setFloat(3, c.getPreco());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) {
				return true;
			} else {
				return false;
			}
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean updateCarroPreco(String placa, float preco) {
		String sql = "UPDATE Carro SET preco = ? WHERE placa LIKE ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setFloat(1, preco);
			stmt.setString(2, placa);
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean deleteCarro(String placa) {
		String sql = "DELETE FROM Carro WHERE placa LIKE ?";
		
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, placa);
			
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
	
	public ArrayList<carroPOJO> getListaCarros() {
		String sql = "SELECT * FROM Carro;";
		ArrayList<carroPOJO> listaCarros = new ArrayList<carroPOJO>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String placa = rs.getString("placa");
				String modelo = rs.getString("modelo");
				float preco = Float.parseFloat(rs.getString("preco"));
				
				carroPOJO c = new carroPOJO(placa, modelo, preco);
				
				listaCarros.add(c);
			}
			stmt.close();
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return listaCarros;
	}
	
	public carroPOJO getCarroPlaca(String placa) {
		String sql = "SELECT * FROM Carro WHERE placa = ?";
		
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, placa);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			carroPOJO c = new carroPOJO(placa, rs.getString("modelo"), Float.parseFloat(rs.getString("preco")));
			stmt.close();
			
			return c;
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
	
	public ArrayList<carroPOJO> getAllEstoque() {
		String sql = "SELECT * FROM Carro WHERE placa IN (SELECT placa_carro FROM Estoque)";
		ArrayList<carroPOJO> est = new ArrayList<carroPOJO>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String placa = rs.getString("placa");
				String modelo = rs.getString("modelo");
				float preco = rs.getFloat("preco");
				
				carroPOJO ep = new carroPOJO(placa, modelo, preco);
				
				est.add(ep);
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
		
		return est;
	}
}
