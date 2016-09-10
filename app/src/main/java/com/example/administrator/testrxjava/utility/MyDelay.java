package com.example.administrator.testrxjava.utility;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/8/28.
 */
public class MyDelay {
    private static final String TAG = MyDelay.class.getName();

    public static void test() {
        Observable.just(1, 2, 3, 4, 5)
                .delay(3, TimeUnit.SECONDS)
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
                        Log.i(TAG, "onNext: " + integer);
                    }
                });
    }

    public static void test2() {
        Observable.just(1, 2, 3, 4, 5)
                .delay(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        long interval = 2;
                        if (integer % 2 == 0) {
                            interval = 2;
                        } else {
                            interval = 3;
                        }

                        Log.i(TAG, "delay function 1: " + integer);
                        return Observable.interval(interval, TimeUnit.SECONDS).map(new Func1<Long, Integer>() {
                            @Override
                            public Integer call(Long aLong) {
                                Log.i(TAG, "delay function2: " + integer);
                                return integer;
                            }
                        });
                    }
                })
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
                        Log.i(TAG, "onNext: " + integer);
                    }
                });
    }

    public static void test3() {
        Observable.just(1, 2, 3, 4, 5)
                .delaySubscription(3, TimeUnit.SECONDS)
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
                        Log.i(TAG, "onNext: " + integer);
                    }
                });
    }
}
