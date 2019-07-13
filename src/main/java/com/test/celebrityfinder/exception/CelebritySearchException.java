package com.test.celebrityfinder.exception;

public class CelebritySearchException extends Exception{

   private static final long serialVersionUID = -1423594554077833764L;

   public CelebritySearchException(final String message, final Throwable cause){
      super(message, cause);
   }

   public CelebritySearchException(final String message){
      super(message);
   }
}
