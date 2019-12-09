package com.szeng.graphqlDemo.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.szeng.graphqlDemo.entity.Dog;
import com.szeng.graphqlDemo.exception.BreedNotFoundException;
import com.szeng.graphqlDemo.exception.DogNotFoundException;
import com.szeng.graphqlDemo.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class Mutation implements GraphQLMutationResolver {
    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Dog updateDogName(String newName, Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if (optionalDog.isPresent()) {
            Dog dog = optionalDog.get();
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog Not Found ", id);
        }
    }

    public boolean deleteDogBreed(String breed) {
        boolean isDeleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();
        for(Dog dog:allDogs) {
            if (dog.getBreed().equals(breed)) {
                dogRepository.delete(dog);
                isDeleted = true;
            }
        }

        if (!isDeleted) {
            throw new BreedNotFoundException("Cannot find breed ", breed);
        }

        return isDeleted;
    }

}
