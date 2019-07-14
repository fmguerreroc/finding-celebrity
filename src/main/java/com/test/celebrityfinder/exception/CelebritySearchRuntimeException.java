package com.test.celebrityfinder.exception;

public class CelebritySearchRuntimeException extends CelebritySearchException{

   private static final String DATABASE_ERROR_MESSAGE = "There was a runtime error trying to processt team members "
         + "fto find celebrity";
   private static final long serialVersionUID = -6240791008972613748L;

   public CelebritySearchRuntimeException(final Throwable cause) {
      super(DATABASE_ERROR_MESSAGE, cause);
   }
}
