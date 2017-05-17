package com.main.java.VDSample;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Object {
	
	private String myKey;
	// private int status;

    public Object(String myKey) {
        this.myKey = myKey;
    }

    public String getMyKey() {
        return myKey;
    }
	
	public String setMyKey() {
		this.myKey = myKey;
	}
	
	// public int getStatus() {
		// return status;
	// }
	
	// public int setStatus() {
		// this.status = status;
	// }
	
	// public String toString() {
        // return String.format("{mykey : %s}", myKey);
    // }
}