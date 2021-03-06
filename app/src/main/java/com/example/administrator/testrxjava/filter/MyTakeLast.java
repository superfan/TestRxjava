package com.example.administrator.testrxjava.filter;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/8/20.
 */
public class MyTakeLast {
    public static void test() {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .takeLast(2)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.err.println("Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });
    }
}
