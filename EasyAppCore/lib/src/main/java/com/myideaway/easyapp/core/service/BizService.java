package com.myideaway.easyapp.core.service;


import android.content.Context;

public abstract class BizService extends Service {
    protected Context context;

    public BizService(Context context) {
        this.context = context;
    }
}
