package com.myideaway.easyapp.core.sample;

import android.app.Application;
import com.myideaway.easyapp.core.lib.L;

/**
 * Created by cdm on 15/2/1.
 */
public class EAApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        L.setTag("EasyAppCoreSample");
        L.setLogLevel(L.LOG_ALL);
    }
}
