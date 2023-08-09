package com.cjj.common.web.exception;

import cn.hutool.http.HttpStatus;
import com.cjj.common.exception.BizException;
import com.cjj.common.result.Result;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Collection;
import java.util.stream.Collectors;


/**
 * @author 陈建军
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 参数校验异常
     */
    @ExceptionHandler(BindException.class)
    public Result processException(BindException e) {
        e.printStackTrace();
        String msg = e.getAllErrors().stream().map(item ->
                item.getDefaultMessage()).collect(Collectors.joining(","));
        return Result.error(HttpStatus.HTTP_BAD_REQUEST, msg);
    }

    /**
     * 自定义业务异常
     */
    @ExceptionHandler(BizException.class)
    public Result processException(BizException e) {
        e.printStackTrace();
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 其他业务异常
     */
    @ExceptionHandler(Throwable.class)
    public Result processException(Throwable e) {
        e.printStackTrace();
        return Result.error(HttpStatus.HTTP_INTERNAL_ERROR, e.getMessage());
    }
}
