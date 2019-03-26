package com.creation.ultrasonic;

public interface IDoctor extends IExamination {
    interface IPrintFilter {
        int indexOf(String printString);
    }

    void initExamination(IPrintFilter printFilter, Type... types);
    IPrintFilter getPrintFilter();
    void setPrintFilter(IPrintFilter printFilter);
    void finish();
}
