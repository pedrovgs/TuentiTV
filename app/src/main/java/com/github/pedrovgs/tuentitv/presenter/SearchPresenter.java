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
package com.github.pedrovgs.tuentitv.presenter;

import com.github.pedrovgs.tuentitv.model.Agenda;
import com.github.pedrovgs.tuentitv.model.Contact;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Pedro Vicente Gómez Sánchez.
 */
public class SearchPresenter {

  private final Agenda agenda;

  private View view;

  @Inject public SearchPresenter(Agenda agenda) {
    this.agenda = agenda;
  }

  public void setView(View view) {
    this.view = view;
  }

  public void loadContacts() {
    List<Contact> contacts = agenda.getContacts();
    view.showAllContacts(contacts);
  }

  public void searchContacts(String query) {
    String lowerCaseQuery = query.toLowerCase();
    List<Contact> contacts = agenda.getContactsWithName(lowerCaseQuery);
    view.showSearchResultContacts(query, contacts);
  }

  public interface View {

    void showAllContacts(List<Contact> contacts);

    void showSearchResultContacts(String query, List<Contact> contacts);
  }
}
