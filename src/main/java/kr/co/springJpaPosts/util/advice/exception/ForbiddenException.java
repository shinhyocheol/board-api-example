package kr.co.springJpaPosts.util.advice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
	
	public ForbiddenException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public ForbiddenException(String msg) {
		super(msg);
	}
	
	public ForbiddenException() {
		super();
	}
}
