package com.example.administrator.testrxjava.filter;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/8/20.
 */
public class MyFirst {
    private static final String TAG = "MyFirst";

    public static void test() {
        Observable.just(1, 2, 3)
                .firstOrDefault(10, new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 5;
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        Log.i(TAG, "Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.i(TAG, "Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "Sequence complete.");
                    }
                });
    }
}
