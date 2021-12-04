package com.iamatum.reactive;

import com.iamatum.reactive.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository{

    Person john = new Person(1,"John","Thompson");
    Person keanu = new Person(2,"Keanu","Reaves");
    Person henry = new Person(3,"Henry","James");
    Person peter = new Person(4,"Peter","Rabbit");

    @Override
    public Mono<Person> getById(Integer id) {
        return Mono.just(john);
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.just(john,keanu,henry,peter);
    }
}
