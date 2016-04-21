package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLDao {
	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/companydb";
	private static String user = "student";
	private static String pass = "student";
	private Connection conn;

	public SQLResult runSQL(String sql) {
		SQLResult result = new SQLResult();
		result.setSql(sql);
		Statement stmt;
		try {
			Class.forName(DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(URL, user, pass);
			stmt = conn.createStatement();
			if (stmt.execute(sql)) { // Have a result set
				result.setResultSet(buildResults(stmt.getResultSet()));
			} else { // No result set
				result.setUpdateCount(stmt.getUpdateCount());
			}
			stmt.close();
			conn.close();
		} catch (SQLException sqle) {
			System.err.println(sqle);
			result.setException(sqle);
		} catch (Exception e) {
			System.err.println(e);
		}
		return result;
	}

	private ArrayList<ArrayList<String>> buildResults(ResultSet rs) throws SQLException {
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		ResultSetMetaData rsmd = rs.getMetaData();
		ArrayList<String> headers = new ArrayList<String>();
		int colCount = rsmd.getColumnCount();
		for (int i = 1; i <= colCount; i++) {
			headers.add(rsmd.getColumnName(i));
		}
		results.add(headers);
		while (rs.next()) {
			ArrayList<String> row = new ArrayList<String>();
			for (int i = 1; i <= colCount; i++) {
				row.add(rs.getString(i));
			}
			results.add(row);
		}
		return results;
	}

	public ArrayList<String> getTableNames() {
		ArrayList<String> tables = new ArrayList<>();
		Statement stmt;
		try {
			Class.forName(DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(URL, user, pass);
			stmt = conn.createStatement();
			String sql = "SELECT table_name FROM information_schema.tables " + " WHERE table_schema='companydb'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				tables.add(rs.getString(1));
			}
			stmt.close();
			conn.close();
		} catch (SQLException sqle) {
			System.err.println(sqle);
		} catch (Exception e) {
			System.err.println(e);
		}
		return tables;
	}

}
