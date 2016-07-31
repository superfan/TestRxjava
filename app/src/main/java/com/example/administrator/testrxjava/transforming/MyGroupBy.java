package com.example.administrator.testrxjava.transforming;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created by Administrator on 2016/7/31.
 */
public class MyGroupBy {
    private static final String TAG = "MyGroupBy";

    public static void test() {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .groupBy(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long value) {
                        //按照key为0,1,2分为3组
                        return value % 3;
                    }
                })
                .subscribe(new Action1<GroupedObservable<Long, Long>>() {
                    @Override
                    public void call(final GroupedObservable<Long, Long> result) {
                        Log.i(TAG, "--GroupedObservable--");
                        result.subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long value) {
                                Log.i(TAG, "key:" + result.getKey() + ", value:" + value);
                                //System.out.println("key:" + result.getKey() + ", value:" + value);
                            }
                        });
                    }
                });
    }
}
