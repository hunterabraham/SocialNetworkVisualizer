package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Filename: Graph.java Project: p4 Authors:
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {
  private Map<Person, ArrayList<Person>> graph = new HashMap<>();



  /*
   * Default no-argument constructor
   */
  public Graph() {

  }

  /**
   * Add new vertex to the graph.
   *
   * If vertex is null or already exists, method ends without adding a vertex or throwing an
   * exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
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
   * Remove a vertex and all associated edges from the graph.
   * 
   * If vertex is null or does not exist, method ends without removing a vertex, edges, or throwing
   * an exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
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
   * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and unweighted) If either
   * vertex does not exist, add vertex, and add edge, no exception is thrown. If the edge exists in
   * the graph, no edge is added and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge is not in the graph
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
   * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed and unweighted) If
   * either vertex does not exist, or if an edge from vertex1 to vertex2 does not exist, no edge is
   * removed and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge from vertex1 to vertex2 is in the graph
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
   * Returns a Set that contains all the vertices
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
   * Get all the neighbor (adjacent) vertices of a vertex
   *
   */
  public ArrayList<Person> getFriendsOf(String name) {
    return graph.get(this.find(name));
    
  }

  /**
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


