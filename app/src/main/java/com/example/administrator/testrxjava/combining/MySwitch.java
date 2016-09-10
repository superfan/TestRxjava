package com.example.administrator.testrxjava.combining;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/27.
 */
public class MySwitch {
    private static final String TAG = "MySwitch";

    public static void test() {
        //每隔500毫秒产生一个observable
        Observable<Observable<String>> observable = Observable.timer(0, 500, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Observable<String>>() {
                    @Override
                    public Observable<String> call(Long aLong1) {
                        //每隔200毫秒产生一组数据（0,10,20,30,40)
                        return Observable.timer(0, 200, TimeUnit.MILLISECONDS).map(new Func1<Long, String>() {
                            @Override
                            public String call(Long aLong2) {
                                return String.valueOf(aLong1) + ": " + aLong2 * 10;
                            }
                        }).take(5);
                    }
                }).take(2);

        Observable.switchOnNext(observable).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(String string) {
                System.out.println("Next:" + string);
            }
        });
    }

    private static Observable<String> switchObserver() {
        return Observable.switchOnNext(Observable.create(
                new Observable.OnSubscribe<Observable<String>>() {
                    @Override
                    public void call(Subscriber<? super Observable<String>> subscriber) {
                        for (int i = 1; i < 3; i++) {
                            subscriber.onNext(createObserver(i));
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ));
    }

    private static Observable<String> createObserver(int index) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 1; i < 5; i++) {
                    subscriber.onNext(index + "-" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).subscribeOn(Schedulers.newThread());
    }

    public static void test2() {
        switchObserver()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError; " + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, "onNext; " + s);
                    }
                });
    }
}
