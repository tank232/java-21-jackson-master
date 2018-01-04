package com.tel.ran;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MetodRun {
    static List<MetodRun> MetodRuns = new ArrayList<>();
    private  Object objectRun;
    private Method methodRun;

    public static List<MetodRun> getMetodRuns() {
        return MetodRuns;
    }

    public MetodRun(Object classRun, Method methodRun) {
        this.objectRun = classRun;
        this.methodRun = methodRun;
        MetodRuns.add(this);
    }

    public Object getObjectRun() {
        return objectRun;
    }

    public Method getMethodRun() {
        return methodRun;
    }
}
