package com.tomatobookstore.tomatobookstore.exception;

import com.tomatobookstore.tomatobookstore.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = TomatoBookstoreException.class)
    public ResultVO<String> handleAllExternalException(TomatoBookstoreException e) {
        // e.printStackTrace();
        if(!Objects.equals(e.getMessage(), "请登录"))
            return ResultVO.buildFailure(e.getMessage());
        else
            return ResultVO.buildNotLogin(e.getMessage());
    }
}
