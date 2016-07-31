package com.example.administrator.testrxjava.transforming;

import android.util.Log;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/7/31.
 */
public class MyMap {
    private static final String TAG = "MyMap";

    public static void test() {
        Observable.just("a", "b", "c")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + 100;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, s);
                    }
                });

        Observable.just("1", "2", "3")
               .cast(String.class)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, s);
                    }
                });
    }
}
