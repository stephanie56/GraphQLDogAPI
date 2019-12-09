package com.szeng.graphqlDemo.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.szeng.graphqlDemo.entity.Dog;
import com.szeng.graphqlDemo.exception.DogNotFoundException;
import com.szeng.graphqlDemo.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    private DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs() {
        return this.dogRepository.findAll();
    }

    public Dog findDogById(Long id) {
        Optional<Dog> optionalDog = this.dogRepository.findById(id);
        if (optionalDog.isPresent()) {
            return optionalDog.get();
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}
