package com.example.administrator.testrxjava.crete;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/7/24.
 */
public class MyTimer {
    private static final String TAG = "MyTimer";

    public static void test() {
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError " + e.getMessage());
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i(TAG, "onNext " + aLong);
                    }
                });
    }
}
