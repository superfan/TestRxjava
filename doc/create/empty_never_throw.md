##Empty

![](http://reactivex.io/documentation/operators/images/empty.c.png)

create an Observable that emits no items but terminates normally


##Never

![](http://reactivex.io/documentation/operators/images/never.c.png)

create an Observable that emits no items and does not terminate


##Throw

![](http://reactivex.io/documentation/operators/images/throw.c.png)

create an Observable that emits no items and terminates with an error

* The Empty, Never, and Throw operators generate Observables with very specific and limited behavior. These are useful for testing purposes, and sometimes also for combining with other Observables or as parameters to operators that expect other Observables as parameters.
