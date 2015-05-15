package com.myideaway.easyapp.core.lib.service;

import android.os.AsyncTask;

public abstract class Service {
    public static final int TASK_RESULT_SUCCESS = 1;
    public static final int TASK_RESULT_FAULT = 2;

    private AsyncTask<Integer, Integer, Integer> task;
    private boolean isCanceled = false;
    private Object executeResult;
    private OnSuccessHandler onSuccessHandler;
    private OnFaultHandler onFaultHandler;
    private OnCompleteHandler onCompleteHandler;

    private Exception executeException;

    public final Object syncExecute() throws Exception {
        isCanceled = false;

        try {
            executeResult = doExecute();

            if(onCompleteHandler != null){
                onCompleteHandler.onComplete(this);
            }

            if (onSuccessHandler != null) {
                onSuccessHandler.onSuccess(this, executeResult);
            }

        } catch (Exception ex) {
            if(onCompleteHandler != null){
                onCompleteHandler.onComplete(this);
            }

            executeException = ex;

            if (onFaultHandler != null) {
                onFaultHandler.onFault(this, executeException);
            }

            throw ex;
        }


        return executeResult;
    }

    public final Object syncExecute(OnCompleteHandler onCompleteHandler, OnSuccessHandler onSuccessHandler, OnFaultHandler onFaultHandler) throws Exception {
        setOnCompleteHandler(onCompleteHandler);
        setOnSuccessHandler(onSuccessHandler);
        setOnFaultHandler(onFaultHandler);

        executeResult = syncExecute();

        return executeResult;
    }


    public final void asyncExecute() {
        isCanceled = false;

        task = new AsyncTask<Integer, Integer, Integer>() {

            @Override
            protected Integer doInBackground(Integer... arg0) {
                try {
                    executeResult = doExecute();
                    return TASK_RESULT_SUCCESS;
                } catch (Exception e) {
                    executeException = e;
                }

                return TASK_RESULT_FAULT;
            }

            protected void onPostExecute(Integer result) {
                if(onCompleteHandler != null){
                    onCompleteHandler.onComplete(Service.this);
                }

                if (result == TASK_RESULT_SUCCESS) {
                    if (onSuccessHandler != null) {
                        onSuccessHandler.onSuccess(Service.this, executeResult);
                    }
                } else if (result == TASK_RESULT_FAULT) {
                    if (onFaultHandler != null) {
                        onFaultHandler.onFault(Service.this, executeException);
                    }
                }
            }
        }.execute(0);

    }

    public final void asyncExecute(OnCompleteHandler onCompleteHandler, OnSuccessHandler onSuccessHandler, OnFaultHandler onFaultHandler) {
        setOnCompleteHandler(onCompleteHandler);
        setOnSuccessHandler(onSuccessHandler);
        setOnFaultHandler(onFaultHandler);

        asyncExecute();
    }

    protected void willExecute() {

    }

    protected Object didExecute(Object result) {
        return result;
    }

    private Object doExecute() throws Exception {
        willExecute();
        Object result = onExecute();
        Object newResult = didExecute(result);

        return newResult;
    }

    public final void cancel() {
        isCanceled = true;
        task.cancel(true);
    }

    public Object getExecuteResult() {
        return executeResult;
    }

    protected abstract Object onExecute() throws Exception;

    public OnSuccessHandler getOnSuccessHandler() {
        return onSuccessHandler;
    }

    public void setOnSuccessHandler(OnSuccessHandler onSuccessHandler) {
        this.onSuccessHandler = onSuccessHandler;
    }

    public OnFaultHandler getOnFaultHandler() {
        return onFaultHandler;
    }

    public void setOnFaultHandler(OnFaultHandler onFaultHandler) {
        this.onFaultHandler = onFaultHandler;
    }

    public OnCompleteHandler getOnCompleteHandler() {
        return onCompleteHandler;
    }

    public void setOnCompleteHandler(OnCompleteHandler onCompleteHandler) {
        this.onCompleteHandler = onCompleteHandler;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public interface OnSuccessHandler {
        public void onSuccess(Service target, Object result);
    }

    public interface OnFaultHandler {
        public void onFault(Service target, Exception ex);
    }

    public interface OnCompleteHandler {
        public void onComplete(Service target);
    }
}
