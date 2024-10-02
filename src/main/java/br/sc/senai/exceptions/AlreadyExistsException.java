package br.sc.senai.exceptions;

public class AlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 3258108157139751502L;
	public final static String JA_EXISTE_MESSAGE = " já existe";
	
	public AlreadyExistsException(String field) {
		super(field + JA_EXISTE_MESSAGE);
	}
}
