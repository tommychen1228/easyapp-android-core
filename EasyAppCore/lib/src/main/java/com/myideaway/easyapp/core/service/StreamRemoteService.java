package com.myideaway.easyapp.core.service;

import com.myideaway.easyapp.core.Config;
import com.myideaway.easyapp.core.L;
import com.myideaway.easyapp.core.exception.RemoteServiceException;
import com.squareup.okhttp.*;
import roboguice.util.Ln;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public abstract class StreamRemoteService extends RemoteService {

    @Override
    protected Object onExecute() throws RemoteServiceException {
        try {

            InputStream result = null;

            if (formFiles != null) {

                L.d("Send post multipart url " + postUrl + ", param " + params);

                MultipartBuilder multipartBuilder = new MultipartBuilder();
                multipartBuilder.type(MultipartBuilder.FORM);

                for (FormFile formFile : formFiles) {
                    multipartBuilder.addFormDataPart(formFile.getName(), null,  RequestBody.create(null, formFile.getFile()));
                }

                for (String key : params.keySet()) {
                    Object value = params.get(key);
                    multipartBuilder.addFormDataPart(key, String.valueOf(value));
                }

                RequestBody requestBody = multipartBuilder.build();

                Request request = new Request.Builder()
                        .url(postUrl)
                        .post(requestBody)
                        .build();

                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.setConnectTimeout(Config.REMOTE_SERVICE_TIME_OUT, TimeUnit.MILLISECONDS);

                Response response = okHttpClient.newCall(request).execute();
                result = response.body().byteStream();

            } else {
                if (requestMethod == REQUEST_METHOD_GET) {
                    L.d("Send get url " + postUrl + ", param " + params);

                    StringBuffer appendParams = new StringBuffer();
                    for (String key : params.keySet()) {
                        Object value = params.get(key);

                        appendParams.append(key).append("&").append(value);
                    }

                    Request request = new Request.Builder()
                            .url(postUrl + "?" + appendParams.toString())
                            .build();

                    OkHttpClient okHttpClient = new OkHttpClient();
                    okHttpClient.setConnectTimeout(Config.REMOTE_SERVICE_TIME_OUT, TimeUnit.MILLISECONDS);

                    Response response = okHttpClient.newCall(request).execute();
                    result = response.body().byteStream();
                } else {

                    L.d("Send post url " + postUrl + ", param " + params);

                    FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
                    for (String key : params.keySet()) {
                        Object value = params.get(key);
                        formEncodingBuilder.add(key, String.valueOf(value));
                    }

                    RequestBody requestBody = formEncodingBuilder.build();

                    Request request = new Request.Builder()
                            .url(postUrl)
                            .post(requestBody)
                            .build();

                    OkHttpClient okHttpClient = new OkHttpClient();
                    okHttpClient.setConnectTimeout(Config.REMOTE_SERVICE_TIME_OUT, TimeUnit.MILLISECONDS);

                    Response response = okHttpClient.newCall(request).execute();
                    result = response.body().byteStream();
                }
            }

            L.d("Result " + result);

            return result;
        } catch (Exception e) {
            throw new RemoteServiceException(e);
        }
    }
}
