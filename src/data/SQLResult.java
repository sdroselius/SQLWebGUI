package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLResult {
	private ArrayList<ArrayList<String>> resultSet;
	private Integer updateCount;
	private SQLException exception;
	private String sql;
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public ArrayList<ArrayList<String>> getResultSet() {
		return resultSet;
	}
	public void setResultSet(ArrayList<ArrayList<String>> resultSet) {
		this.resultSet = resultSet;
	}
	public SQLResult() {
	}
	public SQLResult(ArrayList<ArrayList<String>> resultSet, int updateCount, SQLException exception) {
		this.resultSet = resultSet;
		this.updateCount = updateCount;
		this.exception = exception;
	}
	public Integer getUpdateCount() {
		return updateCount;
	}
	public void setUpdateCount(int updateCount) {
		this.updateCount = new Integer(updateCount);
	}
	public SQLException getException() {
		return exception;
	}
	public void setException(SQLException exception) {
		this.exception = exception;
	}

}
