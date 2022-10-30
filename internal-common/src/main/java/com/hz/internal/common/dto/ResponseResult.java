package com.hz.internal.common.dto;

import com.hz.internal.common.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther: huangzhong
 * @Date: 2022/10/29 - 10 - 29 - 17:20
 * @Description: com.hz.internal.common.dto
 * @version: 1.0
 */
@Data
@Accessors(chain = true) //开启链式调用
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    /**
     * 无参的构造方法
     * @param <T>
     * @return
     */
    public static <T> ResponseResult success(){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue());
    }

    /**
     * 响应成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }

    /**
     * 默认的失败响应
     * @param data
     * @param <T>
     * @return
     */
    public static <T>ResponseResult fail(T data){
        return new ResponseResult().setData(data);
    }
    /**
     * 响应失败
     * @param code
     * @param message
     * @return
     */
    public static ResponseResult fail(int code,String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * 响应失败（重载）
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ResponseResult fail(int code,String message,String data){
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }
}
