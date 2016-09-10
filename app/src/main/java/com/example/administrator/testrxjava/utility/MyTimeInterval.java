package com.example.administrator.testrxjava.utility;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.TimeInterval;

/**
 * Created by Administrator on 2016/8/28.
 */
public class MyTimeInterval {
    private static final String TAG = "MyTimeInterval";

    public static void test() {
        Observable.just(1, 2, 3, 4, 5)
                .timeInterval()
                .subscribe(new Subscriber<TimeInterval<Integer>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError " + e.getMessage());
                    }

                    @Override
                    public void onNext(TimeInterval<Integer> integerTimeInterval) {
                        Log.i(TAG, "onNext: " + integerTimeInterval);
                    }
                });
    }
}
