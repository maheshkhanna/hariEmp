//$Id$
package com.employee.api;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.storage.StorageWrapper;


@RestController
public class OperationController {
	@RequestMapping("/api/v1")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@RequestMapping("/api/v1/isValidUser")
	public String isValidUser(@RequestParam("userName") String userName,@RequestParam("password") String password) throws JSONException
	{
		JSONObject response = new JSONObject();
		
		JSONObject user = StorageWrapper.getUserInfo(userName, password);
		response.put("ID", user.optInt("ID"));
		return response.toString();
	}
	
	@RequestMapping("/api/v1/operations")
	public String getLastOpertaions(@RequestParam("limit") int limit) 
	{
		try {
			return StorageWrapper.getLastOerations(limit).toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JSONArray().toString();
	}
	
	@PostMapping("/api/v1/operation")
	public String storeOperation(@RequestBody String payload) 
	{
		JSONObject response = new JSONObject();
		try 
		{
			JSONObject oerationDetails = new JSONObject(payload);
			StorageWrapper.storeOperationDetails(oerationDetails);
			response.put("message","success");
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.put("message","Invalid user");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return response.toString();
	}
}
