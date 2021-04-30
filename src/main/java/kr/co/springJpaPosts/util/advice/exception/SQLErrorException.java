package kr.co.springJpaPosts.util.advice.exception;

import org.springframework.dao.DataAccessException;

public class SQLErrorException extends DataAccessException{

	public SQLErrorException(String msg, Throwable t) {
        super(msg, t);
    }
	
	public SQLErrorException(String msg) {
		super(msg);
	}
	
	public SQLErrorException() {
		super("");
	}
	
}
