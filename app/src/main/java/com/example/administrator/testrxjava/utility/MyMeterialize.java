package com.example.administrator.testrxjava.utility;

import android.util.Log;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/8/28.
 */
public class MyMeterialize {
    private static final String TAG = "MyMeterialize";

    public static void test() {
        Observable.just(1, 2, 3)
                .materialize()
                .subscribe(new Subscriber<Notification<Integer>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError" + e.getMessage());
                    }

                    @Override
                    public void onNext(Notification<Integer> integerNotification) {
                        Log.i(TAG, "onNext: " + integerNotification.getValue());
                    }
                });
    }
}
