package keepitbusy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.MDC;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import reactor.util.context.Context;

import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
public class Dribble {

    @Test
    public void testObj(){
        TestClass obj = new TestClass();
        obj.print(null);


    }

    class TestClass {
        public void print(String a){
            System.out.println("A: "+a);
        }
        public void print(Object b) {
            System.out.println("B: "+b);
        }
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

    @Test
    private Mono<Void> test(){
        int invoiceId = 124;
        return Mono.just("Hello")
                .doOnEach(logOnNext(msg -> {
                    log.info("LogOnNExt: "+msg);
                }))
                .onErrorResume(e -> {
                    log.error("On Error Controller");
                    return Mono.error(e);
                })
                .retry(2)
                .doOnEach(logOnNext(r -> {
                    log.info("LogOnNEXT service: " + r);
                })).subscriberContext(Context.of("apiID", invoiceId))
                .then();


    }
}
