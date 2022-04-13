package com.salah.springrestdocsheadfirst;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

@RestController
public class FooController {
    @GetMapping("/foo")
    public String foo() {
        return "boo";
    }

    @PostMapping("/person")
    public Person person(@RequestBody PersonRequest personRequest){
        return new Person(personRequest.getName(), "bla");
    }


    @PostMapping("/person/{id}")
    public String name(@PathVariable String id){
        return "salah";
    }

}

@NoArgsConstructor
@Setter
@Getter
class Person {
    String name;
    String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}

@NoArgsConstructor
@Setter
@Getter
class PersonRequest {
    String name;
}