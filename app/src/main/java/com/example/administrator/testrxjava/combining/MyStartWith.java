package com.example.administrator.testrxjava.combining;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/8/27.
 */
public class MyStartWith {
    private static final String TAG = "MyStartWith";

    public static void test() {
        Observable.just(1, 2, 3)
                .startWith(-1, 0)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError" + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, "onNext " + integer);
                    }
                });
    }
}
