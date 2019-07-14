package com.test.celebrityfinder.controller;

import java.math.BigInteger;

import com.test.celebrityfinder.abstraction.CelebrityFinder;
import com.test.celebrityfinder.controller.response.CelebritySearchResponseDTO;
import com.test.celebrityfinder.dataprovider.entity.Person;
import com.test.celebrityfinder.exception.CelebritySearchException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FindCelebrityControllerTest {

   private static final String TEST_FIRST_NAME = "Test First Name";
   private static final String TEST_LAST_NAME = "Test Last Name";
   private static final BigInteger TEST_ID = BigInteger.TEN;

   @Mock
   private CelebrityFinder byBackendCelebrityFinder;

   @Mock
   private CelebrityFinder byDatabaseCelebrityFinder;

   private FindCelebrityController findCelebrityController;

   @Before
   public void setup(){
      findCelebrityController = new FindCelebrityController(byBackendCelebrityFinder, byDatabaseCelebrityFinder);
   }

   @Test
   public void getCelebrityByBackEndSuccessTest() throws CelebritySearchException {
      Mockito.doReturn(Person.builder()
            .firstName(TEST_FIRST_NAME)
            .lastName(TEST_LAST_NAME)
            .id(TEST_ID)
            .build()).when(byBackendCelebrityFinder).obtainCelebrity();
      CelebritySearchResponseDTO celebritySearchResponseDTO = findCelebrityController.getCelebrityByBackEnd();
      Mockito.verify(byBackendCelebrityFinder).obtainCelebrity();
      Assert.assertEquals(TEST_ID,celebritySearchResponseDTO.getCelebrity().getId());
      Assert.assertEquals(TEST_FIRST_NAME, celebritySearchResponseDTO.getCelebrity().getFirstName());
      Assert.assertEquals(TEST_LAST_NAME, celebritySearchResponseDTO.getCelebrity().getLastName());
   }

   @Test
   public void getCelebrityByDatabaseSuccessTest() throws CelebritySearchException {
      Mockito.doReturn(Person.builder()
            .firstName(TEST_FIRST_NAME)
            .lastName(TEST_LAST_NAME)
            .id(TEST_ID)
            .build()).when(byDatabaseCelebrityFinder).obtainCelebrity();
      CelebritySearchResponseDTO celebritySearchResponseDTO = findCelebrityController.getCelebrityByDatabase();
      Mockito.verify(byDatabaseCelebrityFinder).obtainCelebrity();
      Assert.assertEquals(TEST_ID,celebritySearchResponseDTO.getCelebrity().getId());
      Assert.assertEquals(TEST_FIRST_NAME, celebritySearchResponseDTO.getCelebrity().getFirstName());
      Assert.assertEquals(TEST_LAST_NAME, celebritySearchResponseDTO.getCelebrity().getLastName());
   }
}