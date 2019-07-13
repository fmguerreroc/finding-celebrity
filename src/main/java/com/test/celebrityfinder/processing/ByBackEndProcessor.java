package com.test.celebrityfinder.processing;

import java.util.List;
import java.util.Optional;

import com.test.celebrityfinder.abstraction.Processor;
import com.test.celebrityfinder.dataprovider.entity.Person;
import com.test.celebrityfinder.dataprovider.repository.JPAPersonRepository;
import com.test.celebrityfinder.utils.CelebrityValidationUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ByBackEndProcessor implements Processor<Optional<Person>> {

   private final JPAPersonRepository jpaPersonRepository;

   @Override
   public Optional<Person> process() {
      List<Person> teamMembers = jpaPersonRepository.findAll();
      return teamMembers.stream()
            .filter(CelebrityValidationUtils.DOES_KNOW_ANYBODY)
            .filter(celebrityCandidate ->
                  CelebrityValidationUtils.everyBodyKnowsHim(celebrityCandidate, teamMembers))
            .findFirst();
   }
}
