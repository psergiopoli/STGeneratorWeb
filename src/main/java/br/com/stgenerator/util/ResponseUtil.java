package br.com.stgenerator.util;

public class ResponseUtil {
	
	public ResponseUtil(String message) {
		this.message = message;
	}
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}