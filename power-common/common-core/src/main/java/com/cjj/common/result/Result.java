package com.cjj.common.result;
import cn.hutool.http.HttpStatus;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)   //支持链式编程
public class Result<T> {

    // 响应状态码 200 表示成功，其它代码是错误
    private int code;
    // 响应消息
    private String msg;
    // 响应结果
    private T data;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 请求成功
     */
    public static Result success() {
        return new Result(HttpStatus.HTTP_OK, "请求成功");
    }

    // 请求成功
    public static <T> Result<T> success(T data) {
        return Result.success().setData(data);
    }


    /**
     * 请求成功
     */
    public static <T> Result<T> success(String msg, T data) {
        return Result.success(data).setMsg(msg);
    }

    /**
     * 请求失败
     */
    public static Result error(int code,String msg) {
        return new Result(code, msg);
    }


    // 判断请求是否失败
    public boolean isError() {
        return this.code != 200;
    }
}
