package com.example.administrator.testrxjava.transforming;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by Administrator on 2016/7/30.
 */
public class MyFlatMap {
    private static final String TAG = "MyFlatMap";

    public static void test() {
//        Observable.just(10, 20, 30)
//                .flatMap(new Func1<Integer, Observable<Integer>>() {
//                    @Override
//                    public Observable<Integer> call(Integer integer) {
//                        //10的延迟执行时间为200毫秒、20和30的延迟执行时间为180毫秒
//                        int delay = 5;
//
//                        // 10, 5
//                        // 20, 10
//                        // 30, 15
//                        return Observable.from(new Integer[]{integer, integer / 2})
//                                .delay(delay, TimeUnit.SECONDS);
//                    }
//                })
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.i(TAG, "flatMap Next:" + integer);
//                    }
//                });

        Observable.just(10, 20, 30)
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                             @Override
                             public Observable<Integer> call(Integer integer) {
                                 return Observable.from(new Integer[]{integer, integer / 2})
                                         .delay(3, TimeUnit.SECONDS);
                             }
                         },
                        new Func1<Throwable, Observable<Integer>>() {
                            @Override
                            public Observable<Integer> call(Throwable throwable) {
                                return Observable.just(100);
                            }
                        },
                        new Func0<Observable<Integer>>() {
                            @Override
                            public Observable<Integer> call() {
                                return Observable.just(200);
                            }
                        })
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

    public static void test2() {
        Observable.just(10, 20, 30)
                .concatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        //10的延迟执行时间为200毫秒、20和30的延迟执行时间为180毫秒
                        int delay = 5;

                        // 10, 5
                        // 20, 10
                        // 30, 15
                        return Observable.from(new Integer[]{integer, integer / 2})
                                .delay(delay, TimeUnit.SECONDS);
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i(TAG, "flatMap Next:" + integer);
                    }
                });
    }

    public static void test3() {
        Observable.just(10, 20, 30)
                .switchMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        //10的延迟执行时间为200毫秒、20和30的延迟执行时间为180毫秒
                        int delay = 5;

                        // 10, 5
                        // 20, 10
                        // 30, 15
                        return Observable.from(new Integer[]{integer, integer / 2})
                                .delay(delay, TimeUnit.SECONDS);
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i(TAG, "flatMap Next:" + integer);
                    }
                });
    }

    public static void test4() {
        Observable.just(10, 20, 30)
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        return Observable.just(123, 234, 567);
                    }
                }, new Func2<Integer, Integer, String>() {
                    @Override
                    public String call(Integer integer, Integer integer2) {
                        return "[" + integer + ", " + integer2 + "]";
                    }
                }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "flatMap onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "flatMap onError:" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "flatMap Next:" + s);
            }
        });
    }
}
