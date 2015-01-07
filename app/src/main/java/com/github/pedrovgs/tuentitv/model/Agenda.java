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
 * Main class related to contacts feature. This class contains the responsibility to return user
 * logged contacts and favorite contacts. It also perform contact searches by name. All the data
 * this class returns is mocked for this sample.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class Agenda {

  private final List<Contact> contacts;
  private final List<Contact> favoriteContacts;

  @Inject public Agenda() {
    contacts = new LinkedList<Contact>();
    favoriteContacts = new LinkedList<Contact>();
    loadContacts();
    loadFavoriteContacts();
  }

  /**
   * @return every contact associated to the user logged sorted by name.
   */
  public List<Contact> getContacts() {
    return contacts;
  }

  /**
   * @return contacts marked by the logged user as favorite contacts. Returns this information
   * sorted by name.
   */
  public List<Contact> getFavorites() {
    return favoriteContacts;
  }

  /**
   * @return a list of contacts that matches with a name passed as parameter.
   */
  public List<Contact> getContactsWithName(String name) {
    List<Contact> searchResult = new LinkedList<Contact>();
    for (Contact contact : contacts) {
      String contactName = contact.getName().toLowerCase();
      if (contactName.contains(name)) {
        searchResult.add(contact);
      }
    }
    return searchResult;
  }

  /**
   * @return a Contact that matches with the id passed as parameter.
   */
  public Contact getContactById(String id) {
    Contact contactWithId = null;
    for (Contact contact : contacts) {
      if (id.equals(contact.getId())) {
        contactWithId = contact;
        break;
      }
    }
    return contactWithId;
  }

  private void loadContacts() {
    contacts.add(new Contact("Aarón", "https://imrl.tuenti.net/MexmzwOwyK_qaPXpAA",
        "https://imrl.tuenti.net/MeuEFQOwyK9ZMUl8AA"));
    contacts.add(new Contact("Andu Fratu", "https://imrl.tuenti.net/MewKTgRZKByd5oZ9AA",
        "https://imrl.tuenti.net/Mex2FgRZKBxFZQWKAA"));
    contacts.add(new Contact("Blanca Díaz", "https://imrl.tuenti.net/MexmsQOUyDnTABlnAA",
        "https://imrl.tuenti.net/Mer_-gRUXxq_3yZqAA"));
    contacts.add(new Contact("Carmen Barroso", "https://imrl.tuenti.net/MexNAAOXqRoOXZcYAA",
        "https://imrl.tuenti.net/MepPIQS-zo5ircngAA"));
    contacts.add(new Contact("Carlos Perez", "https://imrl.tuenti.net/ILQtUgOLW18w1mHaAA",
        "https://imrl.tuenti.net/MeOI0gOLW19UOvNpAA"));
    contacts.add(new Contact("Cesar Estébanez", "https://imrl.tuenti.net/MeqsBwSymhO82EB9AA",
        "https://imrl.tuenti.net/MeoZRgOOOk3wInOMAA"));
    contacts.add(new Contact("Diana Hernández", "https://imrl.tuenti.net/MeUzdwP0dhA6dQhWAA",
        "https://imrl.tuenti.net/MehaCQP0dhAyjmn_AA"));
    contacts.add(new Contact("Eduardo Ramirez", "https://imrl.tuenti.net/Mef8vgRt9SHKr5U0AA",
        "https://imrl.tuenti.net/MelqgQS-zo7D2jUkAA"));
    contacts.add(new Contact("Eduardo Gonzalez", "https://imrl.tuenti.net/MeHopQQgmSi7GQGdAA",
        "https://imrl.tuenti.net/MeoZRAOOOk07kA8MAA"));
    contacts.add(new Contact("Fina Perez", "https://imrl.tuenti.net/Mev9lAPPtkUlXKrgAA",
        "https://imrl.tuenti.net/Me4jFwTDI0SJxk1fAA"));
    contacts.add(new Contact("Fernando Cejas", "https://imrl.tuenti.net/MeyKggSu6c3B22wiAA",
        "https://imrl.tuenti.net/Meo-kQTBiDZBKnC9AA"));
    contacts.add(new Contact("Iván Mosquera", "https://imrl.tuenti.net/Mek7iQPYc565Wkj7AA",
        "https://imrl.tuenti.net/MepPAQS-zo6Lc9gnAA"));
    contacts.add(new Contact("Joaquin Engelmo", "https://imrl.tuenti.net/MeqNvwQ3IFNBDGnUAA",
        "https://imrl.tuenti.net/MexxswOZ9UAkEFn6AA"));
    contacts.add(new Contact("Juanjo Torroglosa", "https://imrl.tuenti.net/IMwzMQOv3yxSsLLxAA",
        "https://imrl.tuenti.net/Mevl_gS9EGaDmKQ7AA"));
    contacts.add(new Contact("Laura Rus", "https://imrl.tuenti.net/MefvswOREWiM7ehEAA",
        "https://imrl.tuenti.net/MeOW2gOREWgfCrI-AA"));
    contacts.add(new Contact("Manolo Vera", "https://imrl.tuenti.net/MeskMwOsS3aHCO-fAA",
        "https://imrl.tuenti.net/MehZuwOTKRmei3V-AA"));
    contacts.add(new Contact("Sebas Muriel", "https://imrl.tuenti.net/CZ-M0wOhf0IbHVN2AA",
        "https://imrl.tuenti.net/MePi1gOhf0LH0aG8AA"));
    contacts.add(new Contact("Víctor Corugedo", "https://imrl.tuenti.net/MczWnAPlHhALIiljAA",
        "https://imrl.tuenti.net/MejiHgP0dhCo4kjrAA"));
  }

  private void loadFavoriteContacts() {
    favoriteContacts.add(new Contact("Fina Perez", "https://imrl.tuenti.net/Mev9lAPPtkUlXKrgAA",
        "https://imrl.tuenti.net/Me4jFwTDI0SJxk1fAA"));
    favoriteContacts.add(
        new Contact("Joaquin Engelmo", "https://imrl.tuenti.net/MeqNvwQ3IFNBDGnUAA",
            "https://imrl.tuenti.net/MejJ5wRt9SGPKzF-AA"));
    contacts.add(new Contact("Laura Rus", "https://imrl.tuenti.net/MefvswOREWiM7ehEAA",
        "https://imrl.tuenti.net/MeOW2gOREWgfCrI-AA"));
    favoriteContacts.add(
        new Contact("Juanjo Torroglosa", "https://imrl.tuenti.net/IMwzMQOv3yxSsLLxAA",
            "https://imrl.tuenti.net/Mevl_gS9EGaDmKQ7AA"));
    favoriteContacts.add(
        new Contact("Víctor Corugedo", "https://imrl.tuenti.net/MczWnAPlHhALIiljAA",
            "https://imrl.tuenti.net/MejiHgP0dhCo4kjrAA"));
    favoriteContacts.add(new Contact("Iván Mosquera", "https://imrl.tuenti.net/Mek7iQPYc565Wkj7AA",
        "https://imrl.tuenti.net/MepPAQS-zo6Lc9gnAA"));
    contacts.add(new Contact("Carmen Barroso", "https://imrl.tuenti.net/MexNAAOXqRoOXZcYAA",
        "https://imrl.tuenti.net/MepPIQS-zo5ircngAA"));
  }
}
