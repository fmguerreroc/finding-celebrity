package com.test.celebrityfinder.service;

import java.math.BigInteger;
import java.util.Optional;

import com.test.celebrityfinder.abstraction.CelebrityFinder;
import com.test.celebrityfinder.abstraction.Processor;
import com.test.celebrityfinder.dataprovider.entity.Person;
import com.test.celebrityfinder.exception.CelebritySearchDatabaseException;
import com.test.celebrityfinder.exception.CelebritySearchException;
import com.test.celebrityfinder.exception.CelebritySearchRuntimeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;

@RunWith(MockitoJUnitRunner.class)
public class TeamCelebrityProcessorServiceTest {

   private static final BigInteger EXPECTED_ID = BigInteger.TEN;

   @Mock
   private Processor<Optional<Person>> celebrityProcessor;

   private CelebrityFinder teamCelebrityProcessorService;

   @Before
   public void setup(){
      teamCelebrityProcessorService = new TeamCelebrityProcessorService(celebrityProcessor);
   }

   @Test
   public void successObtainCelebrityTest() throws CelebritySearchException {
      Mockito.doReturn(Optional.of(Person.builder()
            .id(EXPECTED_ID).build())).when(celebrityProcessor).process();
      final Person celebrity = teamCelebrityProcessorService.obtainCelebrity();
      Assert.assertEquals(EXPECTED_ID, celebrity.getId());
      Mockito.verify(celebrityProcessor).process();
   }

   @Test(expected = CelebritySearchDatabaseException.class)
   public void celebritySearchDatabaseExceptionOnProcessTest() throws CelebritySearchException {
      Mockito.doThrow(new DataAccessException(""){}).when(celebrityProcessor).process();
      try {
         teamCelebrityProcessorService.obtainCelebrity();
      } finally {
         Mockito.verify(celebrityProcessor).process();
      }
   }

   @Test(expected = CelebritySearchRuntimeException.class)
   public void celebritySearchRuntimeExceptionOnProcessTest() throws CelebritySearchException {
      Mockito.doThrow(new RuntimeException()).when(celebrityProcessor).process();
      try {
         teamCelebrityProcessorService.obtainCelebrity();
      } finally {
         Mockito.verify(celebrityProcessor).process();
      }
   }
}