package com.test.celebrityfinder.exception;

public class CelebritySearchDatabaseException extends CelebritySearchException {

   private static final String DATABASE_ERROR_MESSAGE = "There was an error trying to retrieve the team members "
         + "from database";

   public CelebritySearchDatabaseException(final Throwable cause) {
      super(DATABASE_ERROR_MESSAGE, cause);
   }
}
