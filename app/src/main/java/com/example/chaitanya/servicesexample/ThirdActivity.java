package com.example.chaitanya.servicesexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by chaitanyatanna on 3/11/16.
 */
public class ThirdActivity extends AppCompatActivity {

    EditText editTextFirst, editTextSecond;
    Button buttonAdd, buttonBind, buttonUnbind;
    BindedServices bindedServices;
    boolean status;

    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Result: ", "SonServiceConnected");

            BindedServices.LocalBinder binder = (BindedServices.LocalBinder) service;
            bindedServices = binder.getService();
            status = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("Result: ", "onServiceDisconnected");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_third);

        editTextFirst = (EditText) findViewById(R.id.et_first);
        editTextSecond = (EditText) findViewById(R.id.et_second);
        buttonAdd = (Button) findViewById(R.id.btn_add);
        buttonBind = (Button) findViewById(R.id.btn_bind);
        buttonUnbind = (Button) findViewById(R.id.btn_unbind);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status) {
                    int number1 = Integer.parseInt(editTextFirst.getText().toString());
                    int number2 = Integer.parseInt(editTextSecond.getText().toString());
                    int result = bindedServices.addNumbers(number1, number2);
                    Log.d("Result: ", "Result: " + result);
                } else {
                    Log.d("Result: ", "Bind the Service First");
                }
            }
        });



//        buttonBind.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ThirdActivity.this, BindedServices.class);
//                bindService(intent, ThirdActivity.this.serviceConnection, Context.BIND_AUTO_CREATE);
//                status = true;
//                Log.d("Third: ", "Service Binded Successfully");
//            }
//        });

        buttonUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status) {
                    unbindService(serviceConnection);
                    Log.d("Result: ", "Service unbinded successfully");
                    status = false;

                } else {
                    Log.d("Result: ", "Service already unbinded");
                }
            }
        });

    }

    public void bindService(View view) {
        Intent intent = new Intent(ThirdActivity.this, BindedServices.class);
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                status = true;
                Log.d("Third: ", "Service Binded Successfully");
    }
}
