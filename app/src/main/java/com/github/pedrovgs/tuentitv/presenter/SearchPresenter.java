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
