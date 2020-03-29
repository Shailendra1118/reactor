package keepitbusy;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class MongoTesting {

    private ReactiveMongoTemplate mongoTemplate;

    @Before
    public void setUp(){
        this.mongoTemplate = new ReactiveMongoTemplate(MongoClients.create("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false"),
                "testDB");
    }

    @Test
    public void testFind(){
        System.out.println("##### IN TEST FIND.....");
        Mono<List<TemplateSegments>> res =  mongoTemplate.find(Query.query(Criteria.where("templateId").is("t9")),
                TemplateSegments.class).collectList();

        StepVerifier
                .create(res)
                .consumeNextWith(templateSegments -> {
                    System.out.println("LIST: "+templateSegments.size());
                    //System.out.println(templateSegments.get(0).getSegmentIds());
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void testRemoveItemToArray(){
        System.out.println("##### IN TEST FIND.....");
        //Segment s1  =  new Segment();
        //s1.setId("s97");
        //Segment s2  =  new Segment();
        //s2.setId("s98");
        List<String> toRem =  Arrays.asList("s11", "s33");
        Object[] remove = new Object[]{"s11", "s33"};
        //Map<String, String> m1 = Collections.singletonMap("segmentIds.$", "s22");
        //Map<String, String> m2 = Collections.singletonMap("segmentIds.$", "s44");

        Mono<TemplateSegments> templateSegmentMono = mongoTemplate.update(TemplateSegments.class)
                .inCollection("template_segments")
                .matching(Query.query(Criteria.where("templateId").is("t1")))
                .apply(new Update().pullAll("segments.id", toRem.toArray())
                        .set("updatedDate", Instant.now()))
                .findAndModify().cast(TemplateSegments.class);

       // mongoTemplate.findAndModify(Query.query(Criteria.where("templateId").is("t2")))


        StepVerifier
                .create(templateSegmentMono)
                .consumeNextWith(templateSegment-> {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        String str  = mapper.writeValueAsString(templateSegment);
                        System.out.println("STR: "+str);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    System.out.println(templateSegment);
                })
                .expectComplete()
                .log()
                .verify();
    }

    @Test
    public void testAddItemToArray(){
        System.out.println("##### IN TEST FIND.....");
        List<String> toAdd = Arrays.asList("s33", "s44" ,"s22", "s11");
        Mono<UpdateResult> res = mongoTemplate.update(TemplateSegments.class)
                .inCollection("template_segments")
                .matching(Query.query(Criteria.where("templateId").is("t1")))
                .apply(new Update().addToSet("segmentIds").each(toAdd.toArray())
                        .set("updatedDate", Instant.now())
                .setOnInsert("createdDate", Instant.now()))
                //.setOnInsert("updatedDate", Instant.now()))
                .upsert();


        StepVerifier
                .create(res)
                .consumeNextWith(updateResult ->  {
                    System.out.println(updateResult.getMatchedCount());
                })
                .expectComplete()
                .verify();
    }


    @Test
    public void testUpdateItemToArray(){
        List<String> remove = Arrays.asList("s11", "s33");
        Mono<UpdateResult> res = mongoTemplate.update(TemplateSegments.class)
                .inCollection("template_segments")
                .matching(Query.query(Criteria.where("templateId").is("t1")))
                .apply(new Update().pullAll("segmentIds", remove.toArray())
                        .set("updatedDate", Instant.now())
                        .setOnInsert("createdDate", Instant.now()))
                .first();
                        //.setOnInsert("updatedDate", Instant.now()))
                //.setOnInsert("updatedDate", Instant.now())
        StepVerifier
                .create(res)
                .consumeNextWith(updateResult ->  {
                    System.out.println(updateResult.toString());
                })
                .expectComplete()
                .verify();
    }


    @Test
    public void testAnotherAddItemToArray(){
        System.out.println("##### IN TEST FIND.....");
        List<String> toAdd = Arrays.asList("s11","s22", "s44");
        //String[] toAdd = {"s96", "s99"};
        Mono<TemplateSegments> templateSegmentMono = mongoTemplate.findAndModify(Query.query(Criteria.where("templateId")
                        .is("t3")),
                new Update().pull("segmentIds",toAdd)
                        .set("updatedDate", Instant.now())
                        .setOnInsert("createdDate", Instant.now()),
                        //.setOnInsert("updatedDate", Instant.now()),
                //FindAndModifyOptions.options().upsert(true).returnNew(true),
                TemplateSegments.class);

        StepVerifier
                .create(templateSegmentMono)
                .consumeNextWith(templateSegment ->  {
                    System.out.println(templateSegment.toString());
                })
                .expectComplete()
                .verify();
    }
}
