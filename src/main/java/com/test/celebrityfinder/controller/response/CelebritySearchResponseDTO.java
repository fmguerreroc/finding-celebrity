package com.test.celebrityfinder.controller.response;

import com.test.celebrityfinder.dataprovider.entity.Person;
import com.test.celebrityfinder.exception.CelebritySearchException;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CelebritySearchResponseDTO {

   private final Person celebrity;
   private final String errorMessage;
   private final Long timeToProcessMillis;
   private final CelebritySearchException errorObject;

}
