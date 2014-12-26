package com.github.pedrovgs.tuentitv.di;

import com.github.pedrovgs.tuentitv.model.Accounts;
import com.github.pedrovgs.tuentitv.model.Agenda;
import com.github.pedrovgs.tuentitv.model.Chat;
import com.github.pedrovgs.tuentitv.model.MediaGallery;
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

  @Provides @Singleton Agenda provideAgenda() {
    return new Agenda();
  }

  @Provides @Singleton MediaGallery provideMediaGalery() {
    return new MediaGallery();
  }

  @Provides @Singleton Chat provideChat() {
    return new Chat();
  }
}
