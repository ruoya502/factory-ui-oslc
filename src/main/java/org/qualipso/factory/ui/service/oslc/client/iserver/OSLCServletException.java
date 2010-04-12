package org.qualipso.factory.ui.service.oslc.client.iserver;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Jerome Blanchard <jayblanc@gmail.com>
 * @date 29 january 2010
 */
public class OSLCServletException extends Exception implements IsSerializable {

	private static final long serialVersionUID = 4741175806356479306L;
	private String message;

	public OSLCServletException(Exception e) {
		message = e.getMessage();
	}

	public OSLCServletException() {
	}

	public OSLCServletException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
