package com.example.administrator.testrxjava.utility;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/4.
 */
public class MyUsing {
    private static final String TAG = "MyUsing";

    public static void test() {
        usingObserver()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError " + e.getMessage());
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i(TAG, "onNext " + aLong);
                    }
                });
    }

    private static Observable<Long> usingObserver() {
        return Observable.using(() -> new Animal(), i -> Observable.timer(5000,TimeUnit.MILLISECONDS), o -> o.release());
    }

    private static class Animal {
        Subscriber subscriber = new Subscriber() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "Animal onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "Animal onError " + e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG, "Animal animal eat");
            }
        };

        public Animal() {
            Log.i(TAG, "create animal");
            Observable.interval(1000, TimeUnit.MILLISECONDS)
                    .subscribe(subscriber);
        }

        public void release() {
            Log.i(TAG, "animal released");
            subscriber.unsubscribe();
        }
    }
}
