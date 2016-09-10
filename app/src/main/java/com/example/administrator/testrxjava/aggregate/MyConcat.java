package com.example.administrator.testrxjava.aggregate;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/9/10.
 */
public class MyConcat {
    private static final String TAG = "MyConcat";

    public static void test() {
        Observable<Integer> observable1 = Observable.just(1, 2, 4);
        Observable<Integer> observable2 = Observable.just(3, 5, 6, 8);

        Observable.concat(observable1, observable2)
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
