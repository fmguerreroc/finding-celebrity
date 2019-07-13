package com.test.celebrityfinder.abstraction;

import com.test.celebrityfinder.dataprovider.entity.Person;
import com.test.celebrityfinder.exception.CelebritySearchException;

public interface CelebrityFinder {

   Person obtainCelebrity() throws CelebritySearchException;

}
