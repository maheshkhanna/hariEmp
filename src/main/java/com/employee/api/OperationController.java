//$Id$
package com.employee.api;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
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
	public String isValidUser(@RequestParam("userName") String userName,@RequestParam("password") String password)
	{
		JSONObject response = new JSONObject();
		response.put("isValid", StorageWrapper.checkIfTheUserIsValid(userName, password));
		return response.toString();
	}
	
	@RequestMapping("/api/v1/operations")
	public String getLastOpertaions(@RequestParam("limit") int limit) throws JSONException, SQLException
	{
		return StorageWrapper.getLastOerations(limit).toString();
	}
}
