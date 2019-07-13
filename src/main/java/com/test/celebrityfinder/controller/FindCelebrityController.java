package com.test.celebrityfinder.controller;

import com.test.celebrityfinder.abstraction.CelebrityFinder;
import com.test.celebrityfinder.controller.response.CelebritySearchResponseDTO;
import com.test.celebrityfinder.exception.CelebritySearchException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FindCelebrityController {

   private final CelebrityFinder byBackendCelebrityFinder;
   private final CelebrityFinder byDatabaseCelebrityFinder;

   @GetMapping("/get-celebrity-by-backend-processing")
   public CelebritySearchResponseDTO getCelebrityByBackEnd() {
      return getCelebrityResponse(byBackendCelebrityFinder);
   }

   @GetMapping("/get-celebrity-by-database-processing")
   public CelebritySearchResponseDTO getCelebrityByDatabase() {
      return getCelebrityResponse(byDatabaseCelebrityFinder);
   }

   private CelebritySearchResponseDTO getCelebrityResponse(final CelebrityFinder celebrityFinder) {
      long startTime = System.currentTimeMillis();
      CelebritySearchResponseDTO.CelebritySearchResponseDTOBuilder celebritySearchResponseDTOBuilder =
            CelebritySearchResponseDTO.builder();
      try {
         celebritySearchResponseDTOBuilder.celebrity(celebrityFinder.obtainCelebrity());
         celebritySearchResponseDTOBuilder.timeToProcessMillis(System.currentTimeMillis() - startTime);
         return celebritySearchResponseDTOBuilder.build();
      } catch (final CelebritySearchException e) {
         celebritySearchResponseDTOBuilder.errorMessage(e.getMessage());
         celebritySearchResponseDTOBuilder.errorObject(e);
         celebritySearchResponseDTOBuilder.timeToProcessMillis(System.currentTimeMillis() - startTime);
         return celebritySearchResponseDTOBuilder.build();
      }
   }

}
