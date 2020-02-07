package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import reactor.util.context.Context;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/test")
public class ApiController {

    @Autowired
    Service service;

    @GetMapping("/{invoiceUuid}")
    public Flux<Integer> retrieveInvoicePdf(
            @PathVariable("invoiceUuid") String invoiceUuid) {
        String apiId = invoiceUuid == null ? "" : invoiceUuid;
        log.info("Invoice PDF requested for invoice id: {}", invoiceUuid);
        return Flux.just("Finding data for invoice: "+invoiceUuid)
                .doOnEach(logOnNext(msg -> {
                    //log.info("LogOnNExt: "+msg);
                })).thenMany(service.getValues())
                .onErrorResume(e -> {
                    log.error("On Error Controller");
                    return Mono.error(e);
                })
                .retry(2)
                .doOnEach(logOnNext(r -> {
                    //log.info("LogOnNEXT service: " + r);
                })).subscriberContext(Context.of("apiID", apiId));


                   // return Mono.just(new ResponseEntity<String>("This is Demo application",HttpStatus.OK));
    }


    private static <T> Consumer<Signal<T>> logOnNext(Consumer<T> logStatement) {
        return signal -> {
            //log.info("LogOnNExt method...");
            if (!signal.isOnNext()) return;
            Optional<String> apiIDMaybe = signal.getContext().getOrEmpty("apiID");

            apiIDMaybe.<Runnable>map(apiID -> () -> {
                try (MDC.MDCCloseable closeable = MDC.putCloseable("apiID", apiID)) {
                    //log.info("Accepting...");
                    logStatement.accept(signal.get());
                }
            }).orElse(() -> logStatement.accept(signal.get())).run();
        };
    }
}
