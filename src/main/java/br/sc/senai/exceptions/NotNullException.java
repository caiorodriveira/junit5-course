package br.sc.senai.exceptions;

public class NotNullException extends RuntimeException{

	private static final long serialVersionUID = -994042892757577840L;
	public final static String NOT_NULL_MESSAGE = " não pode ser nulo";
	
	public NotNullException(String field) {
		super(field + NOT_NULL_MESSAGE);
	}
	
	
	
}
