package model.exception;

import java.io.IOException;

public class EArquivoOrigemIncorreto extends IOException{

	private static final long serialVersionUID = 1L;
	
	public EArquivoOrigemIncorreto(String msg) {
		super(msg);
	}

}
