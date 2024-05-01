package com.shb.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundHandler(ResourseNotFoundException exception){

        Map map = new HashMap();

        map.put("Message",exception.getMessage());
        map.put("Success",false);
        map.put("Status",HttpStatus.NOT_FOUND);


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
