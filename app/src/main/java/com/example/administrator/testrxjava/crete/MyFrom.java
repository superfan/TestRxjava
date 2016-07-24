package com.example.administrator.testrxjava.crete;


import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/7/24.
 */
public class MyFrom {
    private static final String TAG = "MyFrom";

    public static void test() {
        Observable.from(new Integer[]{1, 2, 3})
        .subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext" + integer);
            }
        });
    }
}
