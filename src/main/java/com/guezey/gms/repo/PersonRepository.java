package com.guezey.gms.repo;

import com.guezey.gms.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query(
            value = "SELECT * FROM person ORDER BY id DESC LIMIT 1",
            nativeQuery = true
    )
    Person findLast();
}
