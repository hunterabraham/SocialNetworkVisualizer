package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Filename: Graph.java 
 * Project: Social Network
 * p4 Authors: Harrison Ian Eli Mihir Hunter
 * 
 * Undirected and unweighted graph implementation
 * Adapted from Harrison's p4 PackageMangager
 */

public class Graph implements GraphADT {
  private Map<Person, ArrayList<Person>> graph = new HashMap<>();

  /*
   * Default no-argument constructor
   */
  public Graph() {

  }

  /**
   * Add new user to the graph.
   *
   * 
   */
  public void addUser(String person) {
    if (person == null) {
      return;
    }
    for (int i = 0; i < this.getAllUsers().size(); i++) {
      if (this.getAllUsers().get(i).getUserName().equals(person)) {
        return;
      }
    }
    graph.put(new Person(person), new ArrayList<Person>());
  }

  /**
   * Remove a user
   */
  public void removeUser(String user) {
    if (user == null) {
      return;
    }
    for (int i = 0; i < this.getAllUsers().size(); i++) {
      if (this.getAllUsers().get(i).getUserName().equals(user)) {
        Person person = this.getAllUsers().get(i);
        if (!graph.containsKey(person)) {
          return;
        }
        graph.values().stream().forEach(e -> e.remove(person));
        graph.remove(person);
      }
    }
  }

  /**
   * Find a specific person in the graph
   * 
   * @param user
   * @return
   */
  public Person find(String user) {
    Person person = null;
    for (int i = 0; i < this.getAllUsers().size(); i++) {
      if (this.getAllUsers().get(i).getUserName().equals(user)) {
        person = this.getAllUsers().get(i);
      }
    }
    return person;
  }

  /**
   *Add undirected edge from person1 to person2
   */
  public void addFriend(String person1, String person2) {
    if (person1 == null || person2 == null) {
      return;
    }
    if (!this.list().contains(person1))
      addUser(person1);

    if (!this.list().contains(person2))
      addUser(person2);


    if (graph.get(this.find(person1)).contains(this.find(person2))) {
      return;
    }
    graph.get(this.find(person2)).add(this.find(person1));
    graph.get(this.find(person1)).add(this.find(person2));
  }

  /**
   * Remove the edge from person1 to person2
   */
  public void removeFriend(String person1, String person2) {
    if (person1 == null || person2 == null) {
      return;
    }
    if (person1.equals(person2)) {
      return;
    }
    if (!graph.containsKey(this.find(person1))) {
      return;
    }
    if (!graph.containsKey(this.find(person2))) {
      return;
    }
    if (this.getFriendsOf(person1) == null) {
      return;
    }
    if (!this.getFriendsOf(person1).contains(this.find(person2))) {
      return;
    }
    if (graph.containsKey(this.find(person1)) && graph.containsKey(this.find(person2))) {
      if (graph.get(this.find(person1)).contains(this.find(person2))) {
        graph.get(this.find(person1)).remove(this.find(person2));
        graph.get(this.find(person2)).remove(this.find(person1));
      }
    }
  }

  /**
   * Returns a ArrayList that contains all the vertices
   * 
   */
  public ArrayList<Person> getAllUsers() {
    ArrayList<Person> aList = new ArrayList<Person>();
    for (Person x : this.graph.keySet()) {
      aList.add(x);
    }

    return aList;

  }

  /**
   * Get all the friends of a user
   *
   */
  public ArrayList<Person> getFriendsOf(String name) {
    return graph.get(this.find(name));

  }

  /**
   * Return list of string of user in graph
   * @return
   */
  public ArrayList<String> list() {
    ArrayList<String> list = new ArrayList<>();
    for (int i = 0; i < this.getAllUsers().size(); i++) {
      list.add(this.getAllUsers().get(i).getUserName());
    }
    return list;
  }


}


