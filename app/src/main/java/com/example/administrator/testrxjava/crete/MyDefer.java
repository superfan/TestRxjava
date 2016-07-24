package com.example.administrator.testrxjava.crete;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;

/**
 * Created by Administrator on 2016/7/24.
 */
public class MyDefer {
    private static final String TAG = "MyDefer";
    private static int i;

    public static void test() {
        i = 10;
        Observable justObservable = Observable.just(i);

        i = 12;
        Observable deferObservable = Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                return Observable.just(i);
            }
        });

        i = 15;
        justObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG, "just result:" + o.toString());
            }
        });

        deferObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG, "defer result111:" + o.toString());
            }
        });

        i = 2222;
        deferObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG, "defer result222:" + o.toString());
            }
        });

        justObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG, "just result:" + o.toString());
            }
        });
    }
}
