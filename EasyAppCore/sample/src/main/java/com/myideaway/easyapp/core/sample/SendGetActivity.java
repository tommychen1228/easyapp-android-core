package com.myideaway.easyapp.core.sample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.myideaway.easyapp.core.lib.L;
import com.myideaway.easyapp.core.lib.service.BizService;
import com.myideaway.easyapp.core.lib.service.RemoteService;
import com.myideaway.easyapp.core.lib.service.BizServiceResult;
import com.myideaway.easyapp.core.lib.service.Service;
import org.json.JSONObject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.util.HashMap;

/**
 * Created by cdm on 15/1/30.
 */
public class SendGetActivity extends RoboActivity {
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

        WeatherBSGet weatherBSGet = new WeatherBSGet(SendGetActivity.this);
        weatherBSGet.asyncExecute(new Service.OnSuccessHandler() {
            @Override
            public void onSuccess(Service target, Object result) {
                progressDialog.dismiss();

                WeatherBSGet.ServiceResult serviceResult = (WeatherBSGet.ServiceResult) result;

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

    class WeatherBSGet extends BizService {

        public WeatherBSGet(Context context) {
            super(context);
        }

        @Override
        protected BizServiceResult onExecute() throws Exception {


            JSONObject jsonObject = (JSONObject) remoteJSON(RemoteService.REQUEST_METHOD_GET, "http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=mvkTbOvbFD6x9pXd0w64MlOQ");

            //HashMap params = new HashMap();
            //params.put("location", "北京");
            //params.put("output", "json");
            //params.put("ak", "mvkTbOvbFD6x9pXd0w64MlOQ");
            //JSONObject jsonObject = (JSONObject) remoteJSON(RemoteService.REQUEST_METHOD_GET, "http://api.map.baidu.com/telematics/v3/weather", params);

            ServiceResult serviceResult = new ServiceResult();
            serviceResult.setDate(jsonObject);

            return serviceResult;
        }

        class ServiceResult extends BizServiceResult {

        }

    }

}
