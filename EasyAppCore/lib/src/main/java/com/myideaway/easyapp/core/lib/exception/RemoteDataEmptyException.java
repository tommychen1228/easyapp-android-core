package com.myideaway.easyapp.core.lib.exception;

/**
 * Created with IntelliJ IDEA.
 * User: cdm
 * Date: 12-4-21
 * Time: AM10:34
 */
public class RemoteDataEmptyException extends RemoteServiceException {
    public RemoteDataEmptyException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public RemoteDataEmptyException(String detailMessage) {
        super(detailMessage);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public RemoteDataEmptyException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public RemoteDataEmptyException(Throwable throwable) {
        super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
