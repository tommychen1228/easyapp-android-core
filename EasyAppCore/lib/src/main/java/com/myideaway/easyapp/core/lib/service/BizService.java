package com.myideaway.easyapp.core.lib.service;


import android.content.Context;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public abstract class BizService extends Service {
    protected Context context;

    public BizService(Context context) {
        this.context = context;
    }

    @Override
    protected abstract BizServiceResult onExecute() throws Exception;

    protected String remoteString(final int method, final String url, final Map<String, Object> p, final List<RemoteService.FormFile> formFiles) throws Exception {
        StringRemoteService stringRemoteService = new StringRemoteService() {
            @Override
            protected Map<String, Object> getParams() {
                return p;
            }

            @Override
            protected String getURL() {
                return url;
            }

            @Override
            protected List<FormFile> getFormFiles() {
                return formFiles;
            }
        };

        stringRemoteService.setRequestMethod(method);
        String result = (String) stringRemoteService.syncExecute();
        return result;
    }

    protected String remoteString(final int method, final String url, final Map<String, Object> params) throws Exception {
        return remoteString(method, url, params, null);
    }

    protected Object remoteJSON(final int method, final String url, final Map<String, Object> p, final List<RemoteService.FormFile> formFiles) throws Exception {
        JSONStringRemoteService jsonStringRemoteService = new JSONStringRemoteService() {
            @Override
            protected Map<String, Object> getParams() {
                return p;
            }

            @Override
            protected String getURL() {
                return url;
            }

            @Override
            protected List<FormFile> getFormFiles() {
                return formFiles;
            }
        };

        jsonStringRemoteService.setRequestMethod(method);
        Object result = (Object) jsonStringRemoteService.syncExecute();
        return result;
    }

    protected Object remoteJSON(final int method, final String url, final Map<String, Object> params) throws Exception {
        return remoteJSON(method, url, params, null);
    }

    protected Object remoteJSON(final int method, final String url) throws Exception {
        return remoteJSON(method, url, null, null);
    }

    protected InputStream remoteStream(final int method, final String url, final Map<String, Object> p, final List<RemoteService.FormFile> formFiles) throws Exception {
        StreamRemoteService streamRemoteService = new StreamRemoteService() {
            @Override
            protected Map<String, Object> getParams() {
                return p;
            }

            @Override
            protected String getURL() {
                return url;
            }

            @Override
            protected List<FormFile> getFormFiles() {
                return formFiles;
            }
        };

        streamRemoteService.setRequestMethod(method);
        InputStream result = (InputStream) streamRemoteService.syncExecute();
        return result;
    }

    protected InputStream remoteStream(final int method, final String url, final Map<String, Object> p) throws Exception {
        return remoteStream(method, url, p, null);
    }

}
