package com.example.administrator.testrxjava.crete;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/7/24.
 */
public class MyCreate {
    private static final String TAG = "MyCreate";

    public static void test() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        for (int i = 1; i < 5; i++) {
                            observer.onNext(i);
                        }
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        } ).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                Log.i(TAG, "Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                Log.i(TAG, "Sequence complete.");
            }
        });
    }

}
