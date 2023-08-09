package com.cjj.common.exception;
import lombok.Getter;

/**
 * 自定义业务异常类
 */

public class BizException extends RuntimeException {
    @Getter
    private int code;

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
    }
    // public static void main(String[] args) {
    //     BizException e = new BizException(401,"认证失败");
    //
    //     System.out.println(e.getCode());
    //     System.out.println(e.getMessage());
    //     throw e;
    // }
}
// throw new BizException();


