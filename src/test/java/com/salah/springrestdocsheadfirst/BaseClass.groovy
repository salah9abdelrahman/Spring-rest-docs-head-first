package com.salah.springrestdocsheadfirst

import io.restassured.module.mockmvc.RestAssuredMockMvc
import spock.lang.Specification


class BaseClass extends Specification {
    void setup() {
        RestAssuredMockMvc.standaloneSetup(new FooController())
    }
}