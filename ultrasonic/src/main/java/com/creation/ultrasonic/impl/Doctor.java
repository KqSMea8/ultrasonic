package com.creation.ultrasonic.impl;

import com.creation.ultrasonic.IDoctor;
import com.creation.ultrasonic.IProbe;
import com.creation.ultrasonic.impl.normal.bitmap.BitmapExamination;
import com.creation.ultrasonic.impl.normal.looper.LooperExamination;
import com.creation.ultrasonic.impl.normal.thread.ThreadExamination;

import java.util.ArrayList;

public class Doctor extends Examination implements IDoctor {
    private IPrintFilter mPrintFilter;
    private Type[] mTypes;

    public Doctor() {

    }

    @Override
    public void initExamination(IPrintFilter printFilter, Type... types) {
        mTypes = types;
        setPrintFilter(printFilter);
        init();
        start();
    }

    @Override
    public IPrintFilter getPrintFilter() {
        return mPrintFilter;
    }

    @Override
    public void setPrintFilter(IPrintFilter printFilter) {
        mPrintFilter = printFilter;
    }

    @Override
    public void finish() {
        stop();
        clearProbe();
    }

    @Override
    protected void startInner() {
        for (IProbe probe : mProbeList) {
            probe.start();
        }
    }

    @Override
    protected void stopInner() {
        for (IProbe probe : mProbeList) {
            probe.stop();
        }
    }

    @Override
    protected void initInner() {
        super.initInner();
        for (IProbe probe : mProbeList) {
            probe.init();
        }
    }

    @Override
    protected ArrayList<IProbe> initProbeList() {
        ArrayList<IProbe> arrayList = new ArrayList<>();
        for (Type type : mTypes) {
            switch (type) {
                case Bitmap:
                    arrayList.add(new BitmapExamination());
                    break;
                case Looper:
                    arrayList.add(new LooperExamination());
                    break;
                case Thread:
                    arrayList.add(new ThreadExamination());
                    break;
            }
        }
        return arrayList;
    }
}
