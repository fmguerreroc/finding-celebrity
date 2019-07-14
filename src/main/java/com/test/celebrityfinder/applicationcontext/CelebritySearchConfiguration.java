package com.test.celebrityfinder.applicationcontext;

import com.test.celebrityfinder.abstraction.CelebrityFinder;
import com.test.celebrityfinder.dataprovider.repository.JPAPersonRepository;
import com.test.celebrityfinder.processing.ByBackEndProcessor;
import com.test.celebrityfinder.processing.ByDatabaseProcessor;
import com.test.celebrityfinder.service.TeamCelebrityProcessorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CelebritySearchConfiguration {

   @Bean
   CelebrityFinder byDatabaseCelebrityFinder(final ByDatabaseProcessor byDatabaseProcessor){
      return new TeamCelebrityProcessorService(byDatabaseProcessor);
   }

   @Bean
   CelebrityFinder byBackendCelebrityFinder(final ByBackEndProcessor byBackEndProcessor){
      return new TeamCelebrityProcessorService(byBackEndProcessor);
   }

   @Bean
   ByDatabaseProcessor byDatabaseProcessor(final JPAPersonRepository jpaPersonRepository){
      return new ByDatabaseProcessor(jpaPersonRepository);
   }

   @Bean
   ByBackEndProcessor byBackEndProcessor(final JPAPersonRepository jpaPersonRepository){
      return new ByBackEndProcessor(jpaPersonRepository);
   }

}
