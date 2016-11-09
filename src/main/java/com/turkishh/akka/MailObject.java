package com.turkishh.akka;

import java.io.Serializable;

/**
 * www.turkishh.com
 * 
 * Mehmet KILIÇ
 */
public class MailObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public MailObject(String message) {
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
