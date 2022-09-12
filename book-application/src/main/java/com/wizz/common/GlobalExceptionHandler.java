package com.wizz.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@ResponseBody
@ControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseResult<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());

        if(ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");
            String msg = split[2];
            return ResponseResult.error("添加对象已存在");
        }
        return ResponseResult.error("未知错误");
    }

    @ExceptionHandler(MyServiceException.class)
    public ResponseResult<String> exceptionHandler(MyServiceException ex){
        log.error(ex.getMessage());
        return ResponseResult.error(ex.getMessage());
    }
}
