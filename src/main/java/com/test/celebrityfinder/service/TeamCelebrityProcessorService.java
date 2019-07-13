package com.test.celebrityfinder.service;

import java.util.List;
import java.util.Optional;

import com.test.celebrityfinder.abstraction.CelebrityFinder;
import com.test.celebrityfinder.dataprovider.entity.Person;
import com.test.celebrityfinder.exception.CelebrityNotFoundException;
import com.test.celebrityfinder.exception.CelebritySearchDatabaseException;
import com.test.celebrityfinder.exception.CelebritySearchException;
import com.test.celebrityfinder.exception.CelebritySearchRuntimeException;
import com.test.celebrityfinder.abstraction.Processor;
import com.test.celebrityfinder.utils.CelebrityValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;

@AllArgsConstructor
public class TeamCelebrityProcessorService implements CelebrityFinder {

   private final Processor<Optional<Person>> celebrityProcessor;

   @Override
   public Person obtainCelebrity() throws CelebritySearchException {
      try {
         return celebrityProcessor.process().orElseThrow(CelebrityNotFoundException::new);
      } catch (final DataAccessException e){
         throw new CelebritySearchDatabaseException(e);
      } catch (final RuntimeException e){
         throw new CelebritySearchRuntimeException(e);
      }
   }

}
