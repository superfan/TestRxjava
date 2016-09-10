package com.example.administrator.testrxjava.aggregate;

import android.util.Log;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func2;

/**
 * Created by Administrator on 2016/9/10.
 */
public class MyReduce {
    private static final String TAG = "MyReduce";

    public static void test() {
        Observable.just(1, 2, 3)
                .reduce(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        return integer * integer2;
                    }
                })
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

    public static void test2() {
        Observable.just(1, 2, 3)
                .collect(new Func0<ArrayList<Integer>>() {
                    @Override
                    public ArrayList<Integer> call() {
                        return new ArrayList<>();
                    }
                }, new Action2<ArrayList<Integer>, Integer>() {
                    @Override
                    public void call(ArrayList<Integer> arrayList, Integer integer) {
                        arrayList.add(integer);
                    }
                })
                .subscribe(new Subscriber<ArrayList>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError " + e.getMessage());
                    }

                    @Override
                    public void onNext(ArrayList arrayList) {
                        Log.i(TAG, "onNext " + arrayList.size());
                    }
                });
    }
}
