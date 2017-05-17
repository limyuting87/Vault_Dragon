package com.main.java.VDSample;

import java.io.IOException;

import java.util.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.xmlpull.v1.XmlPullParserException;

import com.main.java.VDSample.Object


public class ObjectResource {
	
	private static List ObjectPlaceHolder;
	private boolean isEmpty;
	
	public ObjectResource() {
		initializeObject();
	}
	
	@POST
	@Path("object")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Status insert(Object object) {
		
	}
	
	@GET
	@Path("object/mykey")
	@Produces(MediaType.APPLICATION_JSON)
	public ObjectResource getMyKey() throws IOException {
		List ObjectPlaceHolderList = new ArrayList;
		if (isEmpty == true) {
			return "{mykey : }"
		}
		else {
			return ObjectPlaceHolderList.toArray(new Object[ObjectPlaceHolderList.size()]);
		}
	}
	
	private void initializeObject() {
		if (ObjectPlaceHolder == null) {
			ObjectPlaceHolder = new ArrayList();
			ObjectPlaceHolder = new Object("");
			isEmpty = true;
		}
	}
	
}