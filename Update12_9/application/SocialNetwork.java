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
    if (userName == null) {
      return;
    }
    if (userName.equals("")) {
      throw new DuplicateNameException();
    }
    userName = userName.replaceAll("[^a-zA-Z0-9]", "");
    if (this.string().contains(userName)) {
      throw new DuplicateNameException();
    }
    if (userName.contains(""))
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

  /**
   * Person Arraylist of all users
   * 
   * @return
   */
  public ArrayList<Person> get() {
    return graph.getAllUsers();
  }

  /**
   * String Arraylist of all users
   * 
   * @return
   */
  public ArrayList<String> string() {
    return graph.list();
  }

  /**
   * Search for a specific person in the graph
   * 
   * @param person
   * @return
   */
  public Person search(String person) {
    return graph.find(person);
  }

  /**
   * Clears the graph
   */
  public void clear() {
    graph.clear();
  }

  /**
   * Find mutual friends between users
   */
  public ArrayList<String> mutuals(String user1, String user2) {
    ArrayList<Person> one = graph.getFriendsOf(user1);
    ArrayList<Person> two = graph.getFriendsOf(user2);
    ArrayList<String> three = new ArrayList<>();
    for (int i = 0; i < one.size(); i++) { // If both are friends add to list
      if (two.contains(one.get(i))) {
        three.add(one.get(i).getUserName());
      }
    }
    return three;
  }

}
