package com.example.demo;

import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class MonoFlux {

    @Test
    public void flatMapWithoutChangingScheduler() throws InterruptedException {
        System.out.println("START...");
        Flux.range(1, 3)
                .map(n -> identityWithThreadLogging(n, "map1"))
                .flatMap(n -> Mono.just(n)
                        .map(nn -> identityWithThreadLogging(nn, "mono")))
                .subscribeOn(Schedulers.parallel())
                .subscribe(a -> {
                    this.identityWithThreadLogging(a, "subscribe");
                    System.out.println(a);
                }, throwable -> System.out.println(throwable));

        Thread.sleep(3000);
    }

    @Test
    public void flatMapWithChangingScheduler() throws InterruptedException {
        System.out.println("START...");
        Flux.range(1, 3)
                .map(n -> identityWithThreadLogging(n, "map1"))
                .flatMap(n -> Mono.just(n)
                        .map(nn -> identityWithThreadLogging(nn, "mono")).subscribeOn(Schedulers.elastic()))
                .subscribeOn(Schedulers.parallel())
                .subscribe(a -> {
                    this.identityWithThreadLogging(a, "subscribe");
                    System.out.println(a);
                }, throwable -> System.out.println(throwable));

        Thread.sleep(3000);
    }

    private <T> T identityWithThreadLogging(T el, String operation) {
        System.out.println(operation + " -- " + el + " -- " +
                Thread.currentThread().getName());
        return el;
    }



    @Test
    public void complexCase() {
        Flux.range(1, 4).
                subscribeOn(Schedulers.immediate()).
                map(n -> identityWithThreadLogging(n, "map1")).
                flatMap(n -> {
                    if (n == 1) return createMonoOnScheduler(n, Schedulers.parallel());
                    if (n == 2) return createMonoOnScheduler(n, Schedulers.elastic());
                    if (n == 3) return createMonoOnScheduler(n, Schedulers.single());
                    return Mono.error(new Exception("error")).subscribeOn(Schedulers.newSingle("error-thread"));
                }).
                map(n -> identityWithThreadLogging(n, "map2")).
                subscribe(
                        success -> System.out.println(identityWithThreadLogging(success, "subscribe")),
                        error -> System.err.println(identityWithThreadLogging(error, "subscribe, err").getMessage())
                );
    }

    private Publisher<?> createMonoOnScheduler(Integer n, Scheduler single) {
        return null;
    }

}
