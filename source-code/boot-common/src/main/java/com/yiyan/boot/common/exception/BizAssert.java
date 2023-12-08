package com.yiyan.boot.common.exception;


import com.yiyan.boot.common.enums.BizCodeEnum;
import com.yiyan.boot.common.enums.StatusCodeEnumFormat;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;

/**
 * 断言处理类，用于抛出各种API异常
 *
 * @author Alex Meng
 * @createDate 2020-2-27
 */
public class BizAssert {

    private BizAssert() {
    }

    /**
     * Fail.
     *
     * @param message the message
     */
    public static void fail(String message) {
        throw new BizException(message);
    }

    /**
     * Fail.
     *
     * @param errorCode the error code
     */
    public static void fail(StatusCodeEnumFormat errorCode) {
        throw new BizException(errorCode);
    }

    /**
     * Fail.
     */
    public static void fail(StatusCodeEnumFormat errorCode, String message) {
        throw new BizException(errorCode, message);
    }

    /**
     * Fail.
     */
    public static void fail() {
        throw new BizException(BizCodeEnum.BIZ_ERROR);
    }

    /**
     * Fail.
     */
    public static void fail(boolean flag, String errorMessage) {
        if (flag) {
            throw new BizException(errorMessage);
        }
    }

    public static void isNull(@Nullable Object object, StatusCodeEnumFormat status) {
        if (null != object) {
            throw new BizException(status);
        }
    }

    public static void isNull(@Nullable Object object, String message) {
        if (null != object) {
            throw new BizException(message);
        }
    }

    public static void notNull(@Nullable Object object, StatusCodeEnumFormat status) {
        if (ObjectUtils.isEmpty(object)) {
            throw new BizException(status);
        }
    }

    public static void notNull(@Nullable Object object, String message) {
        if (ObjectUtils.isEmpty(object)) {
            throw new BizException(message);
        }
    }

    public static void notBlank(String object, StatusCodeEnumFormat status) {
        if (StringUtils.isBlank(object)) {
            throw new BizException(status);
        }
    }

    public static void notBlank(String object, String message) {
        if (StringUtils.isBlank(object)) {
            throw new BizException(message);
        }
    }

    public static void isTrue(boolean flag, String message) {
        if (!flag) {
            throw new BizException(message);
        }
    }

    public static void isTrue(boolean flag, StatusCodeEnumFormat status) {
        if (!flag) {
            throw new BizException(status);
        }
    }

    public static void isFalse(boolean flag, String message) {
        if (flag) {
            throw new BizException(message);
        }
    }

    public static void isFalse(boolean flag, StatusCodeEnumFormat status) {
        if (flag) {
            throw new BizException(status);
        }
    }
}
