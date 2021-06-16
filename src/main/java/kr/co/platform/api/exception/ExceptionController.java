package kr.co.platform.api.exception;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.platform.util.advice.exception.AuthenticationEntryPointException;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/exception")
public class ExceptionController {
	
	
	/**
	 * @return
	 */
    @RequestMapping(value = {"/entrypoint"})
    public ResponseEntity<String> entrypointException() {
        throw new AuthenticationEntryPointException();
    }

    /**
     * @return
     */
    @RequestMapping(value = {"/accessdenied"})
    public ResponseEntity<String> accessdeniedException() {    	
        throw new AccessDeniedException("");
    }
}
