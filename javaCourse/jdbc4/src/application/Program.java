package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("UPDATE seller " + "SET BaseSalary = BaseSalary + ? " + "WHERE " + "(Id = ?)");
			st.setDouble(1, 300.00);
			st.setInt(2, 8);
			int rowsAffected = st.executeUpdate();
			if (rowsAffected != 0) {
				System.out.println("Colunas afetadas: " + rowsAffected);
			} else {
				System.out.println("Nenhuma coluna afetada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}


	}

}
