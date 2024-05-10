package org.wallstreetWolf.common.response;

public interface IResponseStatusCode {
    /**
     * 获取响应状态码
     *
     * @return 响应状态码
     */
    Integer getCode();

    /**
     * 获取响应消息
     *
     * @return 响应消息
     */
    String getMessage();
}