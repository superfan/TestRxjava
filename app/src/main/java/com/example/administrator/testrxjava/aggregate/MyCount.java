package com.example.administrator.testrxjava.aggregate;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/9/10.
 */
public class MyCount {
    private static final String TAG = "MyCount";

    public static void test() {
        Observable.just(1, 2, 3)
                .count()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, "onNext " + integer);
                    }
                });
    }
}
