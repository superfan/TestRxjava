package com.example.administrator.testrxjava.transforming;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/7/31.
 */
public class MyWindow {
    private static final String TAG = "MyWindow";

    public static void test() {
        Observable.interval(1, TimeUnit.SECONDS)
                .window(5)
                .subscribe(new Action1<Observable<Long>>() {
                    @Override
                    public void call(Observable<Long> observable) {
                        System.out.println("subdivide begin......");
                        observable.subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                System.out.println("Next:" + aLong);
                            }
                        });
                    }
                });
    }
}
