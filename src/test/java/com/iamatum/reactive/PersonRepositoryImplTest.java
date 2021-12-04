package com.iamatum.reactive;

import com.iamatum.reactive.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    @Test
    void fluxBlockFirst(){
        Person person = personRepository.findAll().blockFirst();
        System.out.println(person);

    }

    @Test
    void testFluxSubscribe(){
        Flux<Person> fluxPerson = personRepository.findAll();
        fluxPerson.subscribe(person -> System.out.println(person)
                );

    }

    @Test
    void testFluxToMonoList(){
        Flux<Person> fluxPerson = personRepository.findAll();

        Mono<List<Person>> listMono = fluxPerson.collectList();
        listMono.subscribe(list ->
                list.forEach(person -> System.out.println(person))
                );

    }

}