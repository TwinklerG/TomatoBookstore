package com.tomatobookstore.tomatobookstore.exception;

import com.tomatobookstore.tomatobookstore.vo.MessageVO;
import com.tomatobookstore.tomatobookstore.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = TomatoBookstoreException.class)
    public ResultVO<MessageVO> handleAllExternalException(TomatoBookstoreException e) {
        // e.printStackTrace();
        return ResultVO.buildFailure(e.getMessage());
    }
}
