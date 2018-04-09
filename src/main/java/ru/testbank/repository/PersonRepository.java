package ru.testbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.testbank.entity.Person;

/**
 * Created by Admin on 08.04.2018.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
