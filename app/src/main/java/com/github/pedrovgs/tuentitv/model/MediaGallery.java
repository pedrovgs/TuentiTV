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
package com.github.pedrovgs.tuentitv.model;

import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

/**
 * Main class related to media feature. This class contains the responsibility to return media
 * elements related to the logged user.All the data this class returns is mocked for this sample.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class MediaGallery {

  private List<MediaElement> mediaElements;

  @Inject public MediaGallery() {
    mediaElements = new LinkedList<MediaElement>();
    loadMediaElements();
  }

  /**
   * @return a list of MediaElement associated to the user logged in the application.
   */
  public List<MediaElement> getLatestMediaElements() {
    return mediaElements;
  }

  private void loadMediaElements() {
    mediaElements.add(
        new MediaElement("Augmented Reality day :)", "https://imrl.tuenti.net/MeVNOAO1hS95RMX9AA"));
    mediaElements.add(
        new MediaElement("Madrid: 4 - BCN: 0 ", "https://imrl.tuenti.net/Me2XLwONIBZEgj76AA"));
    mediaElements.add(
        new MediaElement("Congrats!!!", "https://imrl.tuenti.net/MephbgPFZwIHmNz3AA"));
    mediaElements.add(
        new MediaElement("Happy hallowing!", "https://imrl.tuenti.net/MepO8AS-zo4tbPwOAA"));
    mediaElements.add(
        new MediaElement("I hate mondays :_(", "https://imrl.tuenti.net/Melh1wOmYV2w27UHAA"));
    mediaElements.add(new MediaElement("Take a look at the hat xD",
        "https://imrl.tuenti.net/MevCbwOhf0Kgn2LmAA"));
    mediaElements.add(
        new MediaElement("After work at Tuenti", "https://imrl.tuenti.net/MevCdQOhf0IzmO7bAA"));
    mediaElements.add(new MediaElement("Cloud phone team picture \\o/",
        "https://imrl.tuenti.net/Mej6IwPYc55jJ3OuAA"));
    mediaElements.add(
        new MediaElement("Design team building!", "https://imrl.tuenti.net/MekIYwS-zo6iTThqAA"));
    mediaElements.add(
        new MediaElement("Love is in the air <3", "https://imrl.tuenti.net/MegZXgOmYV2hznMJAA"));
    mediaElements.add(
        new MediaElement("Fifa 14 winners!", "https://imrl.tuenti.net/MeoEZARzE9_n9j5bAA"));
    mediaElements.add(
        new MediaElement("Murcian experts xD", "https://imrl.tuenti.net/MepPHAS-zo4pJD4gAA"));
    mediaElements.add(
        new MediaElement("Free donuts, yeah!", "https://imrl.tuenti.net/Meo8egTBiDawcvM3AA"));
    mediaElements.add(new MediaElement("Really???", "https://imrl.tuenti.net/Me0keARq1hWI_3FFAA"));
  }
}
