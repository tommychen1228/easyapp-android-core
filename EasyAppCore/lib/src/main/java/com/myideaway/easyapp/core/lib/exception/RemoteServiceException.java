package com.myideaway.easyapp.core.lib.exception;

/**
 * Created with IntelliJ IDEA.
 * User: cdm
 * Date: 12-4-18
 * Time: PM5:12
 * To change this template use File | Settings | File Templates.
 */
public class RemoteServiceException extends Exception{
    public RemoteServiceException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public RemoteServiceException(String detailMessage) {
        super(detailMessage);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public RemoteServiceException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public RemoteServiceException(Throwable throwable) {
        super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
