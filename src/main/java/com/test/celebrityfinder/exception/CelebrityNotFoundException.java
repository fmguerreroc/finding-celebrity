package com.test.celebrityfinder.exception;

public class CelebrityNotFoundException extends CelebritySearchException {

   private static final String CELEBRITY_NOT_FOUND = "There was no celebrity after processing records";
   private static final long serialVersionUID = -5934735369581423774L;

   public CelebrityNotFoundException() {
      super(CELEBRITY_NOT_FOUND);
   }
}
