package com.game.store.utils;

import java.io.Serializable;

/**
 * 响应结果类
 * @param <E> 响应数据的类型
 */
public class JsonResult<E> implements Serializable {
    /*响应状态码*/
    private Integer state;
    /*描述信息*/
    private String message;
    /*数据*/
    private E data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }
    public JsonResult(Throwable throwable) {
        this.message = throwable.getMessage();
    }

    public JsonResult(Integer state,  E data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}

