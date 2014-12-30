/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pedrovgs.tuentitv.di;

import com.github.pedrovgs.tuentitv.model.Accounts;
import com.github.pedrovgs.tuentitv.model.Agenda;
import com.github.pedrovgs.tuentitv.model.Chat;
import com.github.pedrovgs.tuentitv.model.MediaGallery;
import com.github.pedrovgs.tuentitv.recommendation.builder.RecommendationBuilder;
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

  @Provides RecommendationBuilder provideRecommendationBuilder() {
    return new RecommendationBuilder();
  }
}
