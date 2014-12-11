package com.myideaway.easyapp.core.service;


import android.content.Context;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public abstract class BizService extends Service {
    protected Context context;

    public BizService(Context context) {
        this.context = context;
    }

    protected String remoteString(final String url, final List<Map<String, Object>> params, final List<RemoteService.FormFile> formFiles) throws Exception {
        StringRemoteService stringRemoteService = new StringRemoteService() {
            @Override
            protected Map<String, Object> getParams() {
                return params;
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

        String result = (String) stringRemoteService.syncExecute();
        return result;
    }

    protected String remoteString(final String url, final List<Map<String, Object>> params) throws Exception {
        return remoteString(url, params, null);
    }

    protected Object remoteJSON(final String url, final List<Map<String, Object>> params, final List<RemoteService.FormFile> formFiles) throws Exception {
        JSONStringRemoteService jsonStringRemoteService = new JSONStringRemoteService() {
            @Override
            protected Map<String, Object> getParams() {
                return params;
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

        Object result = (Object) jsonStringRemoteService.syncExecute();
        return result;
    }

    protected Object remoteJSON(final String url, final List<Map<String, Object>> params) throws Exception {
        return remoteJSON(url, params, null);
    }

    protected InputStream remoteStream(final String url, final List<Map<String, Object>> params, final List<RemoteService.FormFile> formFiles) throws Exception {
        StreamRemoteService streamRemoteService = new StreamRemoteService() {
            @Override
            protected Map<String, Object> getParams() {
                return params;
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

        InputStream result = (InputStream) streamRemoteService.syncExecute();
        return result;
    }

    protected InputStream remoteStream(final String url, final List<Map<String, Object>> params) throws Exception {
        return remoteStream(url, params, null);
    }

}
