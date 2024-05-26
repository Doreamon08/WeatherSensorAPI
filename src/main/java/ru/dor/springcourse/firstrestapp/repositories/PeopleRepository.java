package ru.dor.springcourse.firstrestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dor.springcourse.firstrestapp.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
