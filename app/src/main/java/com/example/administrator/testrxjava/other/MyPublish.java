package com.example.administrator.testrxjava.other;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/10.
 */
public class MyPublish {
    private static final String TAG = "MyPublish";

    public static void test() {
        ConnectableObservable<Long> connectableObservable = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .publish();

        connectableObservable
                .refCount()
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

        //connectableObservable.connect();
    }
}
