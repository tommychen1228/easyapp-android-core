package com.myideaway.easyapp.core.sample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.myideaway.easyapp.core.lib.L;
import com.myideaway.easyapp.core.lib.service.BizService;
import com.myideaway.easyapp.core.lib.service.BizServiceResult;
import com.myideaway.easyapp.core.lib.service.RemoteService;
import com.myideaway.easyapp.core.lib.service.Service;
import org.json.JSONObject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by cdm on 15/1/30.
 */
public class UploadFileActivity extends RoboActivity {
    @InjectView(R.id.sendButton)
    private Button sendButton;
    @InjectView(R.id.resultTextView)
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_get);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendButtonClick();
            }
        });

    }


    private void onSendButtonClick() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();

        FileBSUpload fileBSUpload = new FileBSUpload(UploadFileActivity.this);
        fileBSUpload.asyncExecute(new Service.OnSuccessHandler() {
            @Override
            public void onSuccess(Service target, Object result) {
                progressDialog.dismiss();

                FileBSUpload.ServiceResult serviceResult = (FileBSUpload.ServiceResult) result;

                L.d(serviceResult.getDate().toString());

                resultTextView.setText(serviceResult.getDate().toString());
            }
        }, new Service.OnFaultHandler() {
            @Override
            public void onFault(Service target, Exception ex) {
                progressDialog.dismiss();
            }
        });

    }

    class FileBSUpload extends BizService {

        public FileBSUpload(Context context) {
            super(context);
        }

        @Override
        protected BizServiceResult onExecute() throws Exception {

            ArrayList files = new ArrayList();

            RemoteService.FormFile formFile = new RemoteService.FormFile();

            formFile.setName("file");
            formFile.setFile(new File(Environment.getExternalStorageDirectory().getPath() + "/test.jpg"));
            files.add(formFile);

            JSONObject jsonObject = (JSONObject) remoteJSON(RemoteService.REQUEST_METHOD_POST, "http://xxxxxxxx", null, files);

            ServiceResult serviceResult = new ServiceResult();
            serviceResult.setDate(jsonObject);

            return serviceResult;
        }

        class ServiceResult extends BizServiceResult {

        }

    }

}
