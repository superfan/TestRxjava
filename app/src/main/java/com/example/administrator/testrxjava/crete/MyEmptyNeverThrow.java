package com.example.administrator.testrxjava.crete;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/7/24.
 */
public class MyEmptyNeverThrow {
    private static final String TAG = "MyEmptyNeverThrow";

    public static void test() {
//        Observable.empty()
//                .subscribe(new Subscriber<Object>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i(TAG, "onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i(TAG, "onError " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(Object o) {
//                        Log.i(TAG, "onNext " + ((o != null) ? "not null" : "null"));
//                    }
//                });

//        Observable.never()
//                .subscribe(new Subscriber<Object>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i(TAG, "onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i(TAG, "onError " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(Object o) {
//                        Log.i(TAG, "onNext " + ((o != null) ? "not null" : "null"));
//                    }
//                });

        Observable.error(new Throwable("abc"))
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError " + e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.i(TAG, "onNext " + ((o != null) ? "not null" : "null"));
                    }
                });
    }
}
