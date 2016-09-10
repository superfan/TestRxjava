package com.example.administrator.testrxjava.combining;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/21.
 */
public class MyJoin {
    public static final String TAG = "MyJoin";

    public static void test() {
        //产生0,5,10,15,20数列
        Observable<Long> observable1 = Observable.timer(0, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        System.out.println("observable1: " + aLong * 5);
                        return aLong * 5;
                    }
                }).take(5);

        //产生0,10,20,30,40数列
        Observable<Long> observable2 = Observable.timer(500, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        System.out.println("observable2: " + aLong * 10);
                        return aLong * 10;
                    }
                }).take(5);

        observable1.join(observable2, new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(Long aLong) {
                //使Observable延迟600毫秒执行
                System.out.println("fun1: " + aLong);
                return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
            }
        }, new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(Long aLong) {
                //使Observable延迟600毫秒执行
                System.out.println("fun2: " + aLong);
                return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
            }
        }, new Func2<Long, Long, Long>() {
            @Override
            public Long call(Long aLong, Long aLong2) {
                System.out.println("fun3");
                return aLong + aLong2;
            }
        }).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("Next: " + aLong);
            }
        });
    }

    public static void test2() {
        joinObserver()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError " + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, "onNext " + s);
                    }
                });
    }

    private static Observable<String> createObserver() {
//        return Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                for (int i = 1; i < 5; i++) {
//                    subscriber.onNext("Right-" + i);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).subscribeOn(Schedulers.io());

        return Observable.interval(1000, TimeUnit.MILLISECONDS)
                .map(aLong -> "Right-" + aLong);
//                .map(new Func1<Long, String>() {
//                    @Override
//                    public String call(Long aLong) {
//                        return "Right-" + aLong;
//                    }
//                });
    }

    private static Observable<String> joinObserver() {
        return Observable.just("Left-").join(createObserver(),
                integer -> Observable.timer(3000, TimeUnit.MILLISECONDS),
                integer -> Observable.timer(2000, TimeUnit.MILLISECONDS),
                (i, j) -> i + j
        );
    }

    private static Observable<Observable<String>> groupJoinObserver() {
        return Observable.just("Left-").groupJoin(createObserver(),
                        s -> Observable.timer(3000, TimeUnit.MILLISECONDS),
                        s -> Observable.timer(2000, TimeUnit.MILLISECONDS),
                        (s, stringObservable) -> stringObservable.map(str -> s + str));
    }
}
