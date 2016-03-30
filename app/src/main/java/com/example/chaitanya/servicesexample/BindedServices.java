package com.example.chaitanya.servicesexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by chaitanyatanna on 3/11/16.
 */
public class BindedServices extends Service {

    private final IBinder mBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {

        public BindedServices getService() {
            return BindedServices.this;
        }

    }

    public int addNumbers(int x, int y){
        return x + y;
    }
}
