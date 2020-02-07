package com.example.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Service {

    Mono<String> getValue();

    public Flux<Integer> getValues();
}
