package com.example.administrator.testrxjava.transforming;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/7/30.
 */
public class MyBuffer {
    private static final String TAG = "MyBuffer";

    public static void test() {
//        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
//                .buffer(new Func0<Observable<Long>>() {
//                    @Override
//                    public Observable<Long> call() {
//                        return Observable.interval(1, TimeUnit.SECONDS);
//                    }
//                })
//                .subscribe(new Subscriber<List<Integer>>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i(TAG, "onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i(TAG, "onError" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(List<Integer> integerList) {
//                        Log.i(TAG, "onNext");
//                        for (Integer integer : integerList) {
//                            Log.i(TAG, String.valueOf(integer));
//                        }
//
//                        Log.i(TAG, "-----------------------");
//                    }
//                });

        Observable.interval(2, TimeUnit.SECONDS)
                .buffer(5, TimeUnit.SECONDS)
                .subscribe(new Subscriber<List<Long>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError" + e.getMessage());
                    }

                    @Override
                    public void onNext(List<Long> longs) {
                        Log.i(TAG, "onNext");
                        for (Long l : longs) {
                            Log.i(TAG, String.valueOf(l));
                        }

                        Log.i(TAG, "-----------------------");
                    }
                });
    }
}
