package com.test.celebrityfinder.dataprovider.repository;

import java.util.Optional;

import com.test.celebrityfinder.datapreparing.DataPreparationUtil;
import com.test.celebrityfinder.dataprovider.entity.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class JPAPersonRepositoryTest {

   @Autowired
   private JPAPersonRepository jpaPersonRepository;

   @Before
   public void setup(){
      jpaPersonRepository.saveAll(DataPreparationUtil.getEmptyMembers());
   }

   @Test
   public void getCelebrityByDatabaseProcessingEverybodyKnowsMeButIJustKnowMeTest(){
      jpaPersonRepository.saveAll(DataPreparationUtil.getTestDataForJustKnowMySelfButEverybodyKnowksMe());
      Optional<Person> celebrity = jpaPersonRepository.findCelebrityByDatabaseProcessing();
      Assert.assertEquals(DataPreparationUtil.P3_ID, celebrity.get().getId());
      Assert.assertEquals(DataPreparationUtil.P3_FIRST_NAME, celebrity.get().getFirstName());
      Assert.assertEquals(DataPreparationUtil.P3_LAST_NAME, celebrity.get().getLastName());
   }

   @Test
   public void getCelebrityByDatabaseProcessingEverybodyKnowsIDontKnowAnyoneTest(){
      jpaPersonRepository.saveAll(DataPreparationUtil.getTestDataForNotKnowAnybodyButEverybodyKnowsMe());
      Optional<Person> celebrity = jpaPersonRepository.findCelebrityByDatabaseProcessing();
      Assert.assertEquals(DataPreparationUtil.P3_ID, celebrity.get().getId());
      Assert.assertEquals(DataPreparationUtil.P3_FIRST_NAME, celebrity.get().getFirstName());
      Assert.assertEquals(DataPreparationUtil.P3_LAST_NAME, celebrity.get().getLastName());
   }

   @Test
   public void getCelebrityByDatabaseProcessingEveryOneKnowsEverybodyTest(){
      jpaPersonRepository.saveAll(DataPreparationUtil.getTestDataForEveryOneKnowsEverybody());
      Optional<Person> celebrity = jpaPersonRepository.findCelebrityByDatabaseProcessing();
      Assert.assertFalse(celebrity.isPresent());
   }

   @Test
   public void getCelebrityByDatabaseProcessingNoOneKnowsAnybodyTest(){
      jpaPersonRepository.saveAll(DataPreparationUtil.getTestDataForNoOneKnowsAnybody());
      Optional<Person> celebrity = jpaPersonRepository.findCelebrityByDatabaseProcessing();
      Assert.assertFalse(celebrity.isPresent());
   }

}