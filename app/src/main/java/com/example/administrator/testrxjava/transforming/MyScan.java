package com.example.administrator.testrxjava.transforming;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

/**
 * Created by Administrator on 2016/7/31.
 */
public class MyScan {
    private static final String TAG = "MyScan";

    public static void test() {
        Observable.just(1, 2, 3, 4, 5)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer item) {
                        return sum + item;
                    }
                }).subscribe(new Subscriber<Integer>() {
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
