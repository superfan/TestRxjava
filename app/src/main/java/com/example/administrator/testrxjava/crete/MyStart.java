package com.example.administrator.testrxjava.crete;


import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.util.async.Async;

/**
 * Created by Administrator on 2016/7/24.
 */
public class MyStart {
    private static final String TAG = "MyStart";

    public static void test() {
        Observable<String> observable = Async.start(new Func0<String>() {
            @Override
            public String call() {
                return "hello world";
            }
        });

        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext " + s);
            }
        });

        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted 2");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError 2 " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext 2 " + s);
            }
        });
    }

    public static void test2() {
//        Observable.defer(Async.toAsync(new Func0<String>() {
//            @Override
//            public String call() {
//                return "aaabbbccc";
//            }
//        })).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                Log.i(TAG, "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i(TAG, "onError " + e.getMessage());
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.i(TAG, "onNext " + s);
//            }
//        });

        Observable.defer(Async.toAsync(new Func0<String>() {
            @Override
            public String call() {
                return "aaabbbccc";
            }
        })).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext " + s);
            }
        });
    }
}
