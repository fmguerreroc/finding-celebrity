package com.test.celebrityfinder.processing;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.test.celebrityfinder.abstraction.Processor;
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
      Mockito.doReturn(getTestDataForJustKnowMySelf()).when(jpaPersonRepository).findAll();
      Optional<Person> celebrity = byBackEndProcessor.process();
      Assert.assertEquals(CELEBRITY_ID, celebrity.get().getId());
   }

   @Test
   public void successProcessNotKnowAnybodyButEveryBodyKnowsMeTest(){
      Mockito.doReturn(getTestDataForNotKnowAnybodyButEverybodyKnowsMe()).when(jpaPersonRepository).findAll();
      Optional<Person> celebrity = byBackEndProcessor.process();
      Assert.assertEquals(CELEBRITY_ID, celebrity.get().getId());
   }

   @Test
   public void successProcessNoOneKnowsAnybody(){
      Mockito.doReturn(getTestDataForNoOneKnowsAnybody()).when(jpaPersonRepository).findAll();
      Optional<Person> celebrity = byBackEndProcessor.process();
      Assert.assertFalse(celebrity.isPresent());
   }

   @Test
   public void successProcessEveryOneKnowsEverybody(){
      Mockito.doReturn(getTestDataForEveryOneKnowsEverybody()).when(jpaPersonRepository).findAll();
      Optional<Person> celebrity = byBackEndProcessor.process();
      Assert.assertFalse(celebrity.isPresent());
   }

   private List<Person> getTestDataForJustKnowMySelf(){
      final Person.PersonBuilder p1 = Person.builder();
      p1.id(BigInteger.ZERO);
      final Person.PersonBuilder p2 = Person.builder();
      p2.id(BigInteger.ONE);
      final Person.PersonBuilder p3 = Person.builder();
      p3.id(BigInteger.TEN);
      p1.knowns(new HashSet<>(Arrays.asList(p2.build(), p3.build())));
      p2.knowns(new HashSet<>(Arrays.asList(p1.build(), p3.build())));
      p3.knowns(new HashSet<>(Collections.singletonList(p3.build())));
      return Arrays.asList(
            p1.build(),
            p2.build(),
            p3.build()
      );
   }

   private List<Person> getTestDataForNotKnowAnybodyButEverybodyKnowsMe(){
      final Person.PersonBuilder p1 = Person.builder();
      p1.id(BigInteger.ZERO);
      final Person.PersonBuilder p2 = Person.builder();
      p2.id(BigInteger.ONE);
      final Person.PersonBuilder p3 = Person.builder();
      p3.id(BigInteger.TEN);
      p1.knowns(new HashSet<>(Arrays.asList(p2.build(), p3.build())));
      p2.knowns(new HashSet<>(Arrays.asList(p1.build(), p3.build())));
      p3.knowns(new HashSet<>());
      return Arrays.asList(
            p1.build(),
            p2.build(),
            p3.build()
      );
   }

   private List<Person> getTestDataForNoOneKnowsAnybody(){
      final Person.PersonBuilder p1 = Person.builder();
      p1.id(BigInteger.ZERO);
      final Person.PersonBuilder p2 = Person.builder();
      p2.id(BigInteger.ONE);
      final Person.PersonBuilder p3 = Person.builder();
      p3.id(BigInteger.TEN);
      p1.knowns(new HashSet<>());
      p2.knowns(new HashSet<>());
      p3.knowns(new HashSet<>());
      return Arrays.asList(
            p1.build(),
            p2.build(),
            p3.build()
      );
   }

   private List<Person> getTestDataForEveryOneKnowsEverybody(){
      final Person.PersonBuilder p1 = Person.builder();
      p1.id(BigInteger.ZERO);
      final Person.PersonBuilder p2 = Person.builder();
      p2.id(BigInteger.ONE);
      final Person.PersonBuilder p3 = Person.builder();
      p3.id(BigInteger.TEN);
      p1.knowns(new HashSet<>(Arrays.asList(p2.build(), p3.build())));
      p2.knowns(new HashSet<>(Arrays.asList(p1.build(), p3.build())));
      p3.knowns(new HashSet<>(Arrays.asList(p1.build(), p2.build())));
      return Arrays.asList(
            p1.build(),
            p2.build(),
            p3.build()
      );
   }

}