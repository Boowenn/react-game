package com.game.store.service.ex;

/**
 * @description 购物车数据不存在异常
 */
public class CartInfoNotExistsException extends ServiceException {
    public CartInfoNotExistsException() {
        super();
    }

    public CartInfoNotExistsException(String message) {
        super(message);
    }

    public CartInfoNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartInfoNotExistsException(Throwable cause) {
        super(cause);
    }

    protected CartInfoNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
