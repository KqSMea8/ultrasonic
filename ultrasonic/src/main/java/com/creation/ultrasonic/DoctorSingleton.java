package com.creation.ultrasonic;

import com.creation.ultrasonic.impl.Doctor;

public class DoctorSingleton {
    private static final String TAG = "DoctorSingleton";

    private static IDoctor sDoctor = new Doctor();

    public static IDoctor instance() {
        return sDoctor;
    }

    private DoctorSingleton() {

    }
}
