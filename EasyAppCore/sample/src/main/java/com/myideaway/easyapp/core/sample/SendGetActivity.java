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
import org.json.JSONArray;
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

                L.d(serviceResult.getPm25().toString());

                resultTextView.setText(serviceResult.getPm25().toString());
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
            // 可以选择通过设置默认得data属性来设置结果
            //serviceResult.setDate(jsonObject);
            // 也可以通过自己给ServiceResult添加属性来封装结果


            JSONArray resultsJSONArray = jsonObject.optJSONArray("results");
            JSONObject cityJSONObject = resultsJSONArray.optJSONObject(0);
            String pm25 = cityJSONObject.optString("pm25");

            serviceResult.setPm25(pm25);

            return serviceResult;
        }

        class ServiceResult extends BizServiceResult {
            private String pm25;

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }
        }

    }

}
