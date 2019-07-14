package com.test.celebrityfinder.datapreparing;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import com.test.celebrityfinder.dataprovider.entity.Person;

public final class DataPreparationUtil {

   public static final BigInteger P1_ID = BigInteger.ZERO;
   public static final String P1_FIRST_NAME = "p1-first-name";
   public static final String P1_LAST_NAME = "p1-last-name";
   public static final BigInteger P2_ID = BigInteger.ONE;
   public static final String P2_FIRST_NAME = "p2-first-name";
   public static final String P2_LAST_NAME = "p2-last-name";
   public static final BigInteger P3_ID = BigInteger.TEN;
   public static final String P3_FIRST_NAME = "p3-first-name";
   public static final String P3_LAST_NAME = "p3-last-name";

   private static Person.PersonBuilder p1 = Person.builder();
   private static Person.PersonBuilder p2 = Person.builder();
   private static Person.PersonBuilder p3 = Person.builder();


   static {
      p1.id(P1_ID).firstName(P1_FIRST_NAME).lastName(P1_LAST_NAME);
      p2.id(P2_ID).firstName(P2_FIRST_NAME).lastName(P2_LAST_NAME);
      p3.id(P3_ID).firstName(P3_FIRST_NAME).lastName(P3_LAST_NAME);
   }

   private DataPreparationUtil(){}

   public static List<Person> getTestDataForJustKnowMySelfButEverybodyKnowksMe(){
      p1.knowns(new HashSet<>(Arrays.asList(p2.build(), p3.build())));
      p2.knowns(new HashSet<>(Arrays.asList(p1.build(), p3.build())));
      p3.knowns(new HashSet<>(Collections.singletonList(p3.build())));
      return Arrays.asList(
            p1.build(),
            p2.build(),
            p3.build()
      );
   }

   public static List<Person> getTestDataForNotKnowAnybodyButEverybodyKnowsMe(){
      p1.knowns(new HashSet<>(Arrays.asList(p2.build(), p3.build())));
      p2.knowns(new HashSet<>(Arrays.asList(p1.build(), p3.build())));
      p3.knowns(new HashSet<>());
      return Arrays.asList(
            p1.build(),
            p2.build(),
            p3.build()
      );
   }

   public static List<Person> getTestDataForNoOneKnowsAnybody(){
      p1.knowns(new HashSet<>());
      p2.knowns(new HashSet<>());
      p3.knowns(new HashSet<>());
      return Arrays.asList(
            p1.build(),
            p2.build(),
            p3.build()
      );
   }

   public static List<Person> getTestDataForEveryOneKnowsEverybody(){
      p1.knowns(new HashSet<>(Arrays.asList(p2.build(), p3.build())));
      p2.knowns(new HashSet<>(Arrays.asList(p1.build(), p3.build())));
      p3.knowns(new HashSet<>(Arrays.asList(p1.build(), p2.build())));
      return Arrays.asList(
            p1.build(),
            p2.build(),
            p3.build()
      );
   }

   public static List<Person> getEmptyMembers(){
      p1.knowns(new HashSet<>());
      p2.knowns(new HashSet<>());
      p3.knowns(new HashSet<>());
      return Arrays.asList(p1.build(), p2.build(), p3.build());
   }
}
