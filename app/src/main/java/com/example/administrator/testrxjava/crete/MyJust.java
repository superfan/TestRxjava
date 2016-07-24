package com.example.administrator.testrxjava.crete;


import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/7/24.
 */
public class MyJust {
    private static final String TAG = "MyJust";

    public static void test() {
//        Observable.just(1, 2, 3)
//                .subscribe(new Subscriber<Integer>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i(TAG, "onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, "onError" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.i(TAG, "onNext" + integer);
//                    }
//                });

        Observable.just(null)
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError" + e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.i(TAG, "onNext " + (o != null ? "not null" : "null"));
                    }
                });
    }
}
