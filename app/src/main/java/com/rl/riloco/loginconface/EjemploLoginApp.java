package com.rl.riloco.loginconface;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by riloco on 25/02/17.
 */

public class EjemploLoginApp extends Application {

    @Override
    public void onCreate() {
        //Realm.init(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }



}
