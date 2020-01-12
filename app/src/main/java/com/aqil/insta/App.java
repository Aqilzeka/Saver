package com.aqil.insta;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("oJrR8TaA8PraXLB3dq9l3hw1Q3uWrlbTn9bi0IlD")
                .clientKey("T3RFZvDEo0lxYxxbwpHHS6eKB3mq1NKprX1e2RKN")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
