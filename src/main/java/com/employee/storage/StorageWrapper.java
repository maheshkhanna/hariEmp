//$Id$
package com.employee.storage;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.employee.operation.Operation;
import com.employee.storage.sql.SqlActionImpl;

public class StorageWrapper 
{
	private static SqlActionImpl sqlActionImpl  = new SqlActionImpl("jdbc:mysql://localhost:3306/Hari","root","");
	
	
	public static boolean checkIfTheUserIsValid(String userName,String password)
	{
		try {
			JSONArray users = sqlActionImpl.executeQuery("SELECT * from users WHERE USER_NAME='"+userName+"' AND PASSWORD='"+password+"'");
			return users.length() > 0;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	
	public static JSONArray getLastOerations(int limit) throws JSONException, SQLException
	{
		return sqlActionImpl.executeQuery("SELECT *,(SELECT USER_NAME FROM USERS WHERE USERS.ID=OPERATION_DETAILS.USER_ID ) AS USER_NAME FROM OPERATION_DETAILS left join USERS ON USERS.ID=OPERATION_DETAILS.USER_ID ORDER BY RECORD_ID DESC LIMIT "+limit);
	}
}	
