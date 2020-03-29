package com.example.demo;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class YouAreReactive {

    @Test
    public void testSwitchPosition(){
        Flux.just("Shailendra", "Yadav", "Singh")
            .flatMap(s -> {
                System.out.println("Got: "+s);
                return methodOne(s)
                        .flatMap(val -> {
                            System.out.println("Inner flatmap...");
                            return Mono.just(val);
                        }).switchIfEmpty(Mono.defer(() -> {
                            System.out.println("Received Empty...");
                            //return Mono.just("EMPTY VALUE!"); ---> got to next flatmap bcoz it has now value
                            return Mono.empty();
                        })).flatMap(v -> {
                            System.out.println("FlatMap after empty...got: "+v);
                            return Mono.just(v);
                        });
            }).switchIfEmpty(Mono.defer(() ->{
                System.out.println("Got empty/ null...");
                return Mono.empty();
            })).flatMap(s -> {
                System.out.println("New value:"+s);
                return Mono.just(s);
            }).subscribe(s-> System.out.println("OK with "+s), f -> System.out.println("FAIL with "+f));
    }

    private Mono<String> methodOne(String s) {
        if(s.equals("Yadav")){
            return Mono.empty();
        }else
            return Mono.just(s);
    }



    @Test
    public void testFilter(){
        Mono.just("Biker")
                .flatMap(val -> {
                    System.out.println("First flatMap...");
                    return method(val)
                            .flatMap(x -> method1(x))
                            .filter(y -> {
                                System.out.println("NEW FILTER...");
                                return y.equals("singh");
                            });
                }).filter(s -> {
                    System.out.println("Outer filter called with "+s);
                    return s.equals("Biker");
                })
                .flatMap(another -> {
                    System.out.println("Post outerFilter...with "+another);
                    return Mono.just(another);
                })
                .doOnDiscard(String.class, (s)->  System.out.println("TestFiler Discarded with "+s))
                .subscribe(s-> System.out.println("sub OK "+s), f-> System.out.println("FAIL"));
    }

    private Mono<String> method1(String x) {
        return Mono.just("singh");
    }

    private Mono<String> method(String val) {
        return Mono.just("Xtream")
                .flatMap(v -> {
                    System.out.println("Got from param and Mono: "+val+" "+v);
                    return Mono.just(v);
                }).filter(v -> {
                    System.out.println("Inner filter with "+("Xtream".equals(v)));
                    return v.equals("Xtream");
                })
                .flatMap(another -> {
                    System.out.println("Post Inner..."+another);
                    return Mono.just(another);
                })
                //.log()
                .doOnDiscard(String.class, (s)->  System.out.println("Method Discarded with "+s))
                .doOnSuccess(s -> System.out.println("method OK "+s));
    }


    @Test
    public void tesstFilter(){
        Mono.just("shailendra")
                .flatMap(s -> {
                    System.out.println("Got "+s);
                    return meth1(s);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    System.out.println("EMPTY");
                    return Mono.just("sshailnedra");
                }))
                .filter(s -> {
                    System.out.println("Got: "+s);
                    return s.equals("sshailnedra");
                })
                .flatMap(v -> {
                    System.out.println("latter");
                    return Mono.just(v);
                }).subscribe(s-> System.out.println("OK"));
    }

    private Mono<String> meth1(String s) {
        System.out.println("Meth1");
        return Mono.empty();
    }

    class TT{
        private int i;
    }


    @Test
    public void testTyp(){
        Flux.just(0,1,2)
                .map(v -> v+"Appender")
                .subscribe(s-> System.out.println("OK: "+s));
    }

    private Function<Integer, Boolean> function = (a) -> {
        System.out.println("Called Function...");
        return a.toString().equals("One");
    };
}
