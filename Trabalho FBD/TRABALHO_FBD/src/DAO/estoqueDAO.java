package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.estoquePOJO;
import JDBC.ConnectionFactory;

public class estoqueDAO {
	private Connection connection;
	
	public estoqueDAO() {
		
	}
	
	public ArrayList<estoquePOJO> getAllEstoque() {
		String sql = "SELECT * FROM Estoque";
		ArrayList<estoquePOJO> est = new ArrayList<estoquePOJO>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String placa = rs.getString("placa_carro");
				
				estoquePOJO ep = new estoquePOJO(placa);
				
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
