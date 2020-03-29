package com.example.demo;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.function.Supplier;

public class FunctionFun {

    @Test
    public void testCollector1(){
        Flux.just("Shailendra","Singh","Yadav")
                .collectList()
                .map(list -> {
                    list.forEach(s -> System.out.println(s.length()));
                    return list;
                }).subscribe(s-> System.out.println("OK"), f -> System.out.println("FAIL"));
    }

    @Test
    public void testCollector2(){
        Flux.just("Shailendra","Singh","Yadav")
                .map(listItem -> {
                    System.out.println(listItem.length());
                    return listItem;
                }).subscribe(s-> System.out.println("OK"), f -> System.out.println("FAIL"));
    }

    @Test
    public void testCollector3(){
        Flux.just("Shailendra","Singh","Yadav")
                .collect(XSupplier::new, (generator, item) -> {
                    System.out.println("Collector...");
                    System.out.println(item+" "+generator.get());
                })
                .map(sb -> {
                    System.out.println(sb.toString());
                    return sb;
                }).log()
                .subscribe(s-> System.out.println("OK"), f -> System.out.println("FAIL"));
    }

    class XSupplier implements Supplier<Integer>{

        public XSupplier(){
            System.out.println("XSupplier constructor");
        }
        @Override
        public Integer get() {
            return new Random().nextInt();
        }
    }


    @Test
    public void testThen(){
        Mono.just("Shailendra")
                .flatMap(s -> {
                    throw new RuntimeException("First");
                })
                .then(Mono.just("Singh"))
                .onErrorResume(e -> {
                    System.out.println("Error on resume...");
                    return Mono.error(e);
                }).subscribe(s -> System.out.println("OK"), f-> System.out.println("FAIL"));
    }
}
