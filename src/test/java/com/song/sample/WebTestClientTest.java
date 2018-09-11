package com.song.sample;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebTestClientTest {


    //Spring5에 추가된 RestClient, Async 함
    //https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/
    @Autowired
    WebTestClient webTestClient;


    @Test
    public void hello2() throws Exception {

        webTestClient.get().uri("/hello").exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("HelloJM");
    }

}
