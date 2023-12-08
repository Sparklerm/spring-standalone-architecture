package com.yiyan.boot.common.model.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yiyan.boot.common.constant.BizConstant;
import com.yiyan.boot.common.enums.BizCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 接口统一返回
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result<T> extends BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回数据
     */
    private T data;

    /**
     * Instantiates a new Result.
     */
    public Result() {
    }

    public Result(String code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public static <T> Result<T> success(String code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> success(BizCodeEnum status, T data) {
        return success(status.getCode(), status.getMessage(), data);
    }

    public static <T> Result<T> success(BizCodeEnum status) {
        return success(status.getCode(), status.getMessage(), null);
    }

    public static <T> Result<T> success(T data) {
        return success(BizCodeEnum.SUCCESS, data);
    }

    public static <T> Result<T> success() {
        return success(BizCodeEnum.SUCCESS, null);
    }

    public static Result<String> createSuccess() {
        return new Result<>(BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.SUCCESS.getMessage(), BizConstant.DEFAULT_CREATE_OPTION_SUCCESS_MESSAGE);
    }

    public static <T> Result<String> createSuccess(T data) {
        return new Result<>(BizCodeEnum.SUCCESS.getCode(),
                BizCodeEnum.SUCCESS.getMessage(),
                BizConstant.DEFAULT_CREATE_OPTION_SUCCESS_MESSAGE + " : " + data);
    }

    public static Result<String> updateSuccess() {
        return new Result<>(BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.SUCCESS.getMessage(), BizConstant.DEFAULT_UPDATE_OPTION_SUCCESS_MESSAGE);
    }

    public static <T> Result<String> updateSuccess(T data) {
        return new Result<>(BizCodeEnum.SUCCESS.getCode(),
                BizCodeEnum.SUCCESS.getMessage(),
                BizConstant.DEFAULT_UPDATE_OPTION_SUCCESS_MESSAGE + " : " + data);
    }

    public static Result<String> deleteSuccess() {
        return new Result<>(BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.SUCCESS.getMessage(), BizConstant.DEFAULT_DELETE_OPTION_SUCCESS_MESSAGE);
    }

    public static <T> Result<String> deleteSuccess(T data) {
        return new Result<>(BizCodeEnum.SUCCESS.getCode(),
                BizCodeEnum.SUCCESS.getMessage(),
                BizConstant.DEFAULT_DELETE_OPTION_SUCCESS_MESSAGE + " : " + data);
    }

    public static <T> Result<T> error(String code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> error(String code, String message) {
        return error(code, message, null);
    }

    public static <T> Result<T> error(BizCodeEnum status, T data) {
        return error(status.getCode(), status.getMessage(), data);
    }

    public static <T> Result<T> error(BizCodeEnum status) {
        return error(status.getCode(), status.getMessage(), null);
    }

    public static <T> Result<T> error(T data) {
        return error(BizCodeEnum.ERROR, data);
    }

    public static <T> Result<T> error() {
        return error(BizCodeEnum.ERROR, null);
    }

}

