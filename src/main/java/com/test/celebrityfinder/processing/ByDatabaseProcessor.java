package com.test.celebrityfinder.processing;

import java.util.Optional;

import com.test.celebrityfinder.abstraction.Processor;
import com.test.celebrityfinder.dataprovider.entity.Person;
import com.test.celebrityfinder.dataprovider.repository.JPAPersonRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ByDatabaseProcessor implements Processor<Optional<Person>> {

   private final JPAPersonRepository jpaPersonRepository;

   @Override
   public Optional<Person> process() {
      return jpaPersonRepository.findCelebrityByDatabaseProcessing();
   }
}
