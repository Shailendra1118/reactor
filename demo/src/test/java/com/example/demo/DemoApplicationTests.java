package com.example.demo;

import net.minidev.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxExtensionsKt;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import javax.crypto.spec.PSource;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void errorTest(){
		Mono<Integer> monoInt = Mono.just(100).map(v->{
			System.out.println("Received: "+v);
			return 100/v;
		}).onErrorReturn(2000);

		monoInt.subscribe(s-> System.out.println("Success: "+s), e-> System.out.println("Failure: "+e));
	}

	@Test
	public void contextTest(){
	}

	@Test
	public void subscribeTest(){

	}

	private Mono<String> getHtmlData(String json){
		return Mono.just("HtmlData");
	}

	private Mono<String> getPdfData(String html){
		return Mono.just("PdfData");
	}

	private String secondString(String input){
		if(input.equals("second"))
			return "";
		else
			return "one";
	}


	@Test
	public void testRetry(){
		Flux.just(1,2,0,4)
				.map(integer -> {
					int res = 100/integer;
					System.out.println("Got Res: "+res);
					return res;
				}).retry(1, this::retryOnArithmeticException)
				.doOnError(e -> System.out.println("Got error "+e))
				.subscribe(s-> System.out.println("Success with "+s), e-> System.out.println("Error with "+e));
	}


	// private internal method
	private boolean retryOnArithmeticException(Throwable throwable) {
		System.out.println("Checking retry on Exception: "+throwable.getClass().getName());
		Throwable causeBy = throwable.getCause();
		if (causeBy != null) {
			System.out.println("Exception caused By: "+causeBy.getClass().getName());
		}
		return throwable instanceof ArithmeticException;
	}


	@Test
	public void test2(){
		String first = "Shailendra";
		String second = first.concat(getAnother());
		System.out.println(second.length());

		if("PAID".equals(TestEnum.PAID.value)){
			System.out.println("Its Equal");
		}else{
			System.out.println("Not Equal");
		}

		Map<TestEnum, String> map = new HashMap<>();
		map.put(TestEnum.VOIDED, "void invoice");

		if("void invoice".equals(map.get(TestEnum.valueOf("VOIDED")))){
			System.out.println("FOUND!!");
		}else{
			System.out.println("NOT FOUND.");
		}

	}

	private String getAnother(){
		return "";
	}


	enum TestEnum {
		PAID("PAID"),
		VOIDED("VOIDED");
		String value;
		TestEnum(String value){
			this.value = value;
		}
	}



	@Test
	public void testDelays() throws InterruptedException {
		System.out.println("Time: "+ Instant.now());
		Flux.just(10,20,30)
				.delayElements(Duration.ofSeconds(2))
				.flatMap(e -> {
					System.out.println(e);
					return Mono.just(e);
				})
				.subscribe(s-> System.out.println("Success: "+Instant.now()), e-> System.out.println("Error"));

		Thread.sleep(10000);
		System.out.println("Method finished.");
	}


	@Test
	public void testDelayedSubscription() throws InterruptedException {
		System.out.println("Time: "+ Instant.now());
		Flux.just(1,2,3,4)
				.flatMap(i -> {
					System.out.println("Got: "+i);
					return Mono.just(i);
				})
				.delaySubscription(Duration.ofSeconds(5))
				.subscribe(s-> System.out.println("Success"), e-> System.out.println("Error"));

		Thread.sleep(10000);
		System.out.println("Completed: "+ Instant.now());
	}


	@Test
	public void testPublishOn(){

		Flux.range(0, 4)
				.map(i -> indexToName(i))
				.publishOn(Schedulers.elastic())
				.subscribe(s -> System.out.println("Received "+s+" on thread: "+Thread.currentThread().getName()),
						e -> System.out.println("Error"));
	}

	private String indexToName(int i){
		String arr[] = {"Hawai", "NewYork", "Mumbai"};
		System.out.println("Returning "+arr[i]+" on thread: "+Thread.currentThread().getName());
		return arr[i];
	}

	@Test
	public void testSubscribeOn(){
		Flux.range(0, 3)
				.map(i -> indexToName(i))
				.subscribeOn(Schedulers.elastic())
				.map(s -> {
					System.out.println(s+": on"+Thread.currentThread().getName());
					return s;
				})
				.publishOn(Schedulers.parallel())
				.subscribe(s -> System.out.println("Received "+s+ " on thread "+Thread.currentThread().getName()));
	}

	@Test
	public void testErrorOnReturn(){
		Flux.just(1,-2,3)
				.map( i -> mapToString(i))
				.doOnError(e -> System.out.println("Error xxx"))
				.onErrorReturn(getAlternateValue())
				.onErrorResume(e -> {
					System.out.println("OnErrorResume -->");
					return Mono.error(e);
				}).subscribe(s-> System.out.println("Success with "+s), e-> System.out.println("Error with "+e));

	}

	@Test
	public void testThen(){
		Flux.just(123, 212, 23)
				.map(i -> mapToString(i))
				.log()
				.doOnNext(v -> System.out.println("doOnNext with: "+v))
				.log()
				.onErrorResume(e -> {
					System.out.println("ERROR resume...");
					return Mono.error(e);
				})
				.log()
				.then(callThen("SomeValue")) // then replays
				.log()
				.subscribe(s-> System.out.println("success"), e-> System.out.println("error"));
	}

	private Mono<String> callThen(String value) {
		System.out.println("Calling Then with "+value);
		int j = 0;
		for(int i=0; i<50; i++){
			j++;
			//System.out.println("counting");
		}
		System.out.println("done counting....");
		return getMeAnotherMono();
	}

	private Mono<String> getMeAnotherMono() {
		System.out.println("Returning anotherMono");
		return Mono.just("AnotherMono").flatMap(v -> {
			System.out.println("received another mOno..");
			return Mono.just(v).doOnSuccess(e-> System.out.println("Yey!"));
		});
	}

	private String mapToString(int i){
		System.out.println("mapToString...");
		if(i > 0){
			return "positive";
		}else{
			throw new RuntimeException("Error");
		}
	}

	private String appendSome(String i){
		System.out.println("appendSome...");
		return i+" Some";
	}

	private String getAlternateValue(){
		return "AlternateValue";
	}

	@Test
	public void TestSwitch(){
		Mono.empty()
				//.switchIfEmpty(Mono.just("SomethingElse"))
				.flatMap(e -> {
					System.out.println("E is here: "+e);
					return Mono.just(e);
				}).subscribe();
	}

	@Test
	public void testSwitchIf() throws InterruptedException {
		someOps()
				.switchIfEmpty(Mono.just("Hello121212"))
				.flatMap(s -> {
					System.out.println("Got afterLogIt: "+s);
					return Mono.just(s);
				}).subscribe(s-> System.out.println("OK"+s), e-> System.out.println("ERR"+e));

		Thread.sleep(3000);
	}

	private Mono<String> logIt(){
		for (int i = 0; i < 5; i++) {
			System.out.println("Hello: "+i);
		}
		return Mono.just("Shailendra");
	}

	private Flux<String> someOps(){
		return Flux.interval(Duration.ofMillis(500))
				.take(2).flatMap(s -> {
					System.out.println("Got: "+s.longValue());
					long i = s.longValue();
					if(s.equals(Long.valueOf(1))) {
						System.out.println("INside");
						return Mono.empty();
					}
					else
						return Mono.just(s+": :");
				});
	}

	@Test
	public void testDate(){
		Instant date = Instant.now();
		LocalDateTime date1 =LocalDateTime.ofInstant(date, ZoneOffset.UTC);
		String formatted = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date1);
		System.out.println(formatted);
		System.out.println(DateTimeFormatter.ISO_INSTANT.format(date));

		long a = 1;
		long b = 1;
		System.out.println(a==b);


	}

	@Test
	public void testBin(){
		String s = Integer.toBinaryString(5);
		System.out.println(s);

		String bin = Integer.toBinaryString(13);
		System.out.println(bin);
		int max = 0;
		boolean started = false;
		int count = 0;

		for(int i=0; i< bin.length(); i++){
			if(bin.charAt(i) == '1'){
				if(!started){
					started = true;
					count = 0;
				}else{
					count++;
					max = count > max ? count : max;
					count = 0;
				}
			}else{
				count++;
			}
		}
		System.out.println("max: "+max+" count: "+count);
	}

	@Test
	public void testPredicate(){
		BiPredicate<String, String> predicate = (f,s)-> f.equals("Shailendra") && "Singh".equals(s);
		Map<String, String> map = new HashMap<>();
		String[] arr = {"One", "Two", "Three"};

		Flux.just(Position.values()).collectMap(s -> s.val, v -> {
			System.out.println("Getting value..");
			return WatermarkUtil.getText("status");
		}).subscribe(s-> System.out.println(s), f-> System.out.println(f));


	}


	enum Position {

		FIRST("first"),
		SEC("second");
		private String val;

		Position(String val) {
			this.val = val;
		}
		public String value(){
			return val;
		}
	}

	static class WatermarkUtil{
		public static Mono<Integer> getText(String status){
			System.out.println("Got status: "+status);
			return Mono.just(new Random().ints().findAny().getAsInt());
		}
	}


	@Test
	public void test12(){
		Flux.just(1,2)
				.flatMap(s -> {
					System.out.println("Got first: "+s);
					return Mono.just(s);
				})
				.onErrorResume(e -> {
					System.out.println("err: "+e);
					return Mono.error(e);
				}).flatMap(s -> {
					System.out.println("Last: "+s);
					return Mono.just(s);
				}).subscribe(s-> System.out.println(s), e-> System.out.println(e));

	}

	@Test
	public void testFilter(){
		Flux.just(1,3)
				//.doOnNext(s -> System.out.println(s))
				//.filter(s -> s< 10)
				.flatMap(s -> {
					System.out.println("Got s:"+s);
					return Mono.just(s);
				})
				.doOnComplete(() -> System.out.println("completed"))
				.subscribe(s -> System.out.println("OK "+s), e-> System.out.println("ERR "+e));
	}


	@Test
	public void testMonoDefer(){
		Mono.just("Shailendra")
				.flatMap(s -> {
					if(s.equals("Shailendra")){
						return Mono.empty();
					}else
						return Mono.just(s);
				})
				.switchIfEmpty(Mono.just(Utils.getText()))
				.doOnSuccess(s -> System.out.println("SUCC "+s))
				.doOnError(e -> System.out.println("ERR "+e))
				.flatMap(v -> {
					System.out.println("Got "+v);
					return Mono.just(v);
				}).subscribe(s -> System.out.println("OK "+s), e -> System.out.println("ERR "+e));
	}

	//Mono.defer(()->


	static class Utils{
		public static String getText(){
			System.out.println("Returning Logs");
			return "FDLKJLDJKF";
		}
	}

	class Service{
		public String check(String v){
			if(v.equals("Shailendra"))
				return "Singh";
			else
				return "NotFound";
		}
	}

	@Test
	public void testPred(){
		BiPredicate<Service, String> biPredicate = ((service, s) -> {
			if(service.check(s).equals("Singh")){
				System.out.println("Singh returning...");
				return true;
			}
			else {
				System.out.println("Returning...");
				return false;
			}
		});

		Mono.just("Shailendra1")
				.flatMap(s -> {
					System.out.println("Got: "+s);
					if(biPredicate.test(new Service(), s))
						throw new RuntimeException("Error");
					else
						return Mono.just(s);
				}).subscribe(s -> System.out.println("OK: "+s), e -> System.out.println("ERR"));

	}

	@Test
	public void testDummy() {
		Mono<String> dummy = Mono.empty();
		Mono.just(10)
				.flatMap(s -> {
					if (s == 10) {
						return dummy;
					} else
						return Mono.just(s);
				}).flatMap(v -> {
					System.out.println("Flat map: "+v);
					return Mono.just(v);
		}).subscribe(s -> System.out.println("OK"), e -> System.out.println("ERR"));
	}


	@Test
	public void testPostFilter(){
		Flux.just(1,2,3)
				//.filter((s) -> s == 2)
				.map(s -> {
					System.out.println("Got: "+s);
					return s;
				})
				.map(s -> {
					System.out.println("Another map: "+ s);
					return s;
				})
				//.then(Mono.just(10))
				.doOnDiscard(Integer.class, (s)-> System.out.println("Discarded: "+s))
				.doOnComplete(() -> System.out.println("On completed"))
				.doOnError(err -> System.out.println("Do On Error"+err))
				.subscribe(s -> System.out.println("OK "+s), e -> System.out.println("ERR "+e));
	}



	@Test
	public void testErrors(){
		Mono.just("String")
				.map(s -> {
					System.out.println(" in : map: "+s);
					//return s+" mapped";
					throw new RuntimeException("Exp");
				}).onErrorResume(e -> {
					System.out.println("ERROR post map...");
					return Mono.error(e);
				}).flatMap(v -> {
					System.out.println(" in Flat map...");
					//return Mono.just(v);
					throw new RuntimeException("Exp");
				}).onErrorResume(e ->{
					System.out.println("Error post flap");
					return Mono.error(e);
				}).subscribe(s-> System.out.println("OK"), e -> System.out.println("ERR"));
	}

	@Test
	public void basicTest(){
		List<Integer> list = new ArrayList<>();
		String partner = "Appdirect";
		method1(partner);
		System.out.println(partner);
		char arr[] = partner.toCharArray();
		swap(arr, 0, 2);
		System.out.println("pPost swap: "+new String(arr));
	}

	private void method1(String value){
		value = value+"ABB";
	}

	private void swap(char[] arr, int i, int j){
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}


	@Test
	public void testEMpty() {
		Flux.just("One", "Two" , "Three")
				.flatMap(value -> {
					System.out.println("Received: "+ value);
					if(value.equals("Two"))
						return Mono.empty();
					return Mono.just(value);
				}).switchIfEmpty(Mono.defer(() -> {
					System.out.println("In empty");
					//return Mono.just("EMPTY");
					return Mono.empty();
				})).switchIfEmpty(Mono.defer(()-> {
					System.out.println("last switch if empty");
					return Mono.just("EMPTY");
				}))
				.subscribe(s -> System.out.println("Success: "+s), e -> System.out.println("Error: "+e));
	}



}
