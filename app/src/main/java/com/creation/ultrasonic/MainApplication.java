package com.creation.ultrasonic;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        DoctorSingleton.instance().initExamination(new IDoctor.IPrintFilter() {
            @Override
            public int indexOf(String printString) {
                return printString.indexOf("com.creation.ultrasonic");
            }
        }, IExamination.Type.Thread);
    }
}
