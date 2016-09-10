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
public class MyReplay {
    private static final String TAG = "MyReplay";

    private static ConnectableObservable<Long> relayCountObserver() {
        Observable<Long> obser = Observable.interval(1, TimeUnit.SECONDS);
        obser.observeOn(Schedulers.io());
        return obser.replay(2);
    }

    private static ConnectableObservable<Long> relayTimeObserver() {
        Observable<Long> obser = Observable.interval(1, TimeUnit.SECONDS);
        obser.observeOn(Schedulers.io());
        return obser.replay(3, TimeUnit.SECONDS);
    }

    public static void test() {
        ConnectableObservable<Long> connectableObservable = relayCountObserver();
        connectableObservable.subscribeOn(AndroidSchedulers.mainThread())
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
                        //Log.i(TAG, "onNext " + aLong);
                    }
                });
        connectableObservable.connect();

        try {
            Thread.sleep(6000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        connectableObservable
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted123");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError123 " + e.getMessage());
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i(TAG, "onNext123 " + aLong);
                    }
                });
    }
}
