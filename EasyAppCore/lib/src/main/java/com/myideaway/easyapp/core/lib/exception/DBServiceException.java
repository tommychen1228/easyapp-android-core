package com.myideaway.easyapp.core.lib.exception;

/**
 * Created with IntelliJ IDEA.
 * User: cdm
 * Date: 12-4-19
 * Time: AM10:29
 * To change this template use File | Settings | File Templates.
 */
public class DBServiceException extends Exception {
    public DBServiceException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DBServiceException(String detailMessage) {
        super(detailMessage);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DBServiceException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public DBServiceException(Throwable throwable) {
        super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
