package com.diary.diary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ControllerServiceMethods {
    public static Map<String, String> createErrorResponseJson(String errorCode, String message) {
        Map<String, String> errorDict = new HashMap<>();
        errorDict.put("status", errorCode);
        errorDict.put("message", message);
        return errorDict;
    }

    public static ResponseEntity<Object> getErrorResponse(HttpStatus status, String statusCode, String message) {
        Map<String, String> errorJson = createErrorResponseJson(statusCode, message);
        return new ResponseEntity<>(errorJson, status);
    }
}
