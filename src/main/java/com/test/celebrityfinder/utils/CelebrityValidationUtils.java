package com.test.celebrityfinder.utils;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import com.test.celebrityfinder.dataprovider.entity.Person;

public final class CelebrityValidationUtils {

   private CelebrityValidationUtils(){}

   public static final Predicate<Person> DOES_KNOW_ANYBODY = person -> person.getKnowns().isEmpty()
         || (person.getKnowns().size() == 1 && person.getKnowns().contains(person));
   private static final BiPredicate<Person, Person> IS_CELEBRITY_CANDIDATE =
         (celebrityCandidate, assertionTarget) ->
               celebrityCandidate.getId().compareTo(assertionTarget.getId()) == 0;

   public static Boolean everyBodyKnowsHim(final Person celebrityCandidate, final List<Person> teamMembers){
      return teamMembers.stream()
            .filter(
                  teamMember ->
                        IS_CELEBRITY_CANDIDATE.negate()
                        .test(celebrityCandidate, teamMember))
            .allMatch(person ->
                  person.getKnowns()
                  .contains(celebrityCandidate));
   }
}
