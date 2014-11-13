package org.peg4d.editorplugin.editors;

public class Peg4dSyntaxException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3667071600030661908L;
	private int code;
	
	public Peg4dSyntaxException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
}
