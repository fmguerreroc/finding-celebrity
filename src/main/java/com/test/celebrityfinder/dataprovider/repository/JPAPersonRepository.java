package com.test.celebrityfinder.dataprovider.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.test.celebrityfinder.dataprovider.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAPersonRepository extends JpaRepository<Person, BigInteger> {

   @Query("SELECT person "
         + "FROM Person person "
         + "WHERE "
         + "(person.knowns IS EMPTY "
         + "OR (person MEMBER OF person.knowns "
         + "AND SIZE(person.knowns) = 1)) "
         + "AND (SELECT COUNT (p) "
         + "FROM Person p "
         + "WHERE person MEMBER OF p.knowns "
         + "AND p.id <> person.id) >= "
         + "(SELECT COUNT (p3) "
         + "FROM Person p3 "
         + "WHERE p3.id <> person.id)")
   Optional<Person> findCelebrityByDatabaseProcessing();

}
