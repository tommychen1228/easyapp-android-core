package com.myideaway.easyapp.core.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class MainActivity extends RoboActivity {
    @InjectView(R.id.doGetButton)
    private Button doGetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDoGetButtonClick();
            }
        });
    }

    private void onDoGetButtonClick(){
        Intent intent = new Intent(this, SendGetActivity.class);
        startActivity(intent);
    }
}
