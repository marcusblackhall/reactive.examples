package com.iamatum.reactive;

import com.iamatum.reactive.domain.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {

    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personRepository = new PersonRepositoryImpl();
    }


    @Test
    void getByIdBlock() {

        Mono<Person> byId = personRepository.getById(2);
        Person block = byId.block();
        System.out.println(block);
    }
    @Test
    void getByIdSubscribe() {

        Mono<Person> byId = personRepository.getById(2);
        byId.subscribe(person -> System.out.println(person));
    }

    @Test
    void getByIdMapSubscribe() {

        Mono<Person> byId = personRepository.getById(2);
        byId
                .map(Person::getFirstName)
                .subscribe(firstName -> System.out.println(firstName));
    }
}