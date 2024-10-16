package br.sc.senai.exception;

public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = -2095596359683414035L;
	public final static String NAO_ENCONTRADO_MESSAGE = " não encontrado";
	
	public NotFoundException(String field) {
		super(field + NAO_ENCONTRADO_MESSAGE);
	}
	
}
