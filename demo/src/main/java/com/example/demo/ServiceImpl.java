package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Component
public class ServiceImpl implements Service {

    @Override
    public Mono<String> getValue() {
        log.info("Finding service result...");
        return Mono.just("Default service result")
                .flatMap(s -> {
                    log.info("Mapping...");
                    //return Mono.fromCallable(()-> "Value").delayElement(Duration.ofSeconds(3));
                    return Mono.just("Testing").delayElement(Duration.ofSeconds(2));
                });
    }

    public Flux<Integer> getValues(){
        log.info("Finding service get values...");
        return Flux.just(1,2,0)
                .flatMap(v -> {
                    log.info("Value is: {}", v);
                    if(v <= 0){
                        throw new RuntimeException("Invalid Value found.");
                    }
                    return Mono.just(v);
                }).onErrorResume(e -> {
                    log.error("Error is in SERVICE", e);
                    return Mono.error(e);
                }).retry(2);
    }
}
