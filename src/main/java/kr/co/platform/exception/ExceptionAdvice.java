package kr.co.platform.exception;

import javassist.NotFoundException;

import kr.co.platform.exception.custom.*;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> defaultException(Exception e) throws Exception {
        e.printStackTrace();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", e.getMessage());

        JSONArray result = JSONArray.fromObject(resultMap);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result.get(0).toString());
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            UnsatisfiedServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> badRequestException(Exception e) throws Exception {
        e.printStackTrace();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", e.getMessage());

        JSONArray result = JSONArray.fromObject(resultMap);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.get(0).toString());
    }

    @ExceptionHandler({AuthenticationEntryPointException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> accessDeniedException(Exception e) throws Exception {
        e.printStackTrace();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", e.getMessage());

        JSONArray result = JSONArray.fromObject(resultMap);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.get(0).toString());
    }

    @ExceptionHandler({ForbiddenException.class, AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> forbiddenException(ForbiddenException e) throws Exception {
        e.printStackTrace();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", e.getMessage());

        JSONArray result = JSONArray.fromObject(resultMap);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result.get(0).toString());
    }

    @ExceptionHandler({
            NotFoundException.class,
            UserNotFoundException.class,
            EntityNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> notFoundException(NotFoundException e) throws Exception {
        e.printStackTrace();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", e.getMessage());

        JSONArray result = JSONArray.fromObject(resultMap);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result.get(0).toString());
    }

    @ExceptionHandler(DuplicatedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> DuplicatedException(DuplicatedException e) throws Exception {
        e.printStackTrace();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", e.getMessage());

        JSONArray result = JSONArray.fromObject(resultMap);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result.get(0).toString());
    }

    @ExceptionHandler(ApiOtherException.class)
    public ResponseEntity<String> ApiOtherException(ApiOtherException e) throws Exception {
        e.printStackTrace();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", e.getMessage());

        JSONArray result = JSONArray.fromObject(resultMap);
        return ResponseEntity.status(700).body(result.get(0).toString());
    }
}
