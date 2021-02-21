//$Id$
package com.employee.storage;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.employee.operation.Operation;
import com.employee.storage.sql.SqlActionImpl;

public class StorageWrapper 
{
	private static SqlActionImpl sqlActionImpl  = new SqlActionImpl("jdbc:mysql://localhost:3306/Hari","root","");
	
	
	public static JSONObject getUserInfo(String userName,String password)
	{
		try 
		{
			JSONArray users = sqlActionImpl.executeQuery("SELECT * from users WHERE USER_NAME='"+userName+"' AND PASSWORD='"+password+"'");
			if( users.length() > 0)
			{
				return users.getJSONObject(0);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JSONObject();	
	}
	
	public static JSONArray getLastOerations(int limit) throws JSONException, SQLException
	{
		return sqlActionImpl.executeQuery("SELECT *,(SELECT USER_NAME FROM USERS WHERE USERS.ID=OPERATION_DETAILS.USER_ID ) AS USER_NAME FROM OPERATION_DETAILS left join USERS ON USERS.ID=OPERATION_DETAILS.USER_ID ORDER BY RECORD_ID DESC LIMIT "+limit);
	}
	
	public static void storeOperationDetails(JSONObject operationDetails) throws SQLException, JSONException
	{	
		long timeout = operationDetails.getLong("TIME_OUT");
		long timeIn =operationDetails.getLong("TIME_IN");
		long duration = timeout - timeIn;
		sqlActionImpl.executeDataManipulationQuery("INSERT INTO OPERATION_DETAILS (USER_ID,FIRST_OFF,PRODUCT_LANE,PART_NUMBER,STATUS,TIME_IN,TIME_OUT,DURATION) VALUES ("
				+operationDetails.getLong("USER_ID")+",'"+operationDetails.getString("FIRST_OFF")+"','"+operationDetails.getString("PRODUCT_LANE")+"','"+operationDetails.getString("PART_NUMBER")+"','"+operationDetails.getString("STATUS")+"',"+timeIn+","+timeout+","+duration+")");
	}
}	
