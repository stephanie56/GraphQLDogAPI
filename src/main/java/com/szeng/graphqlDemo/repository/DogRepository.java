package com.szeng.graphqlDemo.repository;

import com.szeng.graphqlDemo.entity.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends CrudRepository<Dog, Long> {
}
