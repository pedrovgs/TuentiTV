package com.tuenti.tuentitv.di;

import com.tuenti.tuentitv.model.Accounts;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */

@Module(library = true) public class TuentiTvApplicationModule {

  @Provides @Singleton Accounts provideAccounts() {
    return new Accounts();
  }
}
