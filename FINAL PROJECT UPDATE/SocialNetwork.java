package application;

import java.util.ArrayList;
import java.util.List;
import application.DuplicateNameException;
import application.Graph;
import application.SocialNetworkADT;

public class SocialNetwork implements SocialNetworkADT {
  private Graph graph;

  public SocialNetwork() {
    graph = new Graph();
  }

  @Override
  public void createNewAccount(String userName) throws DuplicateNameException {
    if (userName.equals("")) {
      throw new DuplicateNameException();
    }
    if (this.string().contains(userName)) {
      throw new DuplicateNameException();
    }
    graph.addUser(userName);
  }

  public void removeUser(String userName) throws DuplicateNameException {
    if (!this.string().contains(userName)) {
      throw new DuplicateNameException();
    }
    graph.removeUser(userName);
  }

  @Override
  public ArrayList<Person> getFriendList(String user) {
    return graph.getFriendsOf(user);
  }

  @Override
  public void addFriend(String user1, String user2) {
    graph.addFriend(user1, user2);
  }

  @Override
  public void removeFriend(String user1, String user2) {
    graph.removeFriend(user1, user2);
  }

  public ArrayList<Person> get() {
    return graph.getAllUsers();
  }

  public ArrayList<String> string() {
    return graph.list();
  }

  public Person search(String person) {
    return graph.find(person);
  }
}
