package com.main.java.VDSample;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class ObjectResource {
	
	private JSONArray objectJSONArray;
	private JSONObject objectJSONObject;
	private long unixTime = System.currentTimeMillis() / 1000L;
	private String unixTimeTemp;
	private String stringOutput;
	private boolean databaseIsEmpty = true;
	private boolean timeStampFound = false;
	
	public ObjectResource() {
		initializeObjectJSONArray(); // Initiate ObjectJSONArray if it is null (empty database)
	}
	
	@POST
	@Path("object")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(InputStream inputJSON) throws IOException {
		String stringInput = "";
		// Parse JSON
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inputJSON));
			String line = "";
			while ((line = in.readLine()) != null) {
				stringInput += line + "\n";
			}
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Input JSON Parsing Error").build();
		}
		
		JSONObject obj = new JSONObject(stringInput);
		
		try {
			String myKeyPlaceHolder =  obj.getString("mykey"); // Extract mykey from stringInput
			myKeyPlaceHolder.replaceAll("\\s+",""); // Remove spaces for mykey
			unixTime = System.currentTimeMillis() / 1000L;; // Get current unixTime
			
			objectJSONObject = new JSONObject(); // Put mykey and unixTime into JSONObject
			objectJSONObject.put("mykey", myKeyPlaceHolder);
			objectJSONObject.put("unixTime", unixTime);
			
			objectJSONArray.put(objectJSONObject); // Put JSONObject into JSONArray (database)
			
		} catch (Exception ex) {
			return Response.status(Response.Status.NOT_FOUND).entity("mykey attribute not found in input JSON").build();
		}
		
		databaseIsEmpty = false; // Update databaseIsEmpty flag to denote database is not empty
		// return HTTP response 200 in case of success
		return Response.status(Response.Status.OK);
	}
	
	@GET
	@Path("object/mykey")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getMyKey(@QueryParam("timestamp") long timeStamp) throws IOException {
		timeStamp.replaceAll("\\s+",""); // Remove spaces for timeStamp
		if (timeStamp == null) { // Check if timestamp QueryParam exists
			
			if (databaseIsEmpty) { 
				// return HTTP response 404 not found if no record in JSONArray (database)
				return Reponse.status(Response.Status.NOT_FOUND).entity("mykey attribute not found in database").build();
			}
			else {
				stringOutput = "";
				objectJSONObject = objectJSONArray.getJSONObject(objectJSONArray.length() - 1); // Get last JSONObject in JSONArray (database)
				stringOutput = objectJSONObject.getString("mykey");
				// return HTTP response 200 in case of success
				return Response.ok(stringOutput, MediaType.TEXT_PLAIN).build();
			}
		}
		else { // timestamp QueryParam exists
			stringOutput = "";
			timeStampNotFound = true;
			
			// Find timeStamp match
			for (int i = 0; i < objectJSONArray.length ; i++) {
				objectJSONObject = objectJSONArray.getJSONObject(i);
				unixTimeTemp = objectJSONObject.getString("unixTime");
				unixTimeTemp.replaceAll("\\s+",""); // Remove spaces for unixTimeTemp
				
				if (unixTimeTemp == timeStamp) {
					stringOutput = objectJSONObject.getString("mykey");
					timeStampNotFound = false;
					break; // Break away from for loop
				}
			}
			if (timeStampNotFound) { // If Query Not Found
				return Reponse.status(Response.Status.NOT_FOUND).entity("mykey attribute not found for the corresponding timestamp").build();
			}
			else { // Query Found
				return Response.ok(stringOutput, MediaType.TEXT_PLAIN).build();
			}
			
		}
	}
	
	private void initializeObjectJSONArray() {
		if (objectJSONArray == null) {
			objectJSONArray = new JSONArray();
			databaseIsEmpty = true;
		}
	}
	
}