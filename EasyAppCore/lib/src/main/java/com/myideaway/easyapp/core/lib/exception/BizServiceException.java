package com.myideaway.easyapp.core.lib.exception;

/**
 * Created with IntelliJ IDEA.
 * User: cdm
 * Date: 12-4-19
 * Time: AM10:29
 * To change this template use File | Settings | File Templates.
 */
public class BizServiceException extends Exception {
    public BizServiceException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public BizServiceException(String detailMessage) {
        super(detailMessage);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public BizServiceException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public BizServiceException(Throwable throwable) {
        super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }
}