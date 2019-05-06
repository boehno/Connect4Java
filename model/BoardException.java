package model;

import java.io.IOException;

public class BoardException extends IOException{ //exceptions
	
	private static final long serialVersionUID = 1L;

	public BoardException(String error) {
		super(error);
	}
}
