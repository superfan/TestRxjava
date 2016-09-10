package com.example.administrator.testrxjava;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.style.SubscriptSpan;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.testrxjava.aggregate.MyConcat;
import com.example.administrator.testrxjava.aggregate.MyReduce;
import com.example.administrator.testrxjava.combining.MyCombineLatest;
import com.example.administrator.testrxjava.combining.MyJoin;
import com.example.administrator.testrxjava.combining.MyMerge;
import com.example.administrator.testrxjava.combining.MyStartWith;
import com.example.administrator.testrxjava.combining.MySwitch;
import com.example.administrator.testrxjava.combining.MyZip;
import com.example.administrator.testrxjava.crete.MyCreate;
import com.example.administrator.testrxjava.crete.MyDefer;
import com.example.administrator.testrxjava.crete.MyEmptyNeverThrow;
import com.example.administrator.testrxjava.crete.MyFrom;
import com.example.administrator.testrxjava.crete.MyInterval;
import com.example.administrator.testrxjava.crete.MyJust;
import com.example.administrator.testrxjava.crete.MyRange;
import com.example.administrator.testrxjava.crete.MyRepeat;
import com.example.administrator.testrxjava.crete.MyStart;
import com.example.administrator.testrxjava.crete.MyTimer;
import com.example.administrator.testrxjava.error.MyCatch;
import com.example.administrator.testrxjava.error.MyRetry;
import com.example.administrator.testrxjava.filter.MyDebounce;
import com.example.administrator.testrxjava.filter.MyDistinct;
import com.example.administrator.testrxjava.filter.MyElementAt;
import com.example.administrator.testrxjava.filter.MyFilter;
import com.example.administrator.testrxjava.filter.MyFirst;
import com.example.administrator.testrxjava.filter.MyIgnoreElements;
import com.example.administrator.testrxjava.filter.MyLast;
import com.example.administrator.testrxjava.filter.MySample;
import com.example.administrator.testrxjava.filter.MySkip;
import com.example.administrator.testrxjava.filter.MySkipLast;
import com.example.administrator.testrxjava.filter.MyTake;
import com.example.administrator.testrxjava.filter.MyTakeFirst;
import com.example.administrator.testrxjava.filter.MyTakeLast;
import com.example.administrator.testrxjava.other.MyPublish;
import com.example.administrator.testrxjava.other.MyReplay;
import com.example.administrator.testrxjava.transforming.MyBuffer;
import com.example.administrator.testrxjava.transforming.MyFlatMap;
import com.example.administrator.testrxjava.transforming.MyGroupBy;
import com.example.administrator.testrxjava.transforming.MyMap;
import com.example.administrator.testrxjava.transforming.MyScan;
import com.example.administrator.testrxjava.transforming.MyWindow;
import com.example.administrator.testrxjava.utility.MyDelay;
import com.example.administrator.testrxjava.utility.MyDo;
import com.example.administrator.testrxjava.utility.MyMeterialize;
import com.example.administrator.testrxjava.utility.MyTimeInterval;
import com.example.administrator.testrxjava.utility.MyTimeOut;
import com.example.administrator.testrxjava.utility.MyTimeStamp;
import com.example.administrator.testrxjava.utility.MyUsing;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.test_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyReplay.test();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void test1() {
        Observable.just("Hello, world!")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return 100;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i(TAG, String.valueOf(integer));
                    }
                });
    }

    private void test2() {
        Observable.just("Hello, world!")
                .concatMap(new Func1<String, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(String s) {
                        if (s == null) {
                            throw new NullPointerException("");
                        }
                        return null;
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }
                });
    }

    private int i;

    private void test3() {
        i = 10;
        Observable justObservable = Observable.just(i);

        i = 12;
        Observable deferObservable = Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                return Observable.just(i);
            }
        });

        i = 15;
        justObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG, "just result:" + o.toString());
            }
        });

        deferObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG, "defer result:" + o.toString());
            }
        });
    }

    private void test4() {
//        Observable.range(1, 3)
//                .repeat(2)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Integer>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.i(TAG, String.valueOf(integer));
//                    }
//                });


//        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
//                .buffer(2, 3)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<Integer>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<Integer> integerList) {
//                        for (Integer integer : integerList) {
//                            Log.i(TAG, String.valueOf(integer));
//                        }
//
//                        Log.i(TAG, "-----------------------");
//                    }
//                });


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

    private void test5() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer + 100;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, String.valueOf(integer));
                    }
                });
    }

    private void test6() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        return integer + integer2;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, String.valueOf(integer));
                    }
                });
    }

    private void test7() {
        Observable.interval(1, TimeUnit.SECONDS)
                //.take(12)
                .window(3, TimeUnit.SECONDS)
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
//        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
//                .window(3)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Observable<Integer>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Observable<Integer> integerObservable) {
//                        integerObservable.subscribe(new Action1<Integer>() {
//                            @Override
//                            public void call(Integer integer) {
//                                Log.i(TAG, String.valueOf(integer));
//                            }
//                        });
//                    }
//                });
    }

    private void test8() {
        //flatMap操作符的运行结果
        Observable.just(10, 20, 30)
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        //10的延迟执行时间为200毫秒、20和30的延迟执行时间为180毫秒
                        int delay = 200;
//                        if (integer > 10)
//                            delay = 180;

                        // 10, 5
                        // 20, 10
                        // 30, 15
                        return Observable.from(new Integer[]{integer, integer / 2})
                                .delay(delay, TimeUnit.MILLISECONDS);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i(TAG, "flatMap Next:" + integer);
                    }
                });


        //concatMap操作符的运行结果
        Observable.just(10, 20, 30)
                .concatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        //10的延迟执行时间为200毫秒、20和30的延迟执行时间为180毫秒
                        int delay = 200;
                        //if (integer > 10)
                        //    delay = 180;

                        return Observable.from(new Integer[]{integer, integer / 2})
                                .delay(delay, TimeUnit.MILLISECONDS);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i(TAG, "concatMap Next:" + integer);
                        //System.out.println("concatMap Next:" + integer);
                    }
                });


        //switchMap操作符的运行结果
        Observable.just(10, 20, 30)
                .switchMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        //10的延迟执行时间为200毫秒、20和30的延迟执行时间为180毫秒
                        int delay = 200;
                        if (integer > 10)
                            delay = 180;

                        return Observable.from(new Integer[]{integer, integer / 2})
                                .delay(delay, TimeUnit.MILLISECONDS);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("switchMap Next:" + integer);
                    }
                });
    }

    private void test9() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                try {
                    //产生结果的间隔时间分别为100、200、300...900毫秒
                    for (int i = 1; i < 10; i++) {
                        subscriber.onNext(i);
                        Thread.sleep(i * 100);
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        })
                .subscribeOn(Schedulers.newThread())
                .debounce(400, TimeUnit.MILLISECONDS)  //超时时间为400毫秒
                .subscribe(
                        new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                System.out.println("Next:" + integer);
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                System.out.println("Error:" + throwable.getMessage());
                            }
                        },
                        new Action0() {
                            @Override
                            public void call() {
                                System.out.println("completed!");
                            }
                        });
    }

    private void test10() {
        Observable.just(1, 2, 1, 1, 2, 3)
                .distinct()
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

    private void test11() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .elementAt(2)
                .subscribe(
                        new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                System.out.println("Next:" + integer);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                System.out.println("Error:" + throwable.getMessage());
                            }
                        }, new Action0() {
                            @Override
                            public void call() {
                                System.out.println("completed!");
                            }
                        });
    }

    private void test12() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .single(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        //取大于10的第一个数字
                        return integer > 0;
                    }
                })
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

    private void test13() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .ignoreElements()
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

    private Observable<Integer> createObserver(final int index) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 1; i < 6; i++) {
                    subscriber.onNext(i * index);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }).subscribeOn(Schedulers.newThread());
    }

    private Observable<Integer> combineLatestObserver() {
        return Observable.combineLatest(createObserver(1),
                createObserver(2),
                new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        Log.i(TAG, "left:" + integer + " right:" + integer2);
                        return integer + integer2;
                    }
                });
    }

    private void test14() {
        combineLatestObserver()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("onNext: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.err.println("onError: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }
                });
    }

    private void test15() {
        /*//产生0,5,10,15,20数列
        Observable<Long> observable1 = Observable.timer(0, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 5;
                    }
                }).take(5);

        //产生0,10,20,30,40数列
        Observable<Long> observable2 = Observable.timer(500, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 10;
                    }
                }).take(5);

        observable1.join(observable2,
                new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long aLong) {
                        //使Observable延迟600毫秒执行
                        return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
                    }
                },
                new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long aLong) {
                        //使Observable延迟600毫秒执行
                        return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
                    }
                },
                new Func2<Long, Long, Long>() {
                    @Override
                    public Long call(Long aLong, Long aLong2) {
                        return aLong + aLong2;
                    }
                })
                .subscribe(new Subscriber<Long>() {
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
                });*/

        /*//产生0,5,10,15,20数列
        Observable<Long> observable1 = Observable.timer(0, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 5;
                    }
                }).take(5);

        //产生0,10,20,30,40数列
        Observable<Long> observable2 = Observable.timer(500, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 10;
                    }
                }).take(5);

        observable1.groupJoin(observable2,
                new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long aLong) {
                        return Observable.just(aLong).delay(1600, TimeUnit.MILLISECONDS);
                    }
                },
                new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long aLong) {
                        return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
                    }
                },
                new Func2<Long, Observable<Long>, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(final Long aLong, Observable<Long> observable) {
                        return observable.map(new Func1<Long, Long>() {
                            @Override
                            public Long call(Long aLong2) {
                                return aLong + aLong2;
                            }
                        });
                    }
                })
                .subscribe(new Subscriber<Observable<Long>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println("Error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Observable<Long> observable) {
                        observable.subscribe(new Subscriber<Long>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Long aLong) {
                                System.out.println("Next: " + aLong);
                            }
                        });
                    }
                });*/


        //产生0,5,10,15,20数列
        /*Observable<Long> observable1 = Observable.timer(0, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong;
                    }
                })
                .take(5)
                .subscribeOn(Schedulers.io());

        //产生0,10,20,30,40数列
        Observable<Long> observable2 = Observable.timer(500, 100, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong + 10;
                    }
                })
                .take(5)
                .subscribeOn(Schedulers.io());

        Observable.merge(observable1, observable2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
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
                        System.out.println("Next:" + aLong);
                    }
                });*/

        /*//每隔500毫秒产生一个observable
        Observable<Observable<Long>> observable = Observable.timer(0, 500, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long aLong) {
                        System.out.println("map call: " + aLong);
                        if (aLong == 0) {
                            //每隔200毫秒产生一组数据（0,10,20,30,40)
                            return Observable.timer(0, 200, TimeUnit.MILLISECONDS).map(new Func1<Long, Long>() {
                                @Override
                                public Long call(Long aLong) {
                                    return aLong;
                                }
                            }).take(5);
                        } else {
                            //每隔200毫秒产生一组数据（0,10,20,30,40)
                            return Observable.timer(0, 200, TimeUnit.MILLISECONDS).map(new Func1<Long, Long>() {
                                @Override
                                public Long call(Long aLong) {
                                    return aLong + 10;
                                }
                            }).take(5);
                        }
                    }
                }).take(2);

        Observable.switchOnNext(observable)
                .subscribe(new Subscriber<Long>() {
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
                        System.out.println("Next:" + aLong);
                    }
                });*/

        /*Observable<Integer> observable1 = Observable.just(10, 20, 30);
        Observable<Integer> observable2 = Observable.just(4, 8, 12, 16);
        Observable.zip(observable1, observable2, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("Next:" + value);
            }
        });*/

        /*Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                //循环输出数字
                try {
                    for (int i = 0; i < 10; i++) {
                        if (i == 4) {
                            throw new Exception("this is number 4 error！");
                        }
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

        observable.onErrorReturn(new Func1<Throwable, Integer>() {
            @Override
            public Integer call(Throwable throwable) {
                return 1004;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("Next:" + value);
            }
        });*/

        /*Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                //循环输出数字
                try {
                    for (int i = 0; i < 10; i++) {
                        if (i == 4) {
                            throw new Exception("this is number 4 error！");
                        }
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

        observable.onErrorResumeNext(new Func1<Throwable, Observable<? extends Integer>>() {
            @Override
            public Observable<? extends Integer> call(Throwable throwable) {
                return Observable.just(100,101, 102);
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("Next:" + value);
            }
        });*/

        /*Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                //循环输出数字
                try {
                    for (int i = 0; i < 10; i++) {
                        if (i == 4) {
                            throw new Exception("this is number 4 error！");
                        }
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });

        observable.onExceptionResumeNext(Observable.just(100, 101, 102)).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("Next:" + value);
            }
        });*/


        /*Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                //循环输出数字
                try {
                    for (int i = 0; i < 10; i++) {
                        if (i == 4) {
                            throw new Exception("this is number 4 error！");
                        }
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });

        observable.retry(2).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("Next:" + value);
            }
        });*/

        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("subscribing");
                subscriber.onError(new RuntimeException("always fails"));
            }
        });

        observable.retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Throwable> observable) {
                return observable.zipWith(Observable.range(1, 3), new Func2<Throwable, Integer, Integer>() {
                    @Override
                    public Integer call(Throwable throwable, Integer integer) {
                        return integer;
                    }
                }).flatMap(new Func1<Integer, Observable<?>>() {
                    @Override
                    public Observable<?> call(Integer integer) {
                        System.out.println("delay retry by " + integer + " second(s)");
                        //每一秒中执行一次
                        return Observable.timer(integer, TimeUnit.SECONDS);
                    }
                });
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("Next:" + value);
            }
        });
    }
}
