package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

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

}
