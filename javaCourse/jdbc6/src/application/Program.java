package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			conn.setAutoCommit(false);
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2190 WHERE DepartmentId = 1");
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3190 WHERE DepartmentId = 2");

			conn.commit();

			System.out.println("rows 1 " + rows1 + ", rows 2 " + rows2);
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Erro de transação: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro de recuperação: " + e.getMessage());	
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
