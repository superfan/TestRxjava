package com.example.administrator.testrxjava.filter;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/8/20.
 */
public class MyDistinct {
    private static final String TAG = "MyDistinct";

    public static void test() {
        Observable.just(1, 2, 4, 5, 1, 2)
                .distinct()
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
                        Log.i(TAG, "onNext" + integer);
                    }
                });
    }
}
