package com.example.administrator.testrxjava.filter;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/8/20.
 */
public class MyLast {
    private static final String TAG = "MyLast";

    public static void test() {
        Observable.just(1, 2, 3)
                .last()
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
