package com.salah.springrestdocsheadfirst;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWireMock(port = 8081)
public class WireMockMappingsTest {

    @Test
    public void should_return_hello_world(){
        String object = new RestTemplate().getForObject("http://localhost:8081/bla", String.class);
        BDDAssertions.then(object).isEqualTo("Hello world");

    }

}
