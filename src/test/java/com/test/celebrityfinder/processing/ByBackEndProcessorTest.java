package com.test.celebrityfinder.processing;

import java.math.BigInteger;
import java.util.Optional;

import com.test.celebrityfinder.abstraction.Processor;
import com.test.celebrityfinder.datapreparing.DataPreparationUtil;
import com.test.celebrityfinder.dataprovider.entity.Person;
import com.test.celebrityfinder.dataprovider.repository.JPAPersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ByBackEndProcessorTest {

   private static final BigInteger CELEBRITY_ID = BigInteger.TEN;

   @Mock
   private JPAPersonRepository jpaPersonRepository;

   private Processor<Optional<Person>> byBackEndProcessor;

   @Before
   public void setup(){
      byBackEndProcessor = new ByBackEndProcessor(jpaPersonRepository);
   }

   @Test
   public void successProcessJustKnowMySelfButEveryBodyKnowsMeTest(){
      Mockito.doReturn(DataPreparationUtil.getTestDataForJustKnowMySelfButEverybodyKnowksMe()).when(jpaPersonRepository).findAll();
      Optional<Person> celebrity = byBackEndProcessor.process();
      Assert.assertEquals(CELEBRITY_ID, celebrity.get().getId());
   }

   @Test
   public void successProcessNotKnowAnybodyButEveryBodyKnowsMeTest(){
      Mockito.doReturn(DataPreparationUtil.getTestDataForNotKnowAnybodyButEverybodyKnowsMe())
            .when(jpaPersonRepository).findAll();
      Optional<Person> celebrity = byBackEndProcessor.process();
      Assert.assertEquals(CELEBRITY_ID, celebrity.get().getId());
   }

   @Test
   public void successProcessNoOneKnowsAnybody(){
      Mockito.doReturn(DataPreparationUtil.getTestDataForNoOneKnowsAnybody()).when(jpaPersonRepository).findAll();
      Optional<Person> celebrity = byBackEndProcessor.process();
      Assert.assertFalse(celebrity.isPresent());
   }

   @Test
   public void successProcessEveryOneKnowsEverybody(){
      Mockito.doReturn(DataPreparationUtil.getTestDataForEveryOneKnowsEverybody()).when(jpaPersonRepository).findAll();
      Optional<Person> celebrity = byBackEndProcessor.process();
      Assert.assertFalse(celebrity.isPresent());
   }

}