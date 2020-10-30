package com.nikitha.android.bakingapp.arch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication {

    private static MyApplication myApplication;
    ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static MyApplication getInstance() {
        if (myApplication==null) {
            myApplication = new MyApplication();
        }
        return myApplication;
    }
}
