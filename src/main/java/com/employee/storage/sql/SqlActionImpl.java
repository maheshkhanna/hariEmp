//$Id$
package com.employee.storage.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SqlActionImpl {
	private Connection connection;
	public SqlActionImpl(String url,String user,String pass) 
	{
		try{
			if(connection == null)
			{
				Class.forName("com.mysql.jdbc.Driver");
				this.connection = DriverManager.getConnection(url,user,pass);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void closeConnection() throws SQLException
	{
		this.connection.close();
	}
	
	public JSONArray executeQuery(String query) throws SQLException, JSONException
	{
		Statement stmt=connection.createStatement();  
		ResultSet rs=stmt.executeQuery(query); 
		return convert(rs);
	}
	
	public void executeDataManipulationQuery(String query) throws SQLException
	{
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.executeUpdate();
	}
	
	public static void main(String[] args) throws Exception {
		//Class.forName("com.mysql.jdbc.Driver");
		SqlActionImpl sql = new SqlActionImpl("jdbc:mysql://localhost:3306/Hari","root","");
		System.out.println(sql.executeQuery("select * from users"));
		sql.closeConnection();
	}
	
	public JSONArray convert(ResultSet rs) throws SQLException, JSONException {
		JSONArray arrayObj = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			int numColumns = rsmd.getColumnCount();
			JSONObject obj = new JSONObject();
			for (int i = 1; i < numColumns + 1; i++) {
				String column_name = rsmd.getColumnName(i);
				if (rsmd.getColumnType(i) == java.sql.Types.ARRAY) {
					obj.put(column_name, rs.getArray(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BIGINT) {
					obj.put(column_name, rs.getBigDecimal(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BOOLEAN) {
					obj.put(column_name, rs.getBoolean(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BLOB) {
					obj.put(column_name, rs.getBlob(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.DOUBLE) {
					obj.put(column_name, rs.getDouble(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.FLOAT) {
					obj.put(column_name, rs.getFloat(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
					obj.put(column_name, rs.getInt(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.NVARCHAR) {
					obj.put(column_name, rs.getNString(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {
					obj.put(column_name, rs.getString(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.TINYINT) {
					obj.put(column_name, rs.getInt(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.SMALLINT) {
					obj.put(column_name, rs.getInt(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
					obj.put(column_name, rs.getDate(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP) {
					obj.put(column_name, rs.getTimestamp(column_name));
				} else {
					obj.put(column_name, rs.getObject(column_name));
				}
			}
			arrayObj.put(obj);
		}
		return arrayObj;
	}
}
