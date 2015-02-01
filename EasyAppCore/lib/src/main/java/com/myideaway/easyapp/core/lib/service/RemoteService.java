package com.myideaway.easyapp.core.lib.service;

import java.io.File;
import java.util.List;
import java.util.Map;

public abstract class RemoteService extends Service {
    public static final int REQUEST_METHOD_POST = 1;
    public static final int REQUEST_METHOD_GET = 2;

	protected Map<String, Object> params;
    protected String requestUrl;
    protected List<FormFile> formFiles;
    protected int requestMethod = REQUEST_METHOD_POST;

    @Override
    protected void willExecute() {
        params = getParams();
        requestUrl = getURL();
        formFiles = getFormFiles();
    }

    protected abstract Map<String, Object> getParams();

	protected abstract String getURL();

    protected List<FormFile> getFormFiles(){
        return null;
    }

    public int getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(int requestMethod) {
        this.requestMethod = requestMethod;
    }

    public static class FormFile{
        private File file;
        private String name;

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
