package org.wallstreetWolf.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure<T>{
    private Integer code;
    private String status;
    private String message;
    private T data;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";


    public static <T> ResponseStructure<T> success(T data) {
        return new ResponseStructure<>(
                ResponseStatusCodeEnum.SUCCESS.getCode(),
                SUCCESS,
                ResponseStatusCodeEnum.SUCCESS.getMessage(),
                data
        );
    }

    public static <T> ResponseStructure<T> created(String message) {
        return new ResponseStructure<>(
                ResponseStatusCodeEnum.UPDATE_RETURN_201.getCode(),
                SUCCESS,
                message,
                null);
    }

    public static <T> ResponseStructure<T> unauthorized(String message) {
        return new ResponseStructure<>(
                ResponseStatusCodeEnum.ALL_RETURN_403.getCode(),
                FAIL,
                message,
                null);
    }

    public static <T> ResponseStructure<T> unauthenticated(String message) {
        return new ResponseStructure<>(
                ResponseStatusCodeEnum.ALL_RETURN_401.getCode(),
                FAIL,
                message,
                null);
    }

    public static <T> ResponseStructure<T> success(String message, T data) {
        return new ResponseStructure<>(
                ResponseStatusCodeEnum.SUCCESS.getCode(),
                SUCCESS,
                message,
                data);
    }

    public static <T> ResponseStructure<T> success(Integer code, String message) {
        return new ResponseStructure<>(code, SUCCESS, message, null);
    }

    public static <T> ResponseStructure<T> success(Integer code, String message, T data) {
        return new ResponseStructure<>(code, SUCCESS, message, data);
    }

    public static ResponseStructure<Object> failed() {
        return new ResponseStructure<>(
                ResponseStatusCodeEnum.GET_RETURN_500.getCode(),
                FAIL,
                ResponseStatusCodeEnum.GET_RETURN_500.getMessage(),
                null);
    }

    public static ResponseStructure<String> failed(String message) {
        return new ResponseStructure<>(
                ResponseStatusCodeEnum.GET_RETURN_500.getCode(),
                FAIL,
                message,
                null);
    }

    public static ResponseStructure<Object> failed(IResponseStatusCode errorResult) {
        return new ResponseStructure<>(
                errorResult.getCode(),
                FAIL,
                errorResult.getMessage(),
                null);
    }

    public static ResponseStructure<Object> conflict(String message) {
        return new ResponseStructure<>(
                ResponseStatusCodeEnum.CONFLICT_RETURN_409.getCode(),
                FAIL,
                message,
                null);
    }

    public static <T> ResponseStructure<T> instance(Integer code, String message, T data) {
        ResponseStructure<T> responseStructure = new ResponseStructure<>();
        responseStructure.setCode(code);
        responseStructure.setMessage(message);
        responseStructure.setData(data);

        if (code >= 300) {
            responseStructure.setStatus(FAIL);
        } else {
            responseStructure.setStatus(SUCCESS);
        }

        return responseStructure;
    }
    public static <T> ResponseStructure<T> instance(Integer code, String message) {
        ResponseStructure<T> responseStructure = new ResponseStructure<>();
        responseStructure.setCode(code);
        responseStructure.setMessage(message);
        responseStructure.setData(null);

        if (code >= 300) {
            responseStructure.setStatus(FAIL);
        } else {
            responseStructure.setStatus(SUCCESS);
        }

        return responseStructure;
    }

    public static <T> ResponseStructure<T> instance(IResponseStatusCode code) {
        ResponseStructure<T> responseStructure = new ResponseStructure<>();
        responseStructure.setCode(code.getCode());
        responseStructure.setMessage(code.getMessage());
        responseStructure.setData(null);

        if (code.getCode() >= 300) {
            responseStructure.setStatus(FAIL);
        } else {
            responseStructure.setStatus(SUCCESS);
        }

        return responseStructure;
    }
}